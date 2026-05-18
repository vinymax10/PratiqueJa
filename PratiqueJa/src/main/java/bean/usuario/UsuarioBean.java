package bean.usuario;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import javax.sql.rowset.serial.SerialBlob;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import bean.PaiBean;
import bean.publicacao.ConfigPostBean;
import dao.usuario.UsuarioDAO;
import filtro.usuario.FiltroUsuario;
import infra.Graphics;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.seguranca.PermissaoPadrao;
import modelo.publicacao.ConfigPost;
import modelo.usuario.Imagem;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;
import service.usuario.UsuarioService;

@Data
@EqualsAndHashCode(callSuper = false)
@Named
@ViewScoped
public class UsuarioBean extends PaiBean<Usuario, UsuarioDAO, PermissaoPadrao<Usuario>>
{
	@Inject
	private FiltroUsuario filtro;

	@Inject
	private ConfigPostBean configPostBean;

	@Inject
	private UsuarioService usuarioService;

	private String senha;
	private String confirmaSenha;
	private int activeIndex;
	private int activeIndexGrafico;

	public UsuarioBean()
	{
		super(Usuario.class, "Usuário");

		urlCadastro = "/administracao/usuarios/usuario/form.xhtml";
		urlLista = "/administracao/usuarios/usuario/list.xhtml";

		auditoriasAtivas = EnumSet.allOf(TipoEvento.class);
	}

	public void filtrar()
	{
		this.lista = entidadeDAO.buscar(filtro);
		tabState.putState(filtro);
	}

	public void filtrarInit()
	{
		filtro.limpar();
		filtrar();
	}

	@PostConstruct
	public void init()
	{
		if(tabState.hasState(FiltroUsuario.class))
			filtro = tabState.getState(FiltroUsuario.class);
	}

	@Override
	public void personalizarAdicionar()
	{
		if(senha != null && !senha.isBlank())
			entidade.setSenha(usuarioService.hashPassword(senha));
	}

	@Override
	public void personalizarSalvar()
	{
		if(entidade.isCriador() && entidade.getConfigPost() == null)
		{
			ConfigPost configPost = configPostBean.cadastrar(entidade);
			entidade.setConfigPost(configPost);
		}
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
			entidade.setImagem(imagem);
		}
		catch(SQLException | IOException e)
		{
			e.printStackTrace();
		}
	}

	public void validateEmail(FacesContext context, UIComponent component, Object email)
	{
		Usuario usuariosBanco = entidadeDAO.getUsuario((String) email, "");
		if(entidade.getId() == null && usuariosBanco != null
		|| (entidade.getId() != null && usuariosBanco != null && !entidade.getId().equals(usuariosBanco.getId())))
		{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Email já cadastrado.");
			throw new ValidatorException(msg);
		}
	}

	public List<Usuario> getTodosUsuarios()
	{
		return entidadeDAO.listarTudo();
	}

	public String gerarSenhaAleatoria()
	{
		return usuarioService.gerarSenhaAleatoria();
	}

	public PerfilUsuario[] getPerfis()
	{
		return PerfilUsuario.values();
	}

}
