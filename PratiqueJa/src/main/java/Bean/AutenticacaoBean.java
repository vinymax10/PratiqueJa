package Bean;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Random;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.imageio.ImageIO;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.sql.rowset.serial.SerialBlob;
import jakarta.validation.constraints.Pattern;

import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.PrimeFaces;

import Bean.Email.EmailBean;
import Bean.Usuario.ControleAcessoBean;
import DAO.Usuario.AcessoDAO;
import DAO.Usuario.ControleAcessoDAO;
import DAO.Usuario.UsuarioDAO;
import Infra.Mensagem;
import Modelo.Usuario.Acesso;
import Modelo.Usuario.ControleAcesso;
import Modelo.Usuario.Imagem;
import Modelo.Usuario.Usuario;
import Session.SessionContext;

@Named
@SessionScoped
public class AutenticacaoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email inválido.")
	String email;

	String senha;

	String senhaAntiga;

	String confirmacaoSenha;

	Usuario usuario;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private AcessoDAO acessoDAO;
	
	@Inject
	private ControleAcessoDAO controleAcessoDAO;
	
	@Inject
	private ControleAcessoBean controleAcessoBean;
	
	@Inject
	EmailBean emailBean;
	
	public String loginGoogle()
	{
		System.out.println("loginGoogle");
		Map<String, String> hash = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		String email = hash.get("email");
		String name = hash.get("name");
		String sub = hash.get("sub");
		String picture = hash.get("picture");

		System.out.println("email: " + email + " name: " + name + " sub: " + sub + " picture: " + picture);

		usuario = usuarioDAO.getUsuarioGoogle(sub);
		
		if(usuario != null)
		{
			SessionContext.getInstance().setAttribute("UsuarioLogado", usuario);

			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", "Efetuado com sucesso"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			salvarAcessoLogin();
			verificaControleAcesso();
			
			System.out.println("Login pelo usuario: " + usuario);
			return "";
		}
		else
		{
			usuario = usuarioDAO.getUsuario(email);
			if(usuario == null)
			{
				usuario = new Usuario();
				usuario.setEmail(email);
				usuario.setNome(name);
			}
			usuario.setSubGoogle(sub);

			SerialBlob serialBlob;
			try
			{
				serialBlob = new SerialBlob(shapeImage(picture, 400, 400));
				Imagem imagem = new Imagem();
				imagem.setFile(serialBlob);
				imagem.setEndereco("image.jpg");
				usuario.setImagem(imagem);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}

			usuarioDAO.salvar(usuario);

			SessionContext.getInstance().setAttribute("UsuarioLogado", usuario);

			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Primeiro Login efetuado com sucesso.", ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			salvarAcessoLogin();
			verificaControleAcesso();
			System.out.println("Login pelo usuario: " + usuario);

			return "";
		}
	}
	
	private void verificaControleAcesso()
	{
		ControleAcesso controleAcesso = controleAcessoDAO.controleAcessoHoje(usuario);
		if(controleAcesso==null)
		{
			controleAcesso = new ControleAcesso();
			controleAcesso.setUsuario(usuario);
			controleAcesso.limpar();
			controleAcessoDAO.salvar(controleAcesso);
		}
		
		SessionContext.getInstance().setAttribute("controleAcesso", controleAcesso);
		controleAcessoBean.carrega();
	}

	public byte[] shapeImage(String imageUrl, int targetWidth, int targetHeight)
	{
		try
		{
			URL url = new URL(imageUrl);
			BufferedImage bufferedImage = ImageIO.read(url);
			Image resultingImage = bufferedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
			BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
			outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(outputImage, "jpg", baos);
			return baos.toByteArray();

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	private void salvarAcessoLogin()
	{
		Acesso acesso = new Acesso();

		String idSessao = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
		acesso.setIdSessao(idSessao);

		acesso.setData(LocalDate.now());

		acesso.setUsuario(usuario);
		acesso.setInicioAcesso(System.currentTimeMillis());
		acessoDAO.salvar(acesso);
	}

	public void salvarAcessoLogout(String idSessao, boolean percaSessao)
	{
		Acesso acesso = acessoDAO.lastAcesso(idSessao);
		if(acesso != null)
		{
			Long fimAcesso = System.currentTimeMillis();
			int minutos = (int) ((double) (fimAcesso - acesso.getInicioAcesso()) / 60000);
			if(percaSessao)
				minutos -= 60;

			acesso.setMinutos(minutos);
			acesso.setFinalizado(true);
			acessoDAO.salvar(acesso);
		}
	}

	public String resetLogin()
	{
		senhaAntiga="";
		senha="";
		email="";
		return "";
	}
	
	private boolean validaUsuarioSenha()
	{
		usuario = usuarioDAO.getUsuario(email);
		if(usuario == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, 
			"Login", "Usuário não cadastrado");
			return false;
		}
		
		if(!BCrypt.checkpw(senha, usuario.getSenha()))
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, 
			"Login", "Usuário e senha incorretos");
			return false;
		}
		
		return true;
	}
	
	public String login()
	{
		if(validaUsuarioSenha())
		{
			usuario = usuarioDAO.getUsuario(email);
			SessionContext.getInstance().setAttribute("UsuarioLogado", usuario);

			FacesContext.getCurrentInstance().addMessage("growl", 
			new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", "Efetuado com sucesso"));
			
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			salvarAcessoLogin();
			verificaControleAcesso();
			System.out.println("Login pelo usuario: " + usuario);
		}
		return "";
	}

	public String atualizarSenha()
	{
		if(BCrypt.checkpw(senhaAntiga, usuario.getSenha()))
		{
			usuario.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt(12)));
			usuarioDAO.salvar(usuario);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, 
			"", "Senha atualizada com sucesso.");
		}
		return "";
	}

	public String recupearSenha()
	{
		System.out.println("recupearSenha: " + email);
		usuario = usuarioDAO.getUsuario(email);

		if(usuario == null)
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email", "Não cadastrado"));
		else
		{
			String novaSenha = gerarSenhaAleatoria();
			usuario.setSenha(BCrypt.hashpw(novaSenha, BCrypt.gensalt(12)));
			usuarioDAO.salvar(usuario);
			String assunto = "Recuperação da Senha de acesso ao Prática";
			String mensagem = "Foi solicitada uma recuperação de senha para o email " + email + "\nSua nova senha é: " + novaSenha
			+ "\nAcesse novamente o Prática e atualize a sua senha.";

			emailBean.adicionar(email, assunto, mensagem,1);
			
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Recuperação de Senha",
			"Um email foi enviado para " + email + " contendo a nova senha. Por favor verifique seu email e acesse novamente o nosso sistema."));

		}
		PrimeFaces.current().executeScript("PF('manageRecuperarSenhaDialog').hide()");

		return "";
	}

	private String gerarSenhaAleatoria()
	{
		String str = "";
		String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
		"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
		"U", "V", "W", "X", "Y", "Z" };
		Random rand = new Random();
		str += carct[rand.nextInt(10)];
		str += carct[10 + rand.nextInt(26)];
		str += carct[36 + rand.nextInt(26)];
		for(int i = 0; i < 7; i++)
		{
			str += carct[rand.nextInt(carct.length)];
		}
		return str;
	}

	public String logout()
	{
		String idSessao = FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
		System.out.println("logout idSessao: " + idSessao);
		salvarAcessoLogout(idSessao, false);
		SessionContext.getInstance().setAttribute("UsuarioLogado", null);
		usuario=null;
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO,"Logout", "Efetuado com sucesso");
//		SessionContext.getInstance().encerrarSessao();
		return "/matematica/painel?faces-redirect=true"; // "
	}

	public void validateEmail(FacesContext context, UIComponent component, Object email)
	{
		Usuario usuariosBanco = usuarioDAO.getUsuario((String) email);
		if(usuariosBanco == null)
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email não cadastrado.");
			throw new ValidatorException(msg);
		}
	}
	
	public void validateSenha(FacesContext context, UIComponent component, Object object)
	{
		UIInput emailInput = (UIInput) component.findComponent("formLogin:email");

		String email = (String) emailInput.getValue();
		
		Usuario usuariosBanco = usuarioDAO.getUsuario(email);
		
		String senha=(String) object;
		if(usuariosBanco != null&&!BCrypt.checkpw(senha, usuariosBanco.getSenha()))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Senha incorreta.");
			throw new ValidatorException(msg);
		}
	}
	
	public void validateSenhaAtualizacao(FacesContext context, UIComponent component, Object object)
	{
		String senha=(String) object;
		if(usuario != null&&!BCrypt.checkpw(senha, usuario.getSenha()))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Senha incorreta.");
			throw new ValidatorException(msg);
		}
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public String getConfirmacaoSenha()
	{
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha)
	{
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public String getSenhaAntiga()
	{
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga)
	{
		this.senhaAntiga = senhaAntiga;
	}

}
