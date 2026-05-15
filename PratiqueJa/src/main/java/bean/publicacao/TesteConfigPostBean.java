package bean.publicacao;

import java.io.IOException;
import java.io.Serializable;

import util.ColorHolder;
import bean.download.Diretorio;
import bean.download.PoolNomesBean;
import bean.util.Mensagem;
import dao.exercicio.ExercicioPadraoDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import pdf.social.InstagramFeed;
import pdf.social.TikTok;
import service.ImagemPostService;
import service.ProgramacaoPostService;

@Named
@ViewScoped
public class TesteConfigPostBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ProgramacaoPost programacaoPost;

	@Inject
	private ConfigPostBean configPostBean;

	@Inject
	private ProgramacaoPostService programacaoPostService;

	@Inject
	private ImagemPostService imagemPostService;

	@Inject
	private PoolNomesBean poolNomesBean;

	private Nivel nivel;

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;

	private boolean validacao()
	{
		ConfigPost configPost = configPostBean.getConfigPost();
		if(configPost.getLogo() == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Por favor adicione uma logomarca em configurações para realizar o teste.");
			return false;
		}

		if(configPost.getNome() == null || configPost.getNome().equals(""))
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Por favor adicione um nome em configurações para realizar o teste.");
			return false;
		}

		return true;
	}

	public String teste(boolean feed)
	{
		if(validacao())
		{
			ConfigPost configPost = configPostBean.getConfigPost();
			programacaoPost.setConfigPost(configPost);
			programacaoPost.setTeste(true);
			programacaoPostService.setImagemPost(programacaoPost);

			ColorHolder.setCOLOR(configPost.getCorFonte());
			ColorHolder.setFORMULA(configPost.getCorFormula());

			if(feed)
				gerarConteudoFeed();
			else
				gerarConteudoReel();

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Teste realizado com sucesso.");
			ColorHolder.clear();
		}

		return "";
	}

	public void gerarConteudoFeed()
	{
		Diretorio diretorio = poolNomesBean.criarDiretorio();
		InstagramFeed gerarLatex = new InstagramFeed(diretorio);
		ExercicioPadrao exercicioPadrao = exercicioPadraoDAO.buscar(programacaoPost.getAssuntoCurso(), nivel);

		gerarLatex.gerarPDFExercicio(exercicioPadrao, programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		try
		{
			imagemPostService.gravarTeste(true, diretorio, configPostBean.getConfigPost());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		poolNomesBean.freeDiretorio(diretorio);
	}

	public void gerarConteudoReel()
	{
		Diretorio diretorio = poolNomesBean.criarDiretorio();
		TikTok gerarLatex = new TikTok(diretorio);
		ExercicioPadrao exercicioPadrao = exercicioPadraoDAO.buscar(programacaoPost.getAssuntoCurso(), nivel);

		gerarLatex.gerarPDFExercicio(exercicioPadrao, programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		try
		{
			imagemPostService.gravarTeste(false, diretorio, configPostBean.getConfigPost());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		poolNomesBean.freeDiretorio(diretorio);
	}

	@PostConstruct
	public void init()
	{
	}

	public ProgramacaoPost getProgramacaoPost()
	{
		return programacaoPost;
	}

	public void setProgramacaoPost(ProgramacaoPost ProgramacaoPost)
	{
		this.programacaoPost = ProgramacaoPost;
	}

	public Nivel getNivel()
	{
		return nivel;
	}

	public void setNivel(Nivel nivel)
	{
		this.nivel = nivel;
	}
}
