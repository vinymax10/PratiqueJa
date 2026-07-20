package service.publicacao;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.FormatoPost;
import modelo.publicacao.ItemPedidoPost;
import modelo.publicacao.PerfilCriador;
import modelo.publicacao.ProgramacaoPost;
import modelo.usuario.Usuario;
import util.ColorHolder;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EnvioPostService
{
	/** Indica que um envio (geração de conteúdo) já está em andamento. */
	public static final int OCUPADO = -1;

	private static final Logger logger = Logger.getLogger(EnvioPostService.class.getName());

	@Inject
	private ConteudoPublicacaoService conteudoPublicacaoService;

	@Inject
	private ProgramacaoPostService programacaoPostService;

	@Inject
	private CreditoPostService creditoPostService;

	@Inject
	private MontadorPostService montadorPostService;

	/**
	 * Garante que apenas um envio rode por vez (agendador ou disparo manual),
	 * sem bloquear a thread chamadora — quem não consegue o "lock" apenas é avisado.
	 */
	private final AtomicBoolean enviando = new AtomicBoolean(false);

	private final Random random = new Random();

	@PostConstruct
	public void init()
	{
		logger.info("----------------Envio Post iniciado----------------");
	}

	// Uma vez por dia, às 06:00 (horário do servidor): processa as programações do dia.
	@Schedule(hour = "6", minute = "0", second = "0", persistent = false)
	public void enviarProgramacao()
	{
		if(!enviando.compareAndSet(false, true))
		{
			logger.fine("Envio já em andamento; agendamento ignorado.");
			return;
		}

		try
		{
			List<ProgramacaoPost> programacoesPosts = programacaoPostService.listarHoje();
			logger.fine("size: " + programacoesPosts.size());
			for(ProgramacaoPost programacaoPost : programacoesPosts)
			{
				ConfigPost configPost = programacaoPost.getConfigPost();
				if(configPost.podeGerar())
					processar(programacaoPost);
			}
		}
		finally
		{
			enviando.set(false);
		}
	}

	/**
	 * Envia imediatamente as programações de um único {@link ConfigPost} cuja
	 * data já chegou (data &lt;= hoje), sem afetar os demais usuários.
	 *
	 * @return quantidade de programações geradas e enviadas, ou {@link #OCUPADO}
	 *         caso já exista um envio em andamento.
	 */
	public int enviarConfig(ConfigPost configPost)
	{
		if(!configPost.podeGerar())
			return 0;

		if(!enviando.compareAndSet(false, true))
			return OCUPADO;

		try
		{
			LocalDate hoje = LocalDate.now();

			// Cópia defensiva: registrarEnvio/remover reorganizam a lista do configPost.
			List<ProgramacaoPost> devidas = new ArrayList<>();
			for(ProgramacaoPost programacaoPost : configPost.getProgramacoesPost())
				if(programacaoPost.getData() != null && !programacaoPost.getData().isAfter(hoje))
					devidas.add(programacaoPost);

			for(ProgramacaoPost programacaoPost : devidas)
				processar(programacaoPost);

			return devidas.size();
		}
		finally
		{
			enviando.set(false);
		}
	}

	private void processar(ProgramacaoPost programacaoPost)
	{
		ConfigPost configPost = programacaoPost.getConfigPost();
		Usuario usuario = configPost.getUsuario();
		PerfilCriador perfilCriador = usuario.getPerfilCriador();
		logger.fine("gerando conteudo para " + usuario.getNome()
		+ "\n" + programacaoPost);

		// Envio programado é exclusivo de quem tem plano ativo (o teste grátis é só sob demanda).
		boolean planoAtivo = usuario.getValidadePlanoCriador() != null
			&& !usuario.getValidadePlanoCriador().isBefore(java.time.LocalDate.now());
		if(!programacaoPost.isAvulsa() && !planoAtivo)
		{
			logger.fine("Plano de conteúdo inativo para " + usuario.getNome() + "; envio programado pulado.");
			return;
		}

		// Híbrido: o envio programado consome do mesmo pool de créditos. Sem saldo, pula o envio
		// (a programação continua devida e tenta de novo no próximo ciclo). Avulsa (teste) não consome.
		int aGerar = perfilCriador.getPostsPorDia();
		if(!programacaoPost.isAvulsa() && creditoPostService.creditosRestantes(usuario, perfilCriador) < aGerar)
		{
			logger.fine("Sem créditos de post para " + usuario.getNome() + "; envio programado pulado.");
			return;
		}

		ColorHolder.setCOLOR(ConfigPost.COR_FONTE);
		ColorHolder.setFORMULA(ConfigPost.COR_FORMULA);

		List<ExercicioPadrao> exerciciosDoDia = sortearExercicios(
			programacaoPost.getAssunto().getExerciciosPadrao(), perfilCriador.getExerciciosPorDia());

		// Além de enviar o e-mail, guarda cada peça gerada (nome do arquivo → PNG/legenda) para
		// empacotar num ZIP e deixar o post programado baixável no histórico de pedidos.
		Map<String, byte[]> entradasZip = new LinkedHashMap<>();
		Set<Nivel> niveisUsados = new LinkedHashSet<>();
		boolean fezFeed = false;
		boolean fezReel = false;
		int gerados = 0;
		int indice = 0;

		for(ExercicioPadrao exercicioPadrao : exerciciosDoDia)
		{
			indice++;
			niveisUsados.add(exercicioPadrao.getNivel());
			String base = "post-" + String.format("%02d", indice);
			byte[] legenda = conteudoPublicacaoService.montarLegenda(exercicioPadrao, configPost)
				.getBytes(StandardCharsets.UTF_8);

			if(perfilCriador.isAmbosFormatos())
			{
				byte[][] feed = conteudoPublicacaoService.gerarConteudoFeed(exercicioPadrao, programacaoPost);
				adicionarPeca(entradasZip, base + "-feed", feed, legenda);
				byte[][] reel = conteudoPublicacaoService.gerarConteudoReel(exercicioPadrao, programacaoPost);
				adicionarPeca(entradasZip, base + "-reel", reel, legenda);
				fezFeed = true;
				fezReel = true;
				gerados += 2;
			}
			else if(programacaoPost.getFormato() == FormatoPost.Reel)
			{
				byte[][] reel = conteudoPublicacaoService.gerarConteudoReel(exercicioPadrao, programacaoPost);
				adicionarPeca(entradasZip, base + "-reel", reel, legenda);
				fezReel = true;
				gerados++;
			}
			else
			{
				byte[][] feed = conteudoPublicacaoService.gerarConteudoFeed(exercicioPadrao, programacaoPost);
				adicionarPeca(entradasZip, base + "-feed", feed, legenda);
				fezFeed = true;
				gerados++;
			}
		}

		ColorHolder.clear();

		if(programacaoPost.isAvulsa())
		{
			programacaoPostService.remover(programacaoPost);
			return;
		}

		programacaoPostService.registrarEnvio(programacaoPost);

		// Um item por formato gerado, para rastreabilidade no detalhe do pedido.
		List<ItemPedidoPost> itens = new ArrayList<>();
		if(fezFeed)
			itens.add(montarItem(programacaoPost, FormatoPost.Feed, niveisUsados, perfilCriador.getExerciciosPorDia()));
		if(fezReel)
			itens.add(montarItem(programacaoPost, FormatoPost.Reel, niveisUsados, perfilCriador.getExerciciosPorDia()));

		// Cria o pedido baixável (também serve de registro de consumo da cota mensal).
		montadorPostService.salvarPedidoProgramado(programacaoPost, entradasZip, itens, gerados);
	}

	/** Adiciona ao ZIP as duas imagens (exercício/resolução) e a legenda de uma peça. */
	private void adicionarPeca(Map<String, byte[]> entradas, String base, byte[][] imagens, byte[] legenda)
	{
		entradas.put(base + "-exercicio.png", imagens[0]);
		entradas.put(base + "-resolucao.png", imagens[1]);
		entradas.put(base + "-legenda.txt", legenda);
	}

	/** Item de pedido (rastreabilidade) espelhando a config da programação, para um formato. */
	private ItemPedidoPost montarItem(ProgramacaoPost prog, FormatoPost formato, Set<Nivel> niveis, int quantidade)
	{
		ItemPedidoPost item = new ItemPedidoPost();
		item.setAssunto(prog.getAssunto());
		item.setFormato(formato);
		item.setNiveis(new ArrayList<>(niveis));
		item.setQuantidade(quantidade);
		item.setAlternativaReel(prog.isAlternativaReel());
		item.setBackgroundAleatorio(prog.isBackgroundAleatorio());
		item.setBasePadrao(prog.isBasePadrao());
		item.setBackground(prog.getBackground());
		item.setPadrao(prog.getPadrao());
		item.setOrdem(0);
		return item;
	}

	/**
	 * Sorteia até {@code quantidade} exercícios distintos do assunto, para variar o
	 * conteúdo a cada envio. Se o assunto tiver menos exercícios que a cota, usa todos.
	 */
	private List<ExercicioPadrao> sortearExercicios(List<ExercicioPadrao> exercicios, int quantidade)
	{
		List<ExercicioPadrao> disponiveis = new ArrayList<>(exercicios);
		Collections.shuffle(disponiveis, random);
		int total = Math.min(quantidade, disponiveis.size());
		return disponiveis.subList(0, total);
	}

	public void acorda()
	{
		enviarProgramacao();
	}
}
