package bean.configuracao;

import java.io.Serializable;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.omnifaces.cdi.Startup;

import dao.configuracao.ConfigCleanupDAO;
import dao.exercicio.ExercicioDAO;
import dao.teste.TesteDAO;
import modelo.configuracao.ConfigCleanup;
import modelo.exercicio.Exercicio;
import modelo.teste.Teste;

@Startup
@Named
@ApplicationScoped
public class CleanupBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static Thread thread;
	
	@Inject
	private TesteDAO testeDAO;
	
	@Inject
	private ExercicioDAO exercicioDAO;
	
	@Inject
	private ConfigCleanupDAO configCleanupDAO;
	
	public void removerTestesVencidos()
	{
		ConfigCleanup configCleanup = configCleanupDAO.buscar();
		int diasRemoverTesteRealizado=configCleanup.getDiasRemoverTesteRealizado();
		int diasRemoverTesteNaoRealizado=configCleanup.getDiasRemoverTesteNaoRealizado();
		try
		{
			List<Teste> testes= testeDAO.testesRealizados(diasRemoverTesteRealizado);
			removerTestes(testes);
			
			testes= testeDAO.testesNaoRealizados(diasRemoverTesteNaoRealizado);
			removerTestes(testes);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void removerExerciciosVencidos()
	{
		ConfigCleanup configCleanup = configCleanupDAO.buscar();
		int diasRemoverExercicioRealizado=configCleanup.getDiasRemoverExercicioRealizado();
		int diasRemoverExercicioNaoRealizado=configCleanup.getDiasRemoverExercicioNaoRealizado();
		try
		{
			List<Exercicio> exercicios= exercicioDAO.exerciciosRealizados(diasRemoverExercicioRealizado);
			removerExercicios(exercicios);
			
			exercicios= exercicioDAO.exerciciosNaoRealizados(diasRemoverExercicioNaoRealizado);
			removerExercicios(exercicios);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void removerTestes(List<Teste> testes)
	{
		try
		{
			while(testes.size()>0)
			{
				Teste teste=testes.get(0);
				testes.remove(teste);
				testeDAO.remover(teste);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void removerExercicios(List<Exercicio> exercicios)
	{
		try
		{
			while(exercicios.size()>0)
			{
				Exercicio exercicio=exercicios.get(0);
				exercicios.remove(exercicio);
				exercicioDAO.remover(exercicio);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void init()
	{
		Cleanup cleanup=new Cleanup(this);
		thread = getInstance(cleanup);
		if(!thread.isAlive())
			thread.start();
	}
	
	public static Thread getInstance(Cleanup cleanup)
	{
		if(thread == null)
			thread = new Thread(cleanup);

		return thread;
	}
}
