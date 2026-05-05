package Bean.Usuario;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.rowset.serial.SerialBlob;

import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import Auxiliar.Graphics;
import Bean.Instagram.ConfigPostBean;
import Bean.Usuario.Filtro.FiltroUsuario;
import DAO.Usuario.UsuarioDAO;
import Infra.Mensagem;
import Modelo.Instagram.ConfigPost;
import Modelo.Usuario.Imagem;
import Modelo.Usuario.Usuario;

@Named
@SessionScoped
public class UsuarioBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private Usuario usuario;

	private String nome = "Usuário";
	private boolean lista = true;
	private boolean cadastro = false;
	private String senha;
	private String confirmaSenha;
	private int activeIndex;
	private int activeIndexGrafico;
	
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@Inject
	private FiltroUsuario filtroUsuario;

	@Inject
	private ConfigPostBean configPostBean;
	
	public String cadastrar(boolean externo)
	{
		cadastro = true;
		lista = false;
		usuario = new Usuario();
		senha = "";
		confirmaSenha = "";
		if(externo)
			PrimeFaces.current().executeScript("PF('cadastroWidget').show()");

		return "";
	}

	public String editar(Usuario usuario)
	{
		cadastro = false;
		this.usuario=usuario;
		senha = "";
		confirmaSenha = "";

		return "";
	}
	
	public String adicionar(boolean externo)
	{
		try
		{
			usuario.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt(12)));
			usuarioDAO.salvar(usuario);
			Mensagem.send("growl", FacesMessage.SEVERITY_INFO, nome + " adicionado com sucesso.");

			if(externo)
				PrimeFaces.current().executeScript("PF('cadastroWidget').hide()");

			lista = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível adicionar o " + nome);
		}
		return "";
	}

	public String salvar()
	{
		try
		{
			if(usuario.isCriador()&&usuario.getConfigPost()==null)
			{
				ConfigPost configPost = configPostBean.cadastrar(usuario);
				usuario.setConfigPost(configPost);
			}
			
			usuario=usuarioDAO.salvar(usuario);
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
			usuarioDAO.remover(usuario);
			usuarios.remove(usuario);
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

	public void onSelected()
	{
		cadastro = false;
		lista = false;
	}

	public String cancelar()
	{
		lista = true;
		return "";
	}

	public void filtrar()
	{
		this.usuarios = usuarioDAO.buscar(filtroUsuario);
	}

	public void uploadFile(FileUploadEvent event)
	{
		UploadedFile file = event.getFile();
		try
		{
			SerialBlob serialBlob = new SerialBlob(Graphics.shapeImage(file, 400, 400));
			Imagem imagem = new Imagem();
			imagem.setFile(serialBlob);
			imagem.setEndereco(file.getFileName());
			usuario.setImagem(imagem);

		}
		catch(SQLException | IOException e)
		{
			e.printStackTrace();
		}
		
	}

	public void validateEmail(FacesContext context, UIComponent component, Object email)
	{
		Usuario usuariosBanco = usuarioDAO.getUsuario((String) email);
		if(this.usuario.getId() == null && usuariosBanco != null
		|| (this.usuario.getId() != null && usuariosBanco != null && !this.usuario.getId().equals(usuariosBanco.getId())))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"" , "Email já cadastrado.");
			throw new ValidatorException(msg);
		}
	}

//	public void compressImage(BufferedImage bufferedImage, OutputStream output, int quality) throws IOException
//	{
//	if (quality <= 0 || quality > 100)
//	{
//	   throw new IllegalArgumentException("quality not in 1-100");
//	}
//	ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
//	   try
//	   {
//	       ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
//	       jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//	       jpgWriteParam.setCompressionQuality(quality * 0.01f);
//	       try (ImageOutputStream ios = ImageIO.createImageOutputStream(output))
//	       {
//	           jpgWriter.setOutput(ios);
//	           IIOImage outputImage = new IIOImage(bufferedImage, null, null);
//	           jpgWriter.write(null, outputImage, jpgWriteParam);
//	       }
//	   }
//	   finally
//	   {
//	       jpgWriter.dispose();
//	   }
//	}

//	private BufferedImage redonda(BufferedImage bufferedImage)
//	{
//		int t = Math.min(bufferedImage.getWidth(), bufferedImage.getHeight());
//		BufferedImage other = new BufferedImage(t, t, bufferedImage.getType());
//		
//		//Obtém o contexto gráfico
//		Graphics2D g2d = other.createGraphics();		
//		//Define a área de pintura para um círculo		
//		g2d.setClip(new Ellipse2D.Double(0, 0, t, t));
//		//Desenha a imagem
//		g2d.drawImage(bufferedImage, 0, 0, null);		
//		//Libera o contexto gráfico
//		g2d.dispose();
//		return other;
//	}

	public List<Usuario> getTodosUsuarios()
	{
		return usuarioDAO.listaTudo();
	}

	public String gerarSenhaAleatoria()
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

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
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

	public void setUsuarios(List<Usuario> usuarios)
	{
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuarios()
	{
		return usuarios;
	}

	public FiltroUsuario getFiltroUsuario() {
		return filtroUsuario;
	}

	public void setFiltroUsuario(FiltroUsuario filtroUsuario) {
		this.filtroUsuario = filtroUsuario;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public String getConfirmaSenha()
	{
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha)
	{
		this.confirmaSenha = confirmaSenha;
	}

	public int getActiveIndex()
	{
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex)
	{
		this.activeIndex = activeIndex;
	}

	public int getActiveIndexGrafico()
	{
		return activeIndexGrafico;
	}

	public void setActiveIndexGrafico(int activeIndexGrafico)
	{
		this.activeIndexGrafico = activeIndexGrafico;
	}

	public UsuarioDAO getUsuarioDAO()
	{
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO)
	{
		this.usuarioDAO = usuarioDAO;
	}

}
