package service.publicacao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
import modelo.publicacao.ConfigPost;
import modelo.publicacao.FormatoPost;
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

	// Uma vez por dia, às 08:00 (horário do servidor): processa as programações do dia.
	@Schedule(hour = "8", minute = "0", second = "0", persistent = false)
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
		PerfilCriador perfilCriador = configPost.getPerfilCriador();
		logger.fine("gerando conteudo para " + usuario.getNome()
		+ "\n" + programacaoPost);

		// Envio programado é exclusivo de quem tem plano ativo (o teste grátis é só sob demanda).
		boolean planoAtivo = usuario.getValidadePlano() != null
			&& !usuario.getValidadePlano().isBefore(java.time.LocalDate.now());
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

		ColorHolder.setCOLOR(configPost.getCorFonte());
		ColorHolder.setFORMULA(configPost.getCorFormula());

		List<ExercicioPadrao> exerciciosDoDia = sortearExercicios(
			programacaoPost.getAssunto().getExerciciosPadrao(), perfilCriador.getExerciciosPorDia());

		int gerados = 0;
		for(ExercicioPadrao exercicioPadrao : exerciciosDoDia)
		{
			if(perfilCriador.isAmbosFormatos())
			{
				conteudoPublicacaoService.gerarConteudoFeed(exercicioPadrao, programacaoPost);
				conteudoPublicacaoService.gerarConteudoReel(exercicioPadrao, programacaoPost);
				gerados += 2;
			}
			else if(programacaoPost.getFormato() == FormatoPost.Reel)
			{
				conteudoPublicacaoService.gerarConteudoReel(exercicioPadrao, programacaoPost);
				gerados++;
			}
			else
			{
				conteudoPublicacaoService.gerarConteudoFeed(exercicioPadrao, programacaoPost);
				gerados++;
			}
		}

		ColorHolder.clear();

		if(programacaoPost.isAvulsa())
			programacaoPostService.remover(programacaoPost);
		else
		{
			programacaoPostService.registrarEnvio(programacaoPost);
			creditoPostService.registrarConsumo(usuario, configPost, gerados);
		}
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
