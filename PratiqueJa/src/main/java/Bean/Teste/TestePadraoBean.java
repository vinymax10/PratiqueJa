package Bean.Teste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import Bean.Teste.Filtro.FiltroTestePadrao;
import DAO.AssuntoCursoDAO;
import DAO.Teste.ConteudoTesteDAO;
import DAO.Teste.TestePadraoDAO;
import Infra.Mensagem;
import Modelo.AssuntoCurso.AssuntoCurso;
import Modelo.Exercicio.ExercicioPadrao;
import Modelo.Teste.ConteudoTeste;
import Modelo.Teste.TestePadrao;

@Named
@ViewScoped
public class TestePadraoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private TestePadraoDAO testePadraoDAO;

	@Inject
	private TestePadrao testePadrao;

	private String nome = "Teste Padrão";
	private boolean lista = true;
	private boolean cadastro = false;

	private List<TestePadrao> testesPadroes = new ArrayList<TestePadrao>();
	
	private List<TestePadrao> todosTestesPadroes = new ArrayList<TestePadrao>();

	@Inject
	private FiltroTestePadrao filtroTestePadrao;

	@Inject
	private AssuntoCursoDAO assuntoCursoDAO;
	
	@Inject
	private ConteudoTesteDAO conteudoTesteDAO;
	
	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		testePadrao = new TestePadrao();
		return "";
	}

	public String adicionar()
	{
		testePadraoDAO.salvar(testePadrao);
		cadastro = false;
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");

		return "";
	}

	public String salvar()
	{
		try
		{
			testePadrao=testePadraoDAO.salvar(testePadrao);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar o " + nome);
		}
		return "";
	}

	public String remover()
	{
		try
		{
			testePadraoDAO.remover(testePadrao);
			testesPadroes.remove(testePadrao);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removido com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover o " + nome);
		}
		return "";
	}

	public void filtrar()
	{
		this.testesPadroes = testePadraoDAO.buscar(filtroTestePadrao);
	}

	public void onSelected()
	{
		cadastro = false;
		lista = false;
	}
	
//	private void carregamentoInicial()
//	{
//		List<AssuntoCurso> assuntosCurso = assuntoCursoDAO.todos();
//		for(AssuntoCurso assuntoCurso : assuntosCurso)
//		{
//			System.out.println(assuntoCurso.getNome());
//			testePadrao = new TestePadrao();
//			testePadrao.setDuracao(10);
//			testePadrao.setNome(assuntoCurso.getModulo().getNome()+" - "+assuntoCurso.getNome());
//			testePadrao.setNotaMinima(100);
//			testePadraoDAO.salvar(testePadrao);
//			
//			assuntoCurso.setTestePadrao(testePadrao);
//			assuntoCursoDAO.salvar(assuntoCurso);
//			
//			for(ExercicioPadrao exercicioPadrao : assuntoCurso.getExerciciosPadrao())
//			{
//				ConteudoTeste conteudoTeste=new ConteudoTeste();
//				conteudoTeste.setExercicioPadrao(exercicioPadrao);
//				conteudoTeste.setTestePadrao(testePadrao);
//				conteudoTeste.setQuantidade(exercicioPadrao.getQuantidade()*2/3);
//				conteudoTesteDAO.salvar(conteudoTeste);
//			}
//		}
//	}
	
	@PostConstruct
	public void init()
	{
//		carregamentoInicial();
		todosTestesPadroes = testePadraoDAO.listaTodos();
	}

	public TestePadrao getTestePadrao()
	{
		return testePadrao;
	}

	public void setTestePadrao(TestePadrao testePadrao)
	{
		this.testePadrao = testePadrao;
	}

	public boolean isLista()
	{
		return lista;
	}

	public void setLista(boolean lista)
	{
		this.lista = lista;
	}

	public boolean isCadastro()
	{
		return cadastro;
	}

	public void setCadastro(boolean cadastro)
	{
		this.cadastro = cadastro;
	}

	public List<TestePadrao> getTestesPadroes()
	{
		return testesPadroes;
	}

	public void setTestesPadroes(List<TestePadrao> testesPadroes)
	{
		this.testesPadroes = testesPadroes;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public FiltroTestePadrao getFiltroTestePadrao()
	{
		return filtroTestePadrao;
	}

	public void setFiltroTestePadrao(FiltroTestePadrao filtroTestePadrao)
	{
		this.filtroTestePadrao = filtroTestePadrao;
	}

	public List<TestePadrao> getTodosTestesPadroes()
	{
		return todosTestesPadroes;
	}

	public void setTodosTestesPadroes(List<TestePadrao> todosTestesPadroes)
	{
		this.todosTestesPadroes = todosTestesPadroes;
	}

}