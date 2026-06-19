package service.exercicio;

import java.util.List;

import dao.academico.AssuntoDAO;
import dao.exercicio.ConfigExercicioDAO;
import dao.exercicio.ExercicioDAO;
import dao.exercicio.ExercicioPadraoDAO;
import dao.exercicio.ResultadoExercicioDAO;
import dao.usuario.UsuarioDAO;
import filtro.exercicio.FiltroExercicio;
import filtro.exercicio.FiltroExercicioPadrao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import matematica.ExercicioFactory;
import modelo.academico.Assunto;
import modelo.exercicio.AlternativaExercicio;
import modelo.exercicio.ConfigExercicio;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ExercicioPadrao;
import modelo.usuario.Usuario;

@ApplicationScoped
public class ExercicioService
{
	@Inject
	private ExercicioDAO exercicioDAO;

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	@Inject
	private ConfigExercicioDAO configExercicioDAO;

	@Inject
	private AssuntoDAO assuntoDAO;

	@Inject
	private ResultadoExercicioDAO resultadoExercicioDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	public void construirExercicio(Exercicio exercicio)
	{
		exercicioDAO.salvar(exercicio);
	}

	public void registrarResposta(Exercicio exercicio)
	{
		AlternativaExercicio escolhida = exercicio.getAlternativaEscolhida();
		if(escolhida == null)
		{
			exercicio.setFeedbackSemSelecao(true);
			exercicio.setFeedbackAcertou(null);
			return;
		}

		escolhida.incrementaQtnEscolhida();
		boolean acertou = escolhida.isCorreta();
		exercicio.setFeedbackAcertou(acertou);
		exercicio.setFeedbackSemSelecao(false);

		if(!acertou)
		{
			AlternativaExercicio correta = exercicio.correta();
			exercicio.setFeedbackLetraCorreta(correta != null ? correta.getLetra() : null);
		}

		exercicioDAO.salvar(exercicio);
	}

	public Exercicio salvar(Exercicio exercicio)
	{
		return exercicioDAO.salvar(exercicio);
	}

	public void remover(Exercicio exercicio)
	{
		exercicioDAO.remover(exercicio);
	}

	public Exercicio carrega(Long id)
	{
		return exercicioDAO.carrega(id);
	}

	public List<Exercicio> buscar(FiltroExercicio filtroExercicio)
	{
		return exercicioDAO.buscar(filtroExercicio);
	}

	public List<Exercicio> meusExercicios(Usuario usuario, Boolean realizada)
	{
		return exercicioDAO.buscarGlobais();
	}

	public Long numeroMeusExercicios(Usuario usuario, Boolean realizado)
	{
		return (long) exercicioDAO.buscarGlobais().size();
	}

	public ResultadoLote gerarTodos()
	{
		List<ConfigExercicio> configs = configExercicioDAO.listarTudo();
		List<Assunto> assuntos = assuntoDAO.todos();

		int gerados = 0;
		int ignorados = 0;
		int erros = 0;

		for(ConfigExercicio cfg : configs)
		{
			for(Assunto assunto : assuntos)
			{
				ExercicioPadrao padrao = exercicioPadraoDAO.buscar(assunto, cfg.getNivel());
				if(padrao == null)
				{
					ignorados++;
					continue;
				}

				for(int i = 0; i < cfg.getQuantidade(); i++)
				{
					try
					{
						Exercicio exercicio = ExercicioFactory.gerar(padrao.getClasse(), i + 1);
						exercicio.setAssunto(padrao.getAssunto());
						exercicio.setNivel(padrao.getNivel());
						exercicio.setVisibilidade(cfg.getVisibilidade());
						exercicioDAO.salvar(exercicio);
						gerados++;
					}
					catch(Exception | LinkageError e)
					{
						e.printStackTrace();
						erros++;
					}
				}
			}
		}

		return new ResultadoLote(gerados, ignorados, erros);
	}

	@Getter
	@AllArgsConstructor
	public static class ResultadoLote
	{
		private final int gerados;
		private final int ignorados;
		private final int erros;
	}

}
