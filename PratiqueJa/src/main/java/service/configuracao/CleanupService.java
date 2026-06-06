package service.configuracao;

import java.util.Iterator;
import java.util.List;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import dao.configuracao.ConfigCleanupDAO;
import dao.exercicio.ExercicioDAO;
import dao.teste.TesteDAO;
import modelo.configuracao.ConfigCleanup;
import modelo.matematica.Exercicio;
import modelo.teste.Teste;

@Singleton
@Startup
public class CleanupService
{
	@Inject
	private TesteDAO testeDAO;

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
			removerTestes(testeDAO.testesRealizados(config.getDiasRemoverTesteRealizado()));
			removerTestes(testeDAO.testesNaoRealizados(config.getDiasRemoverTesteNaoRealizado()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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

	private void removerTestes(List<Teste> testes)
	{
		Iterator<Teste> it = testes.iterator();
		while(it.hasNext())
		{
			testeDAO.remover(it.next());
			it.remove();
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
