package bean.instagram;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.event.ReorderEvent;

import dao.instagram.ProgramacaoPostDAO;
import modelo.academico.AssuntoCurso;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import bean.assuntocurso.AssuntoCursoBean;
import bean.util.Mensagem;

@Named
@ViewScoped
public class ProgramacaoPostBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ProgramacaoPostDAO programacaoPostDAO;

	@Inject
	private ConfigPostBean configPostBean;

	private ProgramacaoPost programacaoPost;

	private String nome = "Programação de Post";

	@Inject
	private AssuntoCursoBean assuntoCursoBean;
	
	private int quantidade=1;
	
	private int ordem;

	@Inject
	private EnvioPostBean envioPostBean;
	
	@Inject
	private  ImagemPostBean imagemPostBean;
	
	public String cadastrar()
	{
		programacaoPost = new ProgramacaoPost();
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
	
	public String adicionar()
	{
		ProgramacaoPost programacaoPostNovo; 
		for(int i = 0; i < quantidade; i++)
		{
			programacaoPostNovo = programacaoPost.clone();
			programacaoPostNovo.setConfigPost(configPostBean.getConfigPost());
			programacaoPostNovo.setOrdem(ordem+i);
			setImagemPost(programacaoPostNovo);
			programacaoPostDAO.salvar(programacaoPostNovo);
			configPostBean.getConfigPost().getProgramacoesPost().add(ordem+i,programacaoPostNovo);
			organizarOrdem();
		}
		
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");

		return "";
	}
	
	private void organizarOrdem()
	{
		List<ProgramacaoPost> programacoesPost=configPostBean.getConfigPost().getProgramacoesPost();
		ProgramacaoPost programacaoPost;
		for(int i = 0; i < programacoesPost.size(); i++)
		{
			programacaoPost=programacoesPost.get(i);
			programacaoPost.setOrdem(i);
			programacaoPost.updateData();
			setImagemPost(programacaoPost);
			programacaoPostDAO.salvar(programacaoPost);
		}
	}
	
	public void onRowReorder(ReorderEvent event)
	{
		organizarOrdem();
	}
	
	public void salvar(ProgramacaoPost programacaoPost)
	{
		ConfigPost configPost=programacaoPost.getConfigPost();
		try
		{
			setImagemPost(programacaoPost);
			programacaoPost=programacaoPostDAO.salvar(programacaoPost);
			configPost.getProgramacoesPost().remove(programacaoPost);
			configPost.getProgramacoesPost().add(programacaoPost.getOrdem(),programacaoPost);

			organizarOrdem();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String salvar()
	{
		try
		{
			setImagemPost(programacaoPost);
			programacaoPost=programacaoPostDAO.salvar(programacaoPost);
			configPostBean.getConfigPost().getProgramacoesPost().remove(programacaoPost);
			configPostBean.getConfigPost().getProgramacoesPost().add(programacaoPost.getOrdem(),programacaoPost);

			organizarOrdem();

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}
		return "";
	}

	public String remover(ProgramacaoPost programacaoPost)
	{
		try
		{
			configPostBean.getConfigPost().getProgramacoesPost().remove(programacaoPost);

			programacaoPostDAO.remover(programacaoPost);
			organizarOrdem();

			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
	}
	
	public String programacaoDefault()
	{
		removerTodos();
		List<AssuntoCurso> assuntos=assuntoCursoBean.getListaAtivas();
		AssuntoCurso assuntoCurso;
		for(int i = 0; i < assuntos.size(); i++)
		{
			assuntoCurso=assuntos.get(i);
			programacaoPost = new ProgramacaoPost();
			programacaoPost.setOrdem(i);
			programacaoPost.setConfigPost(configPostBean.getConfigPost());
			programacaoPost.updateData();
			programacaoPost.setAssuntoCurso(assuntoCurso);
			setImagemPost(programacaoPost);
			programacaoPostDAO.salvar(programacaoPost);
			configPostBean.getConfigPost().getProgramacoesPost().add(i,programacaoPost);
		}
		
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Programação Default realizada com sucesso.");

		return "";
	}
	
	private void removerTodos()
	{
		while(configPostBean.getConfigPost().getProgramacoesPost().size()>0)
		{
			programacaoPost=configPostBean.getConfigPost().getProgramacoesPost().get(0);
			programacaoPostDAO.remover(programacaoPost);
			configPostBean.getConfigPost().getProgramacoesPost().remove(0);
		}
	}
	
	public String removerTodosAcao()
	{
		removerTodos();
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Remoção de todas as Programaões realizada com sucesso.");

		return "";
	}
	
	public ProgramacaoPost programacaoPostDefault()
	{
		ProgramacaoPost programacaoPost=new ProgramacaoPost();
		programacaoPost.setConfigPost(configPostBean.getConfigPost());
		programacaoPost.setData(LocalDate.now());
		setImagemPost(programacaoPost);
		return programacaoPost;
	}
	
	public void gerarConteudo(AssuntoCurso assuntoCurso)
	{
		ProgramacaoPost programacaoPost=programacaoPostDefault();
		programacaoPost.setAssuntoCurso(assuntoCurso);
		programacaoPost.setAvulsa(true);
		programacaoPost=programacaoPostDAO.salvar(programacaoPost);
		envioPostBean.acorda();
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

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public int getQuantidade()
	{
		return quantidade;
	}

	public void setQuantidade(int quantidade)
	{
		this.quantidade = quantidade;
	}

	public int getOrdem()
	{
		return ordem;
	}

	public void setOrdem(int ordem)
	{
		this.ordem = ordem;
	}
}