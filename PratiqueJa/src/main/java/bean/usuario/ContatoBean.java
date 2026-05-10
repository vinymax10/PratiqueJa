package bean.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import util.StringAux;
import filtro.usuario.FiltroContato;
import bean.util.Mensagem;
import dao.usuario.ContatoDAO;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.usuario.Contato;
import modelo.usuario.Usuario;
import service.EmailService;
import web.session.SessionContext;

@Named
@ViewScoped
public class ContatoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	String nome = "Contato";

	@Inject
	private Contato contato;

	private boolean lista = true;
	private boolean cadastro = false;
	private int activeIndex;

	@Inject
	private ContatoDAO contatoDAO;

	private List<Contato> contatos = new ArrayList<Contato>();

	@Inject
	private FiltroContato filtroContato;

	@Inject
	private EmailService emailService;
	
	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		contato = new Contato();
		return "";
	}

	public String adicionar()
	{
		try
		{
			Usuario usuario = (Usuario) SessionContext.getInstance().getAttribute("UsuarioLogado");
			contato.setUsuario(usuario);
			if(usuario!=null)
			{
				contato.setNomeUsuario(usuario.getNome());
				contato.setEmail(usuario.getEmail());
			}
			
			contato.setData(LocalDate.now());
			contatoDAO.salvar(contato);
			avisarAddContato();

			contato = new Contato();
			lista = true;
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o " + nome);
		}
		return "";
	}
	
	private void avisarAddContato()
	{
		String assunto = "Contato recebido";
		String mensagem = "Contato recebido\n\n"
		+ "Nome: "+contato.getNomeUsuario()+"\n"
		+ "Assunto: "+contato.getAssunto()+"\n"
		+ "Mensagem: "+contato.getMensagem()+"\n"
		+ "Data: "+StringAux.getDataStr(contato.getData())+"\n"
		+ "Email: "+contato.getEmail()+"\n";

		emailService.adicionar("vinymax10@gmail.com", assunto, mensagem);
	}

	public String salvar()
	{
		try
		{
			contato=contatoDAO.salvar(contato);
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
			if(contatos.contains(contato))
				contatos.remove(contato);
			
			contatoDAO.remover(contato);

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

	public String cancelar()
	{
		lista = true;
		return "";
	}

	public void filtrar()
	{
		this.contatos = contatoDAO.buscar(filtroContato);
	}

	public void onSelected()
	{
		cadastro = false;
		lista = false;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Contato getContato()
	{
		return contato;
	}

	public void setContato(Contato contato)
	{
		this.contato = contato;
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

	public List<Contato> getContatos()
	{
		return contatos;
	}

	public void setContatos(List<Contato> contatos)
	{
		this.contatos = contatos;
	}

	public FiltroContato getFiltroContato()
	{
		return filtroContato;
	}

	public void setFiltroContato(FiltroContato filtroContato)
	{
		this.filtroContato = filtroContato;
	}

	public int getActiveIndex()
	{
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
	}

}