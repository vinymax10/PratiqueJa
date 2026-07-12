package service.publicacao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

import dao.configuracao.ConfigDAO;
import dao.publicacao.ConfigPostDAO;
import dao.publicacao.PedidoPostDAO;
import modelo.DocumentoFile;
import modelo.configuracao.Config;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.FormatoPost;
import modelo.publicacao.ItemPedidoPost;
import modelo.publicacao.PedidoPost;
import modelo.publicacao.ProgramacaoPost;
import modelo.publicacao.StatusPedidoPost;
import service.email.EmailService;
import util.ColorHolder;

/**
 * Orquestra a geração assíncrona dos posts de um {@link PedidoPost}: gera cada peça (feed/reel),
 * empacota os PNGs num ZIP e disponibiliza para download, atualizando o progresso.
 */
@Stateless
public class MontadorPostService implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ConteudoPublicacaoService conteudoPublicacaoService;

	@Inject
	private ProgramacaoPostService programacaoPostService;

	@Inject
	private PedidoPostDAO pedidoPostDAO;

	@Inject
	private ConfigPostDAO configPostDAO;

	@Inject
	private ConfigDAO configDAO;

	@Inject
	private EmailService emailService;

	private final Random random = new Random();

	/**
	 * Monta um pedido de posts de forma síncrona (bloqueia a thread chamadora até terminar).
	 * A concorrência é controlada por {@link FilaGeracaoPostService}, que garante uma única fila
	 * para toda a aplicação — este método nunca deve ser chamado diretamente a partir de um bean
	 * de tela; use {@code FilaGeracaoPostService#enfileirar(Long)}.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void montar(Long pedidoId)
	{
		PedidoPost pedido = pedidoPostDAO.carrega(pedidoId);
		if(pedido == null)
			return;

		pedido.setStatus(StatusPedidoPost.GERANDO);
		pedido.setProgresso(0);
		pedidoPostDAO.salvar(pedido);

		try
		{
			Config config = configDAO.buscar();
			ConfigPost configPost = configPostDAO.getComLogo(pedido.getConfigPost().getId());

			ColorHolder.setCOLOR(ConfigPost.COR_FONTE);
			ColorHolder.setFORMULA(ConfigPost.COR_FORMULA);

			int totalPosts = pedido.getQuantidade();
			int feitos = 0;

			ByteArrayOutputStream saida = new ByteArrayOutputStream();
			try(ZipOutputStream zip = new ZipOutputStream(saida))
			{
				int ordemItem = 0;
				for(ItemPedidoPost item : pedido.getItens())
				{
					ordemItem++;

					// Programação transitória com a config de fundo do item (sorteia se aleatório).
					ProgramacaoPost prog = montarProg(item, configPost);

					// Para cada nível, sorteia `quantidade` exercícios daquele nível.
					List<ExercicioPadrao> exercicios = new ArrayList<>();
					for(Nivel nivel : item.getNiveis())
					{
						List<ExercicioPadrao> doNivel = filtrarPorNivel(
							item.getAssunto().getExerciciosPadrao(), nivel);
						exercicios.addAll(sortearExercicios(doNivel, item.getQuantidade()));
					}

					for(int i = 0; i < exercicios.size(); i++)
					{
						ExercicioPadrao exercicio = exercicios.get(i);
						String base = "assunto" + ordemItem + "-" + String.format("%02d", i + 1);

						// Legenda (CTA + hashtags) pronta para copiar — a mesma do e-mail.
						byte[] legenda = conteudoPublicacaoService.montarLegenda(exercicio, configPost)
							.getBytes(StandardCharsets.UTF_8);

						if(item.getFormato() == FormatoPost.Feed)
						{
							byte[][] imagens = conteudoPublicacaoService.gerarImagensFeed(exercicio, prog);
							adicionarAoZip(zip, base + "-feed-exercicio.png", imagens[0]);
							adicionarAoZip(zip, base + "-feed-resolucao.png", imagens[1]);
							adicionarAoZip(zip, base + "-feed-legenda.txt", legenda);
						}
						else
						{
							byte[][] imagens = conteudoPublicacaoService.gerarImagensReel(exercicio, prog);
							adicionarAoZip(zip, base + "-reel-exercicio.png", imagens[0]);
							adicionarAoZip(zip, base + "-reel-resolucao.png", imagens[1]);
							adicionarAoZip(zip, base + "-reel-legenda.txt", legenda);
						}

						feitos++;
						atualizarProgresso(pedido, feitos, totalPosts);
					}
				}
			}
			finally
			{
				ColorHolder.clear();
			}

			String nomeDownload = nomeArquivo(pedido) + ".zip";
			Path baseDir = Path.of(config.getEnderecoAvaliacao(), "posts", pedido.getCodigoBatch());
			Files.createDirectories(baseDir);
			Path destino = baseDir.resolve(nomeDownload);
			Files.write(destino, saida.toByteArray());

			pedido.setCaminhoArquivo(destino.toAbsolutePath().toString());
			pedido.setNomeDownload(nomeDownload);
			pedido.setProgresso(100);
			pedido.setDataExpiracao(LocalDateTime.now().plusDays(configPost.getUsuario().getPerfilCriador().getDiasRetencao()));
			pedido.setStatus(StatusPedidoPost.CONCLUIDO);
			pedidoPostDAO.salvar(pedido);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ColorHolder.clear();
			pedido.setStatus(StatusPedidoPost.ERRO);
			pedidoPostDAO.salvar(pedido);
		}
	}

	/**
	 * Sorteia até {@code quantidade} exercícios distintos do assunto. Se houver menos que a cota,
	 * usa todos os disponíveis.
	 */
	/** Mantém só os exercícios do nível pedido; null = todos os níveis. */
	/** Monta uma ProgramacaoPost transitória com a config de fundo do item (sorteia o fundo se aleatório). */
	private ProgramacaoPost montarProg(ItemPedidoPost item, ConfigPost configPost)
	{
		ProgramacaoPost prog = new ProgramacaoPost();
		prog.setConfigPost(configPost);
		prog.setAssunto(item.getAssunto());
		prog.setFormato(item.getFormato());
		prog.setAlternativaReel(item.isAlternativaReel());
		prog.setBackgroundAleatorio(item.isBackgroundAleatorio());
		prog.setBasePadrao(item.isBasePadrao());
		prog.setBackground(item.getBackground());
		prog.setPadrao(item.getPadrao());
		programacaoPostService.setImagemPost(prog);
		return prog;
	}

	private List<ExercicioPadrao> filtrarPorNivel(List<ExercicioPadrao> exercicios, Nivel nivel)
	{
		List<ExercicioPadrao> filtrados = new ArrayList<>();
		for(ExercicioPadrao exercicio : exercicios)
			if(exercicio.getNivel() == nivel)
				filtrados.add(exercicio);
		return filtrados;
	}

	private List<ExercicioPadrao> sortearExercicios(List<ExercicioPadrao> exercicios, int quantidade)
	{
		List<ExercicioPadrao> base = new ArrayList<>(exercicios);
		if(base.isEmpty())
			return base;

		Collections.shuffle(base, random);

		// Honra a quantidade pedida; se o assunto tiver menos exercícios, repete ciclando.
		List<ExercicioPadrao> resultado = new ArrayList<>(quantidade);
		for(int i = 0; i < quantidade; i++)
			resultado.add(base.get(i % base.size()));
		return resultado;
	}

	/** Envia (ou reenvia) por e-mail o ZIP de um pedido já gerado, lido do disco. */
	public void reenviarPorEmail(Long pedidoId)
	{
		PedidoPost pedido = pedidoPostDAO.carrega(pedidoId);
		if(pedido == null || pedido.getCaminhoArquivo() == null)
			return;
		try
		{
			byte[] zipBytes = Files.readAllBytes(Path.of(pedido.getCaminhoArquivo()));
			enviarPorEmail(pedido, pedido.getNomeDownload(), zipBytes);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Reenvia um lote já gerado como <b>um e-mail por post</b>, no mesmo padrão dos e-mails da
	 * programação diária (prévia das imagens embutidas + legenda pronta). Regenera o conteúdo a
	 * partir dos itens do pedido, então as peças podem diferir das do ZIP baixado.
	 */
	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void reenviarPorPost(Long pedidoId)
	{
		PedidoPost pedido = pedidoPostDAO.carrega(pedidoId);
		if(pedido == null)
			return;

		try
		{
			ConfigPost configPost = configPostDAO.getComLogo(pedido.getConfigPost().getId());
			// Os e-mails por post leem o destinatário do configPost; garante o usuário do pedido.
			configPost.setUsuario(pedido.getUsuario());

			ColorHolder.setCOLOR(ConfigPost.COR_FONTE);
			ColorHolder.setFORMULA(ConfigPost.COR_FORMULA);

			for(ItemPedidoPost item : pedido.getItens())
			{
				// Programação transitória com a config de fundo do item (sorteia se aleatório).
				ProgramacaoPost prog = montarProg(item, configPost);

				List<ExercicioPadrao> exercicios = new ArrayList<>();
				for(Nivel nivel : item.getNiveis())
				{
					List<ExercicioPadrao> doNivel = filtrarPorNivel(
						item.getAssunto().getExerciciosPadrao(), nivel);
					exercicios.addAll(sortearExercicios(doNivel, item.getQuantidade()));
				}

				for(ExercicioPadrao exercicio : exercicios)
				{
					if(item.getFormato() == FormatoPost.Feed)
						conteudoPublicacaoService.gerarConteudoFeed(exercicio, prog);
					else
						conteudoPublicacaoService.gerarConteudoReel(exercicio, prog);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			ColorHolder.clear();
		}
	}

	/** Envia o ZIP gerado por e-mail quando o usuário pediu (anexo). Falha de e-mail não derruba o pedido. */
	private void enviarPorEmail(PedidoPost pedido, String nomeDownload, byte[] zipBytes)
	{
		try
		{
			String email = pedido.getUsuario() != null ? pedido.getUsuario().getEmail() : null;
			if(email == null || email.isBlank())
				return;

			DocumentoFile anexo = emailService.criarAnexo(nomeDownload, zipBytes);

			String nome = pedido.getUsuario().getFirstNome();
			String html = montarHtmlZip(nome, pedido.getCodigoBatch(), pedido.getNome(), pedido.getQuantidade());

			emailService.adicionar(email, "Seus posts do Pratique Já estão prontos", html, List.of(anexo));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Corpo HTML do e-mail do lote (ZIP anexado), no mesmo padrão visual dos e-mails individuais:
	 * identidade do Pratique Já, saudação, badges do lote e o destaque do arquivo anexado. Inicia
	 * com "&lt;" para o {@code CommonsEmail} reconhecê-lo como HTML.
	 */
	private String montarHtmlZip(String nome, String codigoLote, String nomeLote, int quantidade)
	{
		nome = escapeHtml(nome);
		String codigo = escapeHtml(codigoLote);
		String tituloLote = nomeLote != null && !nomeLote.isBlank() ? escapeHtml(nomeLote) : "Lote de posts";
		String qtd = quantidade + " post" + (quantidade != 1 ? "s" : "");

		return "<!DOCTYPE html><html><body style=\"margin:0;padding:0;background:#eef1f8;\">"
		+ "<table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:#eef1f8;padding:24px 12px;font-family:Arial,Helvetica,sans-serif;\"><tr><td align=\"center\">"
		+ "<table role=\"presentation\" width=\"600\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:600px;width:100%;background:#ffffff;border-radius:16px;overflow:hidden;border:1px solid #e2e8f4;\">"
		+ "<tr><td style=\"padding:20px 28px;border-bottom:3px solid #3b5bdb;\">"
		+ "<span style=\"font-size:22px;font-weight:bold;color:#3b5bdb;\">Pratique<span style=\"color:#de7b40;\">Já</span></span>"
		+ "<span style=\"font-size:12px;color:#8a93a6;float:right;padding-top:9px;\">Material para Instagram</span>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:26px 28px 6px;\">"
		+ "<p style=\"margin:0 0 6px;font-size:17px;color:#2b3445;\">Olá, <b>" + nome + "</b>! 👋</p>"
		+ "<p style=\"margin:0 0 16px;font-size:14px;color:#6b7689;line-height:1.5;\">Seu lote de posts está pronto! O arquivo com todas as imagens (feed e reels) e as legendas está anexado a este e-mail.</p>"
		+ "<span style=\"display:inline-block;background:#eaeefb;color:#3b5bdb;border-radius:8px;padding:5px 12px;font-size:13px;font-weight:bold;margin:0 6px 6px 0;\">Lote " + codigo + "</span>"
		+ "<span style=\"display:inline-block;background:#fceee4;color:#de7b40;border-radius:8px;padding:5px 12px;font-size:13px;font-weight:bold;margin:0 0 6px;\">" + qtd + "</span>"
		+ "</td></tr>"
		+ "<tr><td align=\"center\" style=\"padding:14px 28px 6px;\">"
		+ "<div style=\"background:#f6f8fc;border:1px dashed #c2cce0;border-radius:12px;padding:22px 18px;\">"
		+ "<div style=\"font-size:34px;line-height:1;margin-bottom:8px;\">📦</div>"
		+ "<p style=\"margin:0;font-size:15px;font-weight:bold;color:#2b3445;\">" + tituloLote + "</p>"
		+ "<p style=\"margin:6px 0 0;font-size:13px;color:#6b7689;line-height:1.5;\">Baixe o arquivo <b>.zip</b> anexo e descompacte para acessar todos os posts.</p>"
		+ "</div>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:16px 28px 4px;\">"
		+ "<p style=\"margin:0 0 8px;font-size:14px;font-weight:bold;color:#2b3445;\">📂 O que vem no arquivo</p>"
		+ "<div style=\"background:#f6f8fc;border:1px solid #e2e8f4;border-radius:12px;padding:14px 18px;font-size:14px;color:#2b3445;line-height:1.8;\">"
		+ "🖼️ Imagens do <b>exercício</b> e da <b>resolução</b> (feed e/ou reel)<br>"
		+ "✍️ Um arquivo de <b>legenda</b> pronto para copiar e colar em cada post"
		+ "</div>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:18px 28px 8px;\">"
		+ "<p style=\"margin:0;font-size:13px;color:#6b7689;line-height:1.5;\">É só baixar, escolher os posts e publicar. Bons posts! 🚀</p>"
		+ "</td></tr>"
		+ "<tr><td style=\"padding:8px 28px 24px;border-top:1px solid #eef1f8;\">"
		+ "<p style=\"margin:14px 0 0;font-size:13px;color:#3b5bdb;font-weight:bold;\">Equipe do Pratique Já</p>"
		+ "<p style=\"margin:2px 0 0;font-size:12px;color:#8a93a6;\">pratiqueja.com</p>"
		+ "</td></tr>"
		+ "</table></td></tr></table></body></html>";
	}

	/** Escapa os caracteres que quebrariam o HTML (conteúdo vem do banco/usuário). */
	private String escapeHtml(String texto)
	{
		if(texto == null)
			return "";
		return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
	}

	private void adicionarAoZip(ZipOutputStream zip, String nome, byte[] bytes) throws IOException
	{
		zip.putNextEntry(new ZipEntry(nome));
		zip.write(bytes);
		zip.closeEntry();
	}

	private void atualizarProgresso(PedidoPost pedido, int feitos, int total)
	{
		// Deixa 5% para o empacotamento/gravação do ZIP.
		double progresso = total > 0 ? (feitos * 95.0) / total : 0;
		pedido.setProgresso(progresso);
		pedidoPostDAO.salvar(pedido);
	}

	private String nomeArquivo(PedidoPost pedido)
	{
		String nome = pedido.getNome() != null && !pedido.getNome().isBlank() ? pedido.getNome() : "posts";
		return nome.toLowerCase()
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

	public static String gerarCodigoBatch()
	{
		String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		return uuid.substring(0, 4);
	}
}
