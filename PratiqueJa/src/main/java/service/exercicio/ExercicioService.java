package service.exercicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import bean.download.Diretorio;
import bean.exercicio.ConfigDownload;
import dao.exercicio.ExercicioDAO;
import dao.exercicio.ResultadoExercicioDAO;
import dao.usuario.UsuarioDAO;
import filtro.exercicio.FiltroExercicio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ResultadoExercicio;
import modelo.questao.Alternativa;
import modelo.usuario.Usuario;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import pdf.exercicio.GerarLatexExercicio;

@ApplicationScoped
public class ExercicioService
{
	@Inject
	private ExercicioDAO exercicioDAO;

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
		Alternativa escolhida = exercicio.getAlternativaEscolhida();
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
			Alternativa correta = exercicio.correta();
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

}
