package bean.publicacao;

import java.io.IOException;
import java.io.Serializable;

import bean.download.Diretorio;
import bean.util.Mensagem;
import dao.exercicio.ExercicioPadraoDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import pdf.publicacao.InstagramFeed2;
import pdf.publicacao.TikTok2;
import service.configuracao.DiretorioService;
import service.publicacao.ImagemPostService;
import service.publicacao.ProgramacaoPostService;
import util.ColorHolder;

@Data
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
	private DiretorioService diretorioService;

	private Nivel nivel;

	/** Formato selecionado no switch do teste: true = Feed, false = Reel. */
	private boolean testarFeed = true;

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

		if(configPost.getNome() == null || configPost.getNome().isBlank())
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

			gerarConteudo(feed);

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Teste realizado com sucesso.");
			ColorHolder.clear();
		}

		return "";
	}

	public void gerarConteudoFeed()
	{
		gerarConteudo(true);
	}

	public void gerarConteudoReel()
	{
		gerarConteudo(false);
	}

	private void gerarConteudo(boolean feed)
	{
		Diretorio diretorio = diretorioService.criarDiretorio();
		ExercicioPadrao exercicioPadrao = exercicioPadraoDAO.buscar(programacaoPost.getAssunto(), nivel);

		if(feed)
		{
			InstagramFeed2 gerarLatex = new InstagramFeed2(diretorio);
			gerarLatex.gerarPDFExercicio(exercicioPadrao, programacaoPost);
			gerarLatex.gerarPDF();
			gerarLatex.convertPNG();
		}
		else
		{
			TikTok2 gerarLatex = new TikTok2(diretorio);
			gerarLatex.gerarPDFExercicio(exercicioPadrao, programacaoPost);
			gerarLatex.gerarPDF();
			gerarLatex.convertPNG();
		}

		try
		{
			imagemPostService.gravarTeste(feed, diretorio, configPostBean.getConfigPost());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		diretorioService.freeDiretorio(diretorio);
	}
}
