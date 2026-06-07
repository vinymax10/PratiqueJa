package service.exercicio;

import java.util.List;

import dao.exercicio.ExercicioDAO;
import dao.exercicio.ExercicioPadraoDAO;
import dao.exercicio.ResultadoExercicioDAO;
import dao.usuario.UsuarioDAO;
import filtro.exercicio.FiltroExercicio;
import filtro.exercicio.FiltroExercicioPadrao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import matematica.ExercicioFactory;
import modelo.academico.Assunto;
import modelo.exercicio.ExercicioPadrao;
import modelo.matematica.AlternativaExercicio;
import modelo.matematica.Exercicio;
import modelo.usuario.Usuario;

@ApplicationScoped
public class ExercicioService
{
	@Inject
	private ExercicioDAO exercicioDAO;

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

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

	public List<Exercicio> renovarExercicios(Assunto assunto)
	{
		List<Exercicio> existentes = exercicioDAO.buscarPorAssunto(assunto);
		for(Exercicio e : existentes)
			exercicioDAO.remover(e);

		FiltroExercicioPadrao filtro = new FiltroExercicioPadrao();
		filtro.setAssunto(assunto);
		List<ExercicioPadrao> padroes = exercicioPadraoDAO.buscar(filtro);

		for(ExercicioPadrao padrao : padroes)
		{
			int qtd = padrao.getQuantidade() > 0 ? padrao.getQuantidade() : 1;
			for(int i = 0; i < qtd; i++)
			{
				Exercicio novo = ExercicioFactory.gerar(padrao.getClasse(), i + 1);
				novo.setAssunto(padrao.getAssunto());
				novo.setNivel(padrao.getNivel());
				novo.setGlobal(true);
				exercicioDAO.salvar(novo);
			}
		}

		return exercicioDAO.buscarPorAssunto(assunto);
	}

}
