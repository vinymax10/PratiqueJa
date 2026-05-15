package bean.publicacao;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.event.ReorderEvent;

import modelo.academico.AssuntoCurso;
import modelo.publicacao.ConfigPost;
import modelo.publicacao.ProgramacaoPost;
import bean.util.Mensagem;
import service.ProgramacaoPostService;

@Named
@ViewScoped
public class ProgramacaoPostBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ConfigPostBean configPostBean;

	private ProgramacaoPost programacaoPost;

	private String nome = "Programação de Post";

	private int quantidade = 1;

	private int ordem;

	@Inject
	private EnvioPostBean envioPostBean;

	@Inject
	private ProgramacaoPostService programacaoPostService;

	public String cadastrar()
	{
		programacaoPost = new ProgramacaoPost();
		return "";
	}

	public String adicionar()
	{
		for(int i = 0; i < quantidade; i++)
		{
			ProgramacaoPost novo = programacaoPost.clone();
			novo.setConfigPost(configPostBean.getConfigPost());
			novo.setOrdem(ordem + i);
			programacaoPostService.adicionar(novo);
		}

		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");

		return "";
	}

	public void onRowReorder(ReorderEvent event)
	{
		programacaoPostService.organizarOrdem(configPostBean.getConfigPost());
	}

	public void salvar(ProgramacaoPost programacaoPost)
	{
		try
		{
			programacaoPostService.setImagemPost(programacaoPost);
			programacaoPostService.salvar(programacaoPost);
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
			programacaoPostService.setImagemPost(programacaoPost);
			programacaoPost = programacaoPostService.salvar(programacaoPost);
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
			programacaoPostService.remover(programacaoPost);
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
		programacaoPostService.programacaoDefault(configPostBean.getConfigPost());
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Programação Default realizada com sucesso.");

		return "";
	}

	public String removerTodosAcao()
	{
		programacaoPostService.removerTodos(configPostBean.getConfigPost());
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Remoção de todas as Programações realizada com sucesso.");

		return "";
	}

	public ProgramacaoPost programacaoPostDefault()
	{
		ProgramacaoPost programacaoPost = new ProgramacaoPost();
		programacaoPost.setConfigPost(configPostBean.getConfigPost());
		programacaoPost.setData(LocalDate.now());
		programacaoPostService.setImagemPost(programacaoPost);
		return programacaoPost;
	}

	public void gerarConteudo(AssuntoCurso assuntoCurso)
	{
		ProgramacaoPost programacaoPost = programacaoPostDefault();
		programacaoPost.setAssuntoCurso(assuntoCurso);
		programacaoPost.setAvulsa(true);
		programacaoPostService.persistir(programacaoPost);
		envioPostBean.acorda();
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

	public ConfigPost getConfigPost()
	{
		return configPostBean.getConfigPost();
	}
}
