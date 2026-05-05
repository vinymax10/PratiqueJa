package Bean.Email;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.push.Push;
import jakarta.faces.push.PushContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.omnifaces.cdi.Startup;

import DAO.EmailDAO;
import Modelo.DocumentoFile;
import Modelo.Email;

@Startup
@Named
@ApplicationScoped
public class EmailBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private EmailDAO emailDAO;

	private List<Email> emails = new ArrayList<Email>();
	
	@Inject 
	@Push(channel = "email")
    private PushContext push;
	
	private String emailPessoal="vinymax10@gmail.com"; 

	private String listDestinatarios=""; 
	
	private String assunto=""; 

	private String mensagem=""; 
	
	private boolean lista = true;
	private boolean cadastro = true;
	private int tempoEspera=60;

	private static Thread thread;
	
	@Inject 
	private Email email;
	
	public String cadastrar()
	{
		cadastro = true;
		lista = false;
		listDestinatarios=""; 
		assunto=""; 
		mensagem=""; 
		return "";
	}

	public String adicionar()
	{
		String destinatarios[]=listDestinatarios.split(",");
		for(String destinatario : destinatarios)
		{
			email = new Email();
			email.setDestinatario(destinatario);
			email.setAssunto(assunto);
			email.setMensagem(mensagem);
			email.setTempoEspera(tempoEspera);
			adicionar(email);
		}
		
		cadastro = false;
		lista = true;
		
		return "";
	}
	
	public void adicionar(String destinatario, String subject, String msg, int tempoEspera)
	{
		Email email = new Email();
		email.setDestinatario(destinatario);
		email.setAssunto(subject);
		email.setMensagem(msg);
		email.setTempoEspera(tempoEspera);
		adicionar(email);
	}
	
	public void adicionar(String destinatario, String subject, String msg, List<DocumentoFile> documentosFile, int tempoEspera)
	{
		Email email = new Email();
		email.setDestinatario(destinatario);
		email.setAssunto(subject);
		email.setMensagem(msg);
		email.setDocumentosFile(documentosFile);
		email.setTempoEspera(tempoEspera);

		adicionar(email);
	}
	
	public String adicionar(Email email)
	{
		System.out.println("adicionar Email");
		try
		{
			emailDAO.salvar(email);
			emails.add(email);

			push.send("updateEmail");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	public String salvar(Email email)
	{
		try
		{
			email=emailDAO.salvar(email);
			push.send("updateEmail");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public String removerTeste()
	{

		remover(emails.get(0));
		return "";
	}

	public String remover(Email email)
	{
		try
		{
			if(emails.contains(email))
				emails.remove(email);

			emailDAO.remover(email);
			push.send("updateEmail");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return "";
	}

	@PostConstruct
	public void init()
	{
		this.emails = emailDAO.listarTudo();
		EnvioEmail envioEmail=new EnvioEmail(this);
		thread = getInstance(envioEmail);
		if(!thread.isAlive())
			thread.start();
	}
	
	public static Thread getInstance(EnvioEmail envioEmail)
	{
		if(thread == null)
			thread = new Thread(envioEmail);

		return thread;
	}

	public List<Email> getEmails()
	{
		return emails;
	}

	public String getEmailPessoal()
	{
		return emailPessoal;
	}

	public void setEmails(List<Email> emails)
	{
		this.emails = emails;
	}

	public boolean isLista()
	{
		return lista;
	}

	public void setLista(boolean lista)
	{
		this.lista = lista;
	}

	public String getListDestinatarios()
	{
		return listDestinatarios;
	}

	public Email getEmail()
	{
		return email;
	}

	public void setEmail(Email email)
	{
		this.email = email;
	}

	public void setListDestinatarios(String listDestinatarios)
	{
		this.listDestinatarios = listDestinatarios;
	}

	public void setAssunto(String assunto)
	{
		this.assunto = assunto;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	public String getAssunto()
	{
		return assunto;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public boolean isCadastro()
	{
		return cadastro;
	}

	public void setCadastro(boolean cadastro)
	{
		this.cadastro = cadastro;
	}

	public int getTempoEspera()
	{
		return tempoEspera;
	}

	public void setTempoEspera(int tempoEspera)
	{
		this.tempoEspera = tempoEspera;
	}
	
}
