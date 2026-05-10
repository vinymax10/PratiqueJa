package service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import dao.exercicio.ExercicioDAO;
import dao.exercicio.ResultadoExercicioDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ResultadoExercicio;
import modelo.matematica.Conta;

@Named
@ApplicationScoped
public class ExercicioService
{
	@Inject
	private ExercicioDAO exercicioDAO;

	@Inject
	private ResultadoExercicioDAO resultadoExercicioDAO;

	public void construirExercicio(Exercicio exercicio)
	throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		Conta conta;
		for(int i = 0; i < exercicio.getExercicioPadrao().getQuantidade(); i++)
		{
			do
			{
				conta = (Conta) Class.forName(exercicio.getExercicioPadrao().getClasse()).getConstructor(Integer.TYPE).newInstance(i + 1);
				conta.setExercicio(exercicio);
				conta.setTipoExercicio(exercicio.getExercicioPadrao().getTipoExercicio());
			}
			while(exercicio.getContas().contains(conta));
			exercicio.getContas().add(conta);
		}

		if(exercicio.getPrazo() == null)
			exercicio.setPrazo(LocalDate.now());

		exercicioDAO.salvar(exercicio);
	}

	public void registrarResposta(Exercicio exercicio, Conta conta)
	{
		conta.setRespondida(true);
		if(conta.isCorreta())
			exercicio.incrementaContasCorretas();

		exercicio.incrementaContasRealizadas();
		exercicio.calculaNota();
		exercicio.setRealizacao(LocalDate.now());
		exercicio.setRealizado(true);

		if(exercicio.getResultadoExercicio() == null)
		{
			ResultadoExercicio resultado = new ResultadoExercicio();
			resultado.setExercicioPadrao(exercicio.getExercicioPadrao());
			resultado.setUsuario(exercicio.getUsuario());
			resultado.setRealizacao(LocalDate.now());
			resultado.setNota(exercicio.getNota());
			resultadoExercicioDAO.salvar(resultado);
			exercicio.setResultadoExercicio(resultado);
		}
		else
		{
			ResultadoExercicio resultado = exercicio.getResultadoExercicio();
			resultado.setNota(exercicio.getNota());
			resultadoExercicioDAO.salvar(resultado);
		}

		exercicioDAO.salvar(exercicio);
	}
}
