package service.exercicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

import bean.download.Diretorio;
import bean.exercicio.ConfigDownload;
import dao.exercicio.ExercicioDAO;
import dao.exercicio.ResultadoExercicioDAO;
import dao.usuario.UsuarioDAO;
import filtro.exercicio.FiltroExercicio;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.exercicio.Exercicio;
import modelo.exercicio.ResultadoExercicio;
import modelo.matematica.Conta;
import modelo.usuario.Usuario;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import pdf.exercicio.GerarLatexExercicio;

@Named
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
		return exercicioDAO.meusExercicios(usuario, realizada);
	}

	public Long numeroMeusExercicios(Usuario usuario, Boolean realizado)
	{
		return exercicioDAO.numeroMeusExercicios(usuario, realizado);
	}

	public StreamedContent gerarPdfExercicio(Exercicio exercicio, ConfigDownload configDownload, Diretorio diretorio, Usuario usuario)
	{
		usuario = usuarioDAO.carrega(usuario.getId());
		configDownload.setUsuario(usuario);

		GerarLatexExercicio gerarLatex = new GerarLatexExercicio(diretorio);
		gerarLatex.gerarPDFExercicio(exercicio, configDownload);
		gerarLatex.gerar();

		File initialFile = new File(diretorio.getEnderecoPdf());
		InputStream inStream;
		StreamedContent file = null;
		try
		{
			inStream = new FileInputStream(initialFile);
			file = DefaultStreamedContent.builder()
				.name("Exercicio" + exercicio.getExercicioPadrao().getAssuntoCurso().getChave() + exercicio.getExercicioPadrao().getNivel() + ".pdf")
				.contentType("aplication/pdf")
				.stream(() -> inStream)
				.build();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}

		return file;
	}
}
