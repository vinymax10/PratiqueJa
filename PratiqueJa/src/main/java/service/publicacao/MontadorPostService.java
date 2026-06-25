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

import javax.sql.rowset.serial.SerialBlob;

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

	@Asynchronous
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

			ColorHolder.setCOLOR(configPost.getCorFonte());
			ColorHolder.setFORMULA(configPost.getCorFormula());

			int totalPosts = pedido.getQuantidade();
			int feitos = 0;

			ByteArrayOutputStream saida = new ByteArrayOutputStream();
			try(ZipOutputStream zip = new ZipOutputStream(saida))
			{
				int ordemItem = 0;
				for(ItemPedidoPost item : pedido.getItens())
				{
					ordemItem++;

					// Programação transitória só para carregar o branding e sortear backgrounds aleatórios.
					ProgramacaoPost prog = new ProgramacaoPost();
					prog.setConfigPost(configPost);
					prog.setAssunto(item.getAssunto());
					programacaoPostService.setImagemPost(prog);

					List<ExercicioPadrao> doNivel = filtrarPorNivel(
						item.getAssunto().getExerciciosPadrao(), item.getNivel());
					List<ExercicioPadrao> exercicios = sortearExercicios(doNivel, item.getQuantidade());

					for(int i = 0; i < exercicios.size(); i++)
					{
						ExercicioPadrao exercicio = exercicios.get(i);
						String base = "assunto" + ordemItem + "-" + String.format("%02d", i + 1);

						// Legenda (CTA + hashtags) pronta para copiar — a mesma do e-mail.
						byte[] legenda = conteudoPublicacaoService.montarLegenda(exercicio, configPost)
							.getBytes(StandardCharsets.UTF_8);

						if(item.getFormato().geraFeed())
						{
							byte[][] imagens = conteudoPublicacaoService.gerarImagensFeed(exercicio, prog);
							adicionarAoZip(zip, base + "-feed-exercicio.png", imagens[0]);
							adicionarAoZip(zip, base + "-feed-resolucao.png", imagens[1]);
							adicionarAoZip(zip, base + "-feed-legenda.txt", legenda);
							feitos++;
							atualizarProgresso(pedido, feitos, totalPosts);
						}

						if(item.getFormato().geraReel())
						{
							byte[][] imagens = conteudoPublicacaoService.gerarImagensReel(exercicio, prog);
							adicionarAoZip(zip, base + "-reel-exercicio.png", imagens[0]);
							adicionarAoZip(zip, base + "-reel-resolucao.png", imagens[1]);
							adicionarAoZip(zip, base + "-reel-legenda.txt", legenda);
							feitos++;
							atualizarProgresso(pedido, feitos, totalPosts);
						}
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
			pedido.setDataExpiracao(LocalDateTime.now().plusDays(configPost.getPerfilCriador().getDiasRetencao()));
			pedido.setStatus(StatusPedidoPost.CONCLUIDO);
			pedidoPostDAO.salvar(pedido);

			if(pedido.isEnviarEmail())
				enviarPorEmail(pedido, nomeDownload, saida.toByteArray());
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
	private List<ExercicioPadrao> filtrarPorNivel(List<ExercicioPadrao> exercicios, Nivel nivel)
	{
		if(nivel == null)
			return exercicios;
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

	/** Envia o ZIP gerado por e-mail quando o usuário pediu (anexo). Falha de e-mail não derruba o pedido. */
	private void enviarPorEmail(PedidoPost pedido, String nomeDownload, byte[] zipBytes)
	{
		try
		{
			String email = pedido.getUsuario() != null ? pedido.getUsuario().getEmail() : null;
			if(email == null || email.isBlank())
				return;

			DocumentoFile anexo = new DocumentoFile();
			anexo.setFile(new SerialBlob(zipBytes));
			anexo.setEndDocumentacao(nomeDownload);

			String html = "<p>Olá! Seus posts (lote <b>" + pedido.getCodigoBatch() + "</b>) estão prontos. "
				+ "O arquivo com as imagens (feed e reels) está anexado, pronto para baixar e publicar.</p>";

			emailService.adicionar(email, "Seus posts do Pratique Já estão prontos", html, List.of(anexo));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
