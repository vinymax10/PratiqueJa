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
import modelo.avaliacao.PerfilAvaliacao;
import modelo.publicacao.PerfilCriador;
import modelo.seguranca.PermissaoPadrao;
import modelo.usuario.Imagem;
import modelo.usuario.PerfilUsuario;
import modelo.usuario.Usuario;
import service.configuracao.DiretorioService;
import service.usuario.EstatisticaUsuarioService;
import service.usuario.UsuarioService;
import util.FileAux;

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

	@Inject
	private EstatisticaUsuarioService estatisticaUsuarioService;

	// Estatísticas do usuário (calculadas sob demanda e cacheadas por id enquanto a view viver).
	private Long estatisticasParaId;
	private int totalPosts;
	private int totalAvaliacoes;
	private int totalExerciciosResolvidos;
	private int totalQuestoesResolvidas;

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

	public PerfilCriador[] getPerfisCriador()
	{
		return PerfilCriador.values();
	}

	public PerfilAvaliacao[] getPerfisAvaliacao()
	{
		return PerfilAvaliacao.values();
	}

	// ── Estatísticas do usuário (form do admin) ───────────────────────

	/** Calcula as 4 estatísticas uma vez por usuário carregado (evita reconsultar a cada getter do JSF). */
	private void carregarEstatisticas()
	{
		Usuario u = getEntidade();
		Long id = u != null ? u.getId() : null;

		if(id != null && id.equals(estatisticasParaId))
			return;

		estatisticasParaId = id;

		EstatisticaUsuarioService.Estatisticas e = estatisticaUsuarioService.calcular(u);
		totalPosts = e.posts();
		totalAvaliacoes = e.avaliacoes();
		totalExerciciosResolvidos = e.exerciciosResolvidos();
		totalQuestoesResolvidas = e.questoesResolvidas();
	}

	/** Total de posts gerados pelo usuário (soma das quantidades, exceto rascunhos). */
	public int getTotalPosts()
	{
		carregarEstatisticas();
		return totalPosts;
	}

	/** Total de avaliações geradas pelo usuário (soma das quantidades, exceto rascunhos). */
	public int getTotalAvaliacoes()
	{
		carregarEstatisticas();
		return totalAvaliacoes;
	}

	/** Total de exercícios que o usuário já resolveu. */
	public int getTotalExerciciosResolvidos()
	{
		carregarEstatisticas();
		return totalExerciciosResolvidos;
	}

	/** Total de questões que o usuário já resolveu. */
	public int getTotalQuestoesResolvidas()
	{
		carregarEstatisticas();
		return totalQuestoesResolvidas;
	}

}
