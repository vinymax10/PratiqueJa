package bean.teste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import dao.teste.TesteDAO;
import infra.Navegacao;
import service.TesteService;
import modelo.assuntocurso.AssuntoCurso;
import modelo.teste.Teste;
import modelo.usuario.Turma;
import modelo.usuario.Usuario;
import web.session.SessionContext;
import filtro.teste.FiltroTeste;
import bean.usuario.ControleAcessoBean;
import bean.util.Mensagem;

@Named
@SessionScoped
public class TesteBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private TesteDAO testeDAO;
	
	@Inject
	private Teste teste;

	private String nome = "Teste";
	private boolean lista = true;
	private boolean cadastro = false;

	private List<Teste> testes = new ArrayList<Teste>();

	private int activeIndex;

	@Inject
	private FiltroTeste filtroTeste;

	@Inject
	private ControleAcessoBean controleAcessoBean;

	@Inject
	private TesteService testeService;

	public String cadastrar()
	{
		activeIndex = 0;
		cadastro = true;
		lista = false;
		teste = new Teste();
		return "";
	}
	
	public String criarTesteTurma(Turma turma)
	{
		for (Usuario usuario : turma.getAlunos())
		{
			teste = new Teste();
			teste.setRepetirAtePassar(true);
			teste.setTestePadrao(turma.getAssuntoAtual().getTestePadrao());
			teste.setUsuario(usuario);
			
			construirTeste();
		}
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " foi criado para cada aluno da turma.");

		return "";
	}
	
	public String criarTeste(AssuntoCurso assuntoCurso)
	{
		if(controleAcessoBean.verificaEstaLogado())
		{
			teste = new Teste();
			teste.setTestePadrao(assuntoCurso.getTestePadrao());
			Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
			teste.setUsuario(usuario);
			
			construirTeste();
			
			Navegacao.redirect("/teste/teste/verTeste.xhtml?id="+teste.getId());
		}
		return "";
	}
	
	private void construirTeste()
	{
		try
		{
			testeService.construirTeste(teste);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String adicionar()
	{
		try
		{
			construirTeste();
			cadastro = false;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");
		}
		catch(IllegalArgumentException | SecurityException e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar a " + nome + ". Tipo de exercício não encontrado.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar a " + nome);
		}

		return "";
	}

	public String salvar()
	{
		try
		{
			teste=testeDAO.salvar(teste);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " salvo com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível salvar a " + nome);
		}
		return "";
	}
	
	public String remover()
	{
		try
		{
			testeDAO.remover(teste);
			testes.remove(teste);
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " removida com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível remover a " + nome);
		}
		return "";
	}

	public void filtrar()
	{
		this.testes = testeDAO.buscar(filtroTeste);
	}
	
	public void onSelected()
	{
		activeIndex = 0;
		cadastro = false;
		lista = false;
	}

	public List<Teste> getMeusTestes(Boolean realizado)
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		return testeDAO.meusTestes(usuario, realizado);
	}
	
	public Long numeroMeusTestes(Boolean realizado)
	{
		Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
		return testeDAO.numeroMeusTestes(usuario, realizado);
	}

	public Teste getTeste()
	{
		return teste;
	}

	public void setTeste(Teste teste)
	{
		this.teste = teste;
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

	public List<Teste> getTestes()
	{
		return testes;
	}

	public void setTestes(List<Teste> testes)
	{
		this.testes = testes;
	}

	public int getActiveIndex()
	{
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public FiltroTeste getFiltroTeste()
	{
		return filtroTeste;
	}

	public void setFiltroTeste(FiltroTeste filtroTeste)
	{
		this.filtroTeste = filtroTeste;
	}

}