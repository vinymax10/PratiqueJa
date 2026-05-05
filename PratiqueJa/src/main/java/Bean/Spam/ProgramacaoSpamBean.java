package Bean.Spam;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ReorderEvent;

import Bean.AssuntoCurso.AssuntoCursoBean;
import Bean.Instagram.EnvioPostBean;
import DAO.Spam.ProgramacaoSpamDAO;
import Infra.Mensagem;
import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.Spam.ConfigSpam;
import Modelo.Spam.ProgramacaoSpam;

@Named
@ViewScoped
public class ProgramacaoSpamBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private ProgramacaoSpamDAO programacaoSpamDAO;

	@Inject
	private ConfigSpamBean configSpamBean;

	private ProgramacaoSpam programacaoSpam;

	private String nome = "Programação de Spam";

	@Inject
	private AssuntoCursoBean assuntoCursoBean;
	
	private int quantidade=1;
	
	private int ordem;

	@Inject
	private EnvioPostBean envioPostBean;
	
	public String cadastrar()
	{
		programacaoSpam = new ProgramacaoSpam();
		return "";
	}

	public String adicionar()
	{
		ProgramacaoSpam programacaoSpamNovo; 
		for(int i = 0; i < quantidade; i++)
		{
			programacaoSpamNovo = programacaoSpam.clone();
			programacaoSpamNovo.setConfigSpam(configSpamBean.getConfigSpam());
			programacaoSpamNovo.setOrdem(ordem+i);
			programacaoSpamDAO.salvar(programacaoSpamNovo);
			configSpamBean.getConfigSpam().getProgramacoesSpam().add(ordem+i,programacaoSpamNovo);
			organizarOrdem();
		}
		
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");

		return "";
	}
	
	private void organizarOrdem()
	{
		List<ProgramacaoSpam> programacoesSpam=configSpamBean.getConfigSpam().getProgramacoesSpam();
		ProgramacaoSpam programacaoSpam;
		for(int i = 0; i < programacoesSpam.size(); i++)
		{
			programacaoSpam=programacoesSpam.get(i);
			programacaoSpam.setOrdem(i);
			programacaoSpam.updateData();
			programacaoSpamDAO.salvar(programacaoSpam);
		}
	}
	
	public void onRowReorder(ReorderEvent event)
	{
		organizarOrdem();
	}
	
	public void salvar(ProgramacaoSpam programacaoSpam)
	{
		ConfigSpam configSpam=programacaoSpam.getConfigSpam();
		try
		{
			programacaoSpam=programacaoSpamDAO.salvar(programacaoSpam);
			configSpam.getProgramacoesSpam().remove(programacaoSpam);
			configSpam.getProgramacoesSpam().add(programacaoSpam.getOrdem(),programacaoSpam);

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
			programacaoSpam=programacaoSpamDAO.salvar(programacaoSpam);
			configSpamBean.getConfigSpam().getProgramacoesSpam().remove(programacaoSpam);
			configSpamBean.getConfigSpam().getProgramacoesSpam().add(programacaoSpam.getOrdem(),programacaoSpam);

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

	public String remover(ProgramacaoSpam programacaoSpam)
	{
		try
		{
			configSpamBean.getConfigSpam().getProgramacoesSpam().remove(programacaoSpam);

			programacaoSpamDAO.remover(programacaoSpam);
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
		List<AssuntoCurso> assuntos=assuntoCursoBean.getTodosAssuntoCursos();
		AssuntoCurso assuntoCurso;
		for(int i = 0; i < assuntos.size(); i++)
		{
			assuntoCurso=assuntos.get(i);
			programacaoSpam = new ProgramacaoSpam();
			programacaoSpam.setOrdem(i);
			programacaoSpam.setConfigSpam(configSpamBean.getConfigSpam());
			programacaoSpam.updateData();
			programacaoSpam.setAssuntoCurso(assuntoCurso);
			programacaoSpamDAO.salvar(programacaoSpam);
			configSpamBean.getConfigSpam().getProgramacoesSpam().add(i,programacaoSpam);
		}
		
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Programação Default realizada com sucesso.");

		return "";
	}
	
	private void removerTodos()
	{
		while(configSpamBean.getConfigSpam().getProgramacoesSpam().size()>0)
		{
			programacaoSpam=configSpamBean.getConfigSpam().getProgramacoesSpam().get(0);
			programacaoSpamDAO.remover(programacaoSpam);
			configSpamBean.getConfigSpam().getProgramacoesSpam().remove(0);
		}
	}
	
	public String removerTodosAcao()
	{
		removerTodos();
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Remoção de todas as Programaões realizada com sucesso.");

		return "";
	}
	
	public ProgramacaoSpam programacaoSpamDefault()
	{
		ProgramacaoSpam programacaoSpam=new ProgramacaoSpam();
		programacaoSpam.setConfigSpam(configSpamBean.getConfigSpam());
		programacaoSpam.setData(LocalDate.now());
		return programacaoSpam;
	}
	
	public void gerarConteudo(AssuntoCurso assuntoCurso)
	{
		ProgramacaoSpam programacaoSpam=programacaoSpamDefault();
		programacaoSpam.setAssuntoCurso(assuntoCurso);
		programacaoSpam=programacaoSpamDAO.salvar(programacaoSpam);
		envioPostBean.acorda();
	}
	
	@PostConstruct
	public void init()
	{
//		carregamentoInicial();
//		todosTestesPadroes = programacaoSpamDAO.listaTodos();
	}

	public ProgramacaoSpam getProgramacaoSpam()
	{
		return programacaoSpam;
	}

	public void setProgramacaoSpam(ProgramacaoSpam ProgramacaoSpam)
	{
		this.programacaoSpam = ProgramacaoSpam;
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