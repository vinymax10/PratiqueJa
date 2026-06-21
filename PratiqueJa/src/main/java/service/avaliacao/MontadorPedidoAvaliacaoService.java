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

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import dao.avaliacao.PedidoAvaliacaoDAO;
import dao.configuracao.ConfigDAO;
import modelo.avaliacao.FormatoSaida;
import modelo.avaliacao.PedidoAvaliacao;
import modelo.avaliacao.PosicaoGabarito;
import modelo.avaliacao.StatusPedidoAvaliacao;
import modelo.avaliacao.TipoGabarito;
import modelo.configuracao.Config;

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

	@Asynchronous
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

			// Bytes da logo da escola lidos em transação (o gerador roda sem transação e não conseguiria
			// ler o LOB). null quando o usuário não é Profissional/Master ou não enviou logo.
			byte[] logoEscolaBytes = pedidoAvaliacaoDAO.buscarLogoEscolaBytes(pedidoId);

			int total = pedido.getQuantidade();
			List<byte[]> pdfsAvaliacao = new ArrayList<>(total);
			List<byte[]> pdfsGabarito = new ArrayList<>(1);
			// Blocos de cada exemplar, na ordem, para montar um único PDF de gabaritos ao final
			Map<String, List<BlocoExercicio>> gabaritosPorExemplar = new LinkedHashMap<>();

			Path styDir = Path.of("C:/Users/maximovrm/git/PratiqueJa/PratiqueJa/tex-new");
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
