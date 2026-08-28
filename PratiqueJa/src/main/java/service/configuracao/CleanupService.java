package service.configuracao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;

import dao.configuracao.ConfigCleanupDAO;
import dao.exercicio.ExercicioDAO;
import modelo.configuracao.ConfigCleanup;
import modelo.exercicio.Exercicio;

/**
 * Remoção diária dos exercícios vencidos. <b>Hoje as chamadas estão comentadas</b> — a forma foi
 * corrigida mesmo assim, para que reativá-las seja só descomentar.
 *
 * <p><b>Um exercício, uma transação.</b> A classe é {@code NOT_SUPPORTED} de propósito: sem isso o
 * método do timer roda no default {@code REQUIRED}, o {@code @Transactional} do
 * {@code ExercicioDAO.remover} entra nessa mesma transação e a varredura inteira — que aqui pode
 * ser grande, são exercícios de todos os usuários — vira um commit só. Estourados os 300s do
 * WildFly, ou dado um único erro, a limpeza toda é descartada sem deixar rastro.</p>
 */
@Singleton
@Startup
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CleanupService
{
	private static final Logger logger = Logger.getLogger(CleanupService.class.getName());

	@Inject
	private ExercicioDAO exercicioDAO;

	@Inject
	private ConfigCleanupDAO configCleanupDAO;

	@Schedule(hour = "0", minute = "0", second = "0", persistent = false)
	public void removerVencidos()
	{
		ConfigCleanup config = configCleanupDAO.buscar();
		try
		{
//			removerExercicios(exercicioDAO.exerciciosRealizados(config.getDiasRemoverExercicioRealizado()));
//			removerExercicios(exercicioDAO.exerciciosNaoRealizados(config.getDiasRemoverExercicioNaoRealizado()));
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Falha na limpeza de exercícios vencidos", e);
		}
	}

	@SuppressWarnings("unused")
	private void removerExercicios(List<Exercicio> exercicios)
	{
		int removidos = 0;
		int falhas = 0;

		for(Exercicio exercicio : exercicios)
		{
			// Cada remoção é a sua própria transação (a classe é NOT_SUPPORTED); um exercício
			// problemático não pode levar junto tudo o que já foi removido.
			try
			{
				exercicioDAO.remover(exercicio);
				removidos++;
			}
			catch(Exception e)
			{
				falhas++;
				logger.log(Level.WARNING, "Falha ao remover o exercício id=" + exercicio.getId()
					+ "; os demais seguem.", e);
			}
		}

		logger.info("Limpeza de exercícios: " + removidos + " removido(s), " + falhas + " com falha.");
	}
}
