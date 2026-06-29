package bean.usuario;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.List;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import bean.PaiBean;
import bean.download.Diretorio;
import dao.usuario.UsuarioDAO;
import filtro.usuario.FiltroUsuario;
import infra.Graphics;
import jakarta.annotation.PostConstruct;
import service.configuracao.DiretorioService;
import util.FileAux;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import lombok.EqualsAndHashCode;
import modelo.auditoria.TipoEvento;
import modelo.seguranca.PermissaoPadrao;
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
	private UsuarioService usuarioService;

	@Inject
	private DiretorioService diretorioService;

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

	public void uploadFile(FileUploadEvent event)
	{
		UploadedFile arquivo = event.getFile();
		try
		{
			Diretorio diretorio = diretorioService.criarDiretorioSemReserva();
			String endBase = diretorio.getConfig().getEndereco();
			String endRel = "/images/usuario/" + entidade.getId() + "/";

			Imagem foto = entidade.getFoto() != null ? entidade.getFoto() : new Imagem();
			if(foto.getEndereco() != null)
			{
				File antigo = new File(endBase + foto.getEndereco());
				if(antigo.exists())
					antigo.delete();
			}
			byte[] bytes = Graphics.shapeImage(arquivo, 400, 400);
			FileAux.gravarFile(endBase + endRel, arquivo.getFileName(), bytes);
			foto.setEndereco(endRel + arquivo.getFileName());
			entidade.setFoto(foto);
		}
		catch(IOException e)
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
