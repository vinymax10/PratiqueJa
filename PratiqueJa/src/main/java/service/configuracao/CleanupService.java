package service.configuracao;

import java.util.Iterator;
import java.util.List;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import dao.configuracao.ConfigCleanupDAO;
import dao.exercicio.ExercicioDAO;
import modelo.configuracao.ConfigCleanup;
import modelo.exercicio.Exercicio;

@Singleton
@Startup
public class CleanupService
{
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
			e.printStackTrace();
		}
	}

	private void removerExercicios(List<Exercicio> exercicios)
	{
		Iterator<Exercicio> it = exercicios.iterator();
		while(it.hasNext())
		{
			exercicioDAO.remover(it.next());
			it.remove();
		}
	}
}
