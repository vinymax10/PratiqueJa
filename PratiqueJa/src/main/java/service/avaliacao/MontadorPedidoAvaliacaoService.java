package service.avaliacao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import dao.avaliacao.PedidoAvaliacaoDAO;
import dao.configuracao.ConfigDAO;
import modelo.DocumentoFile;
import modelo.avaliacao.FormatoSaida;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.PosicaoGabarito;
import modelo.avaliacao.StatusPedidoAvaliacao;
import modelo.avaliacao.TipoGabarito;
import modelo.configuracao.Config;
import service.email.EmailService;

/**
 * Orquestra a geração assíncrona de todas as avaliações de um PedidoAvaliacao.
 */
@Stateless
public class MontadorPedidoAvaliacaoService implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private GeradorAvaliacaoPdfService geradorAvaliacao;

	@Inject
	private GeradorGabaritoPdfService geradorGabarito;

	@Inject
	private PedidoAvaliacaoDAO pedidoAvaliacaoDAO;

	@Inject
	private ConfigDAO configDAO;

	@Inject
	private EmailService emailService;

	/**
	 * Monta um pedido de avaliação de forma síncrona (bloqueia a thread chamadora até terminar).
	 * A concorrência é controlada por {@link FilaGeracaoAvaliacaoService}, que garante uma única
	 * fila para toda a aplicação — este método nunca deve ser chamado diretamente a partir de um
	 * bean de tela; use {@code FilaGeracaoAvaliacaoService#enfileirar(Long)}.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void montar(Long pedidoId)
	{
		PedidoAvaliacao pedido = pedidoAvaliacaoDAO.carrega(pedidoId);
		if (pedido == null) return;

		pedido.setStatus(StatusPedidoAvaliacao.GERANDO);
		pedido.setProgresso(0);
		pedidoAvaliacaoDAO.salvar(pedido);

		try
		{
			Config config = configDAO.buscar();
			// Diretório das avaliações derivado da raiz: {endereco}/avaliacoes/{codigoBatch}
			Path baseDir = Path.of(config.getEnderecoAvaliacao(), pedido.getCodigoBatch());
			Files.createDirectories(baseDir);

			boolean comGabaritoIndividual =
				pedido.getPosicaoGabarito() == PosicaoGabarito.APOS_CADA_AVALIACAO;

			String logoEscolaEndRel = pedidoAvaliacaoDAO.buscarLogoEscolaEndereco(pedidoId);
			byte[] logoEscolaBytes = null;
			if(logoEscolaEndRel != null)
			{
				Path logoPath = Path.of(config.getEndereco() + logoEscolaEndRel);
				if(Files.exists(logoPath))
					logoEscolaBytes = Files.readAllBytes(logoPath);
			}

			int total = pedido.getQuantidade();
			List<byte[]> pdfsAvaliacao = new ArrayList<>(total);
			List<byte[]> pdfsGabarito = new ArrayList<>(1);
			// Blocos de cada exemplar, na ordem, para montar um único PDF de gabaritos ao final
			Map<String, List<BlocoExercicio>> gabaritosPorExemplar = new LinkedHashMap<>();

			Path styDir = Path.of(config.getEnderecoTexNew());
			String xelatex = resolverXelatex(config);

			// Cada avaliação possui 3 sub-etapas (exercícios, PDF, gabarito) para granularidade do progresso
			int totalEtapas = total * 3;
			int etapaAtual = 0;

			for (int i = 1; i <= total; i++)
			{
				String codigoAvaliacao = pedido.getCodigoBatch() + "-" + String.format("%03d", i);
				Path workDir = baseDir.resolve("avaliacao_" + String.format("%03d", i));

				// Exercícios gerados uma única vez por avaliação → gabarito usa os mesmos
				List<BlocoExercicio> blocos = geradorAvaliacao.gerarExercicios(pedido);
				etapaAtual++;
				atualizarProgresso(pedido, etapaAtual, totalEtapas);

				byte[] pdfAvaliacao = geradorAvaliacao.gerarAvaliacao(
					pedido, blocos, codigoAvaliacao, styDir, xelatex, workDir,
					comGabaritoIndividual, logoEscolaBytes
				);
				pdfsAvaliacao.add(pdfAvaliacao);
				etapaAtual++;
				atualizarProgresso(pedido, etapaAtual, totalEtapas);

				if (!comGabaritoIndividual && temGabarito(pedido))
					gabaritosPorExemplar.put(codigoAvaliacao, blocos);
				etapaAtual++;
				atualizarProgresso(pedido, etapaAtual, totalEtapas);
			}

			// Todos os gabaritos (AGRUPADO_NO_FINAL) em um único PDF: compacto + linha tracejada
			// entre exemplares, ou 1 página por exemplar quando for com resolução.
			if (!gabaritosPorExemplar.isEmpty())
				pdfsGabarito.add(geradorGabarito.gerarGabaritosCombinados(
					pedido, gabaritosPorExemplar, styDir, xelatex, baseDir.resolve("gabaritos"), logoEscolaBytes));

			byte[] arquivoFinal;
			String nomeDownload;

			if (pedido.getFormatoSaida() == FormatoSaida.ZIP)
			{
				arquivoFinal = montarZip(pedido, pdfsAvaliacao, pdfsGabarito);
				nomeDownload = nomeArquivo(pedido) + ".zip";
			}
			else
			{
				arquivoFinal = montarPdfUnico(pdfsAvaliacao, pdfsGabarito);
				nomeDownload = nomeArquivo(pedido) + ".pdf";
			}

			String caminhoFinal = salvarArquivo(baseDir, nomeDownload, arquivoFinal);

			pedido.setCaminhoArquivo(caminhoFinal);
			pedido.setNomeDownload(nomeDownload);
			pedido.setProgresso(100);
			pedido.setStatus(StatusPedidoAvaliacao.CONCLUIDO);
			pedidoAvaliacaoDAO.salvar(pedido);

			limparDiretoriosTemp(baseDir, total);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			pedido.setStatus(StatusPedidoAvaliacao.ERRO);
			pedidoAvaliacaoDAO.salvar(pedido);
		}
	}

	// ── Montagem do arquivo final ─────────────────────────────────────

	private byte[] montarPdfUnico(List<byte[]> pdfsAvaliacao, List<byte[]> pdfsGabarito)
		throws IOException
	{
		PDFMergerUtility merger = new PDFMergerUtility();
		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		merger.setDestinationStream(saida);

		for (byte[] pdf : pdfsAvaliacao)
			merger.addSource(new RandomAccessReadBuffer(pdf));

		for (byte[] gab : pdfsGabarito)
			merger.addSource(new RandomAccessReadBuffer(gab));

		merger.mergeDocuments(null);
		return saida.toByteArray();
	}

	private byte[] montarZip(PedidoAvaliacao pedido, List<byte[]> pdfsAvaliacao,
		List<byte[]> pdfsGabarito) throws IOException
	{
		ByteArrayOutputStream saida = new ByteArrayOutputStream();
		try (ZipOutputStream zip = new ZipOutputStream(saida))
		{
			for (int i = 0; i < pdfsAvaliacao.size(); i++)
			{
				String codigo = pedido.getCodigoBatch() + "-" + String.format("%03d", i + 1);
				zip.putNextEntry(new ZipEntry("avaliacao_" + codigo + ".pdf"));
				zip.write(pdfsAvaliacao.get(i));
				zip.closeEntry();
			}

			if (!pdfsGabarito.isEmpty())
			{
				byte[] gabaritoMerge = montarPdfUnico(List.of(), pdfsGabarito);
				zip.putNextEntry(new ZipEntry("gabaritos_" + pedido.getCodigoBatch() + ".pdf"));
				zip.write(gabaritoMerge);
				zip.closeEntry();
			}
		}
		return saida.toByteArray();
	}

	// ── Envio por e-mail ──────────────────────────────────────────────

	/** Envia (ou reenvia) por e-mail o arquivo de um pedido já gerado, lido do disco. */
	public void reenviarPorEmail(Long pedidoId)
	{
		PedidoAvaliacao pedido = pedidoAvaliacaoDAO.carrega(pedidoId);
		if (pedido == null || pedido.getCaminhoArquivo() == null)
			return;
		try
		{
			byte[] bytes = Files.readAllBytes(Path.of(pedido.getCaminhoArquivo()));
			enviarPorEmail(pedido, pedido.getNomeDownload(), bytes);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/** Enfileira o e-mail com o arquivo anexado para o dono do pedido. Falha não derruba o fluxo. */
	private void enviarPorEmail(PedidoAvaliacao pedido, String nomeDownload, byte[] bytes)
	{
		try
		{
			String email = pedido.getUsuario() != null ? pedido.getUsuario().getEmail() : null;
			if (email == null || email.isBlank())
				return;

			DocumentoFile anexo = emailService.criarAnexo(nomeDownload, bytes);

			String nome = pedido.getUsuario().getFirstNome();
			String html = montarHtmlAvaliacao(nome, pedido.getCodigoBatch(), pedido.getTitulo(),
				pedido.getQuantidade(), nomeDownload);

			emailService.adicionar(email, "Sua avaliação do Pratique Já está pronta", html, List.of(anexo));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Corpo HTML do e-mail da avaliação (arquivo anexado), no mesmo padrão visual dos demais
	 * e-mails do Pratique Já. Inicia com "&lt;" para o e-mail ser reconhecido como HTML.
	 */
	private String montarHtmlAvaliacao(String nome, String codigo, String titulo, int quantidade,
		String nomeDownload)
	{
		nome = escapeHtml(nome);
		String cod = escapeHtml(codigo);
		String tit = titulo != null && !titulo.isBlank() ? escapeHtml(titulo) : "Avaliação";
		boolean zip = nomeDownload != null && nomeDownload.toLowerCase().endsWith(".zip");
		String formato = zip ? "ZIP" : "PDF";
		String qtd = quantidade + " avaliaç" + (quantidade != 1 ? "ões" : "ão");

		return "<!DOCTYPE html><html><body style=\"margin:0;padding:0;background:#eef1f8;\">"
		+ "<table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#eef1f8;padding:24px 12px;font-family:Arial,Helvetica,sans-serif;\"><tr><td align=\"center\">"
		+ "<table role=\"presentation\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;background:#ffffff;border-radius:16px;overflow:hidden;border:1px solid #e2e8f4;\">"
		+ "<tr><td style=\"padding:20px 28px;border-bottom:3px solid #2563eb;\">"
		+ "<span style=\"font-size:22px;font-weight:bold;color:#2563eb;\">Pratique<span style=\"color:#de7b40;\">Já</span></span>"
		+ "<span style=\"font-size:12px;color:#8a93a6;float:right;padding-top:9px;\">Confecção de Avaliações</span>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:26px 28px 6px;\">"
		+ "<p style=\"margin:0 0 6px;font-size:17px;color:#2b3445;\">Olá, <b>" + nome + "</b>! 👋</p>"
		+ "<p style=\"margin:0 0 16px;font-size:14px;color:#6b7689;line-height:1.5;\">Sua avaliação está pronta! O arquivo com a" + (quantidade != 1 ? "s provas e os gabaritos" : " prova e o gabarito") + " está anexado a este e-mail.</p>"
		+ "<span style=\"display:inline-block;background:#e7edfd;color:#2563eb;border-radius:8px;padding:5px 12px;font-size:13px;font-weight:bold;margin:0 6px 6px 0;\">Código " + cod + "</span>"
		+ "<span style=\"display:inline-block;background:#fceee4;color:#de7b40;border-radius:8px;padding:5px 12px;font-size:13px;font-weight:bold;margin:0 0 6px;\">" + qtd + "</span>"
		+ "</td></tr>"
		+ "<tr><td align=\"center\" style=\"padding:14px 28px 6px;\">"
		+ "<div style=\"background:#f6f8fc;border:1px dashed #c2cce0;border-radius:12px;padding:22px 18px;\">"
		+ "<div style=\"font-size:34px;line-height:1;margin-bottom:8px;\">📄</div>"
		+ "<p style=\"margin:0;font-size:15px;font-weight:bold;color:#2b3445;\">" + tit + "</p>"
		+ "<p style=\"margin:6px 0 0;font-size:13px;color:#6b7689;line-height:1.5;\">Baixe o arquivo <b>." + formato.toLowerCase() + "</b> anexo para imprimir e aplicar.</p>"
		+ "</div>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:18px 28px 28px;\">"
		+ "<p style=\"margin:0;font-size:12px;color:#8a93a6;line-height:1.6;\">Este e-mail foi enviado automaticamente pelo Pratique Já. Se você não solicitou esta avaliação, basta ignorá-lo.</p>"
		+ "</td></tr>"
		+ "</table></td></tr></table></body></html>";
	}

	private String escapeHtml(String texto)
	{
		if (texto == null)
			return "";
		return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
	}

	// ── Utilitários ───────────────────────────────────────────────────

	private boolean temGabarito(PedidoAvaliacao pedido)
	{
		return pedido.getTipoGabarito() != null;
	}

	private void atualizarProgresso(PedidoAvaliacao pedido, int etapa, int totalEtapas)
	{
		// Deixa 5% pro empacotamento final (zip/merge) e download
		double progresso = (etapa * 95.0) / totalEtapas;
		pedido.setProgresso(progresso);
		pedidoAvaliacaoDAO.salvar(pedido);
	}

	private String salvarArquivo(Path dir, String nome, byte[] bytes) throws IOException
	{
		Path destino = dir.resolve(nome);
		Files.write(destino, bytes);
		return destino.toAbsolutePath().toString();
	}

	private String nomeArquivo(PedidoAvaliacao pedido)
	{
		String titulo = pedido.getTitulo() != null ? pedido.getTitulo() : "avaliacao";
		return titulo.toLowerCase()
			.replaceAll("[áàãâä]", "a")
			.replaceAll("[éèêë]", "e")
			.replaceAll("[íìîï]", "i")
			.replaceAll("[óòõôö]", "o")
			.replaceAll("[úùûü]", "u")
			.replaceAll("[ç]", "c")
			.replaceAll("[^a-z0-9]+", "_")
			.replaceAll("^_|_$", "")
			+ "_" + pedido.getCodigoBatch();
	}

	private String resolverXelatex(Config config)
	{
		return (config.getNome() != null && config.getNome().contains("xelatex"))
			? config.getNome()
			: "xelatex";
	}

	private void limparDiretoriosTemp(Path baseDir, int total) throws IOException
	{
		for (int i = 1; i <= total; i++)
		{
			Path workDir = baseDir.resolve("avaliacao_" + String.format("%03d", i));
			if (Files.exists(workDir))
			{
				Files.list(workDir).forEach(f -> {
					try { Files.deleteIfExists(f); } catch (IOException ignored) {}
				});
				Files.deleteIfExists(workDir);
			}
		}
	}

	public static String gerarCodigoBatch()
	{
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		return uuid.substring(0, 4);
	}
}
