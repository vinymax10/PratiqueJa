package bean.instagram;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

import auxiliar.ColorHolder;
import bean.download.Diretorio;
import bean.download.PoolNomesBean;
import bean.util.Mensagem;
import dao.exercicio.ExercicioPadraoDAO;
import dao.instagram.ImagemPostDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.exercicio.ExercicioPadrao;
import modelo.exercicio.Nivel;
import modelo.instagram.ConfigPost;
import modelo.instagram.ImagemPost;
import modelo.instagram.ProgramacaoPost;
import pdf.latex.InstagramFeed;
import pdf.latex.TikTok;

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
	private  ImagemPostBean imagemPostBean;
	
	@Inject
	private ImagemPostDAO imagemPostDAO;
	
	@Inject
	private PoolNomesBean poolNomesBean;
	private Nivel nivel;

	@Inject
	private ExercicioPadraoDAO exercicioPadraoDAO;
	
	private boolean validacao()
	{
		ConfigPost configPost=configPostBean.getConfigPost();
		if(configPost.getLogo()==null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Por favor adicione uma logomarca em configurações para realizar o teste.");
			return false;			
		}
	
		if(configPost.getNome()==null||configPost.getNome().equals(""))
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
			ConfigPost configPost=configPostBean.getConfigPost();
			programacaoPost.setConfigPost(configPost);
			programacaoPost.setTeste(true);
			setImagemPost(programacaoPost);
			
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
	
	private void setImagemPost(ProgramacaoPost programacaoPost)
	{
		if(programacaoPost.isBackgroundAleatorioFeed())
		{
			if(programacaoPost.isBasePadraoFeed())
				programacaoPost.setPadraoFeed(imagemPostBean.aleatorioPadraoFeed());
			else
				programacaoPost.setBackgroundFeed(imagemPostBean.aleatorioImagemPostFeed());
		}
		
		if(programacaoPost.isBackgroundAleatorioReel())
		{
			if(programacaoPost.isBasePadraoReel())
				programacaoPost.setPadraoReel(imagemPostBean.aleatorioPadraoReel());
			else
				programacaoPost.setBackgroundReel(imagemPostBean.aleatorioImagemPostReel());
		}
	}
	
	public void gerarConteudoFeed()
	{
		Diretorio diretorio = poolNomesBean.criarDiretorio();
		InstagramFeed gerarLatex=new InstagramFeed(diretorio);
		ExercicioPadrao exercicioPadrao=exercicioPadraoDAO.buscar(programacaoPost.getAssuntoCurso(), nivel);
		
		gerarLatex.gerarPDFExercicio(exercicioPadrao,programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		gravarArquivo(true,diretorio);
		
		poolNomesBean.freeDiretorio(diretorio);
	}
	
	public void gerarConteudoReel()
	{
		Diretorio diretorio = poolNomesBean.criarDiretorio();
		TikTok gerarLatex=new TikTok(diretorio);
		ExercicioPadrao exercicioPadrao=exercicioPadraoDAO.buscar(programacaoPost.getAssuntoCurso(), nivel);
		
		gerarLatex.gerarPDFExercicio(exercicioPadrao,programacaoPost);
		gerarLatex.gerarPDF();
		gerarLatex.convertPNG();

		gravarArquivo(false,diretorio);
		
		poolNomesBean.freeDiretorio(diretorio);
	}
	
	private void gravarArquivo(boolean feed,Diretorio diretorio)
	{
		ConfigPost configPost=configPostBean.getConfigPost();
		long id=configPost.getUsuario().getId();

		try
		{
			String endBase=diretorio.getEndBackgroundServidor();
			String endRel;
			if(feed)
				endRel="/background/"+id+"/teste/feed";
			else
				endRel="/background/"+id+"/teste/reel";
			
			File theDir = new File(endBase+endRel);
			if(!theDir.exists())
				theDir.mkdirs();
			
//			-----------Exercicio-------------
			String endExercicio=endBase+endRel+"/Exercicio.png";
			String endResolucao=endBase+endRel+"/Resolucao.png";

			File origem = new File(diretorio.getEnderecoExercicioPNG());
			File destino = new File(endExercicio);
			if(destino.exists())
				destino.delete();
			
			Files.copy(origem.toPath(), destino.toPath());
			
			ImagemPost imagem=null;
			if(feed&&configPost.getTesteExeFeed()!=null)
				imagem = configPost.getTesteExeFeed();
			else if(!feed&&configPost.getTesteExeReel()!=null)
				imagem = configPost.getTesteExeReel();
			
			if(imagem==null)
				imagem = new ImagemPost();
			
			imagem.setEndImagem(endRel+"/Exercicio.png");
			imagem.setNome("Exercicio.png");
			imagem.setFeed(feed);
			imagemPostDAO.salvar(imagem);
			
			if(feed)
				configPost.setTesteExeFeed(imagem);
			else
				configPost.setTesteExeReel(imagem);
			
//			-----------Resolucao-------------
			origem = new File(diretorio.getEnderecoResolucaoPNG());
			destino = new File(endResolucao);
			if(destino.exists())
				destino.delete();
			
			Files.copy(origem.toPath(), destino.toPath());

			imagem=null;
			if(feed&&configPost.getTesteResFeed()!=null)
				imagem = configPost.getTesteResFeed();
			else if(!feed&&configPost.getTesteResReel()!=null)
				imagem = configPost.getTesteResReel();
			
			if(imagem==null)
				imagem = new ImagemPost();
			
			imagem.setEndImagem(endRel+"/Resolucao.png");
			imagem.setNome("Resolucao.png");
			imagem.setFeed(true);
			imagemPostDAO.salvar(imagem);
			
			if(feed)
				configPost.setTesteResFeed(imagem);
			else
				configPost.setTesteResReel(imagem);
			
			configPost=configPostBean.getConfigPostDAO().salvar(configPost);

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void init()
	{
//		carregamentoInicial();
//		todosTestesPadroes = programacaoPostDAO.listaTodos();
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