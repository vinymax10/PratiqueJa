package service.configuracao;

import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerConfig;
import jakarta.ejb.TimerService;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import service.email.EnvioEmailService;
import service.publicacao.EnvioPostService;

/**
 * Ao subir o servidor, dispara o que ficou pendente enquanto ele esteve fora do ar, em vez de
 * esperar a próxima janela de cada agendador:
 *
 * <ul>
 *   <li><b>E-mails</b> com status PENDENTE — o {@link EnvioEmailService} só varre de minuto em
 *       minuto, então sem isto a fila do restart espera até o próximo minuto cheio;</li>
 *   <li><b>Programações de post</b> devidas (data &le; hoje) — o {@link EnvioPostService} só roda
 *       às 06:00, então um servidor que passou o horário fora do ar (ou que subiu depois dele)
 *       deixaria o dia inteiro sem publicar.</li>
 * </ul>
 *
 * As filas sob demanda (pedidos de post e de avaliação) já se recuperam sozinhas no
 * {@code @PostConstruct} de {@code FilaGeracaoPostService}/{@code FilaGeracaoAvaliacaoService},
 * lendo do banco o que ficou em AGUARDANDO/GERANDO.
 *
 * <p>Repetir o disparo é seguro: e-mail enviado sai da lista de pendentes e
 * {@code ProgramacaoPostService.registrarEnvio} empurra a data da programação para o dia
 * seguinte — um segundo restart no mesmo dia não republica nada.</p>
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RecuperacaoPendenciasService
{
	/**
	 * O trabalho não pode rodar no próprio @PostConstruct: ele acontece no meio do deploy e a
	 * geração de post (um pdflatex por exercício) levaria minutos segurando a subida da
	 * aplicação. Um timer de disparo único adia para depois do boot.
	 */
	private static final long ATRASO_MS = 30_000;

	private static final Logger logger = Logger.getLogger(RecuperacaoPendenciasService.class.getName());

	@Resource
	private TimerService timerService;

	@Inject
	private EnvioEmailService envioEmailService;

	@Inject
	private EnvioPostService envioPostService;

	@PostConstruct
	public void agendar()
	{
		// persistent=false: o timer é recriado a cada start; não faz sentido guardá-lo no banco.
		timerService.createSingleActionTimer(ATRASO_MS, new TimerConfig(null, false));
		logger.info("---------------- Recuperação de pendências agendada para " + (ATRASO_MS / 1000)
			+ "s após o start ----------------");
	}

	@Timeout
	public void dispararPendentes(Timer timer)
	{
		logger.info("---------------- Verificando pendências de envio ----------------");

		// E-mail primeiro: é rápido e destrava a fila acumulada antes do trabalho pesado.
		try
		{
			envioEmailService.enviarPendentes();
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Falha ao enviar os e-mails pendentes no start", e);
		}

		// Posts depois: gera só o que está devido; o que já saiu hoje teve a data empurrada.
		try
		{
			envioPostService.enviarProgramacao();
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Falha ao enviar as programações de post pendentes no start", e);
		}

		logger.info("---------------- Pendências de envio processadas ----------------");
	}
}
