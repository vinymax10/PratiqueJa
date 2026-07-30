package bean.seguranca;

import java.io.Serializable;

import bean.util.Mensagem;
import dao.usuario.UsuarioDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.usuario.Usuario;
import service.usuario.UsuarioService;
import web.session.Sessao;

/**
 * Tela /login/redefinirSenha.xhtml: troca de senha obrigatória no login, para quem está com
 * {@code Usuario.resetSenha} marcado — ver {@link AutenticacaoBean#login()}.
 *
 * Não é o "Esqueci minha senha" (esse é por link com token, sem exigir login: ver
 * {@link service.usuario.RecuperacaoSenhaService} e {@link NovaSenhaBean}).
 */
@Data
@Named
@ViewScoped
public class RedefinirSenhaBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private SessaoBean sessaoBean;

	private Usuario entidade;
	private String senha;
	private String confirmaSenha;

	/**
	 * Acesso anônimo já é barrado pelo PageFilter (/login/* no web.xml) — aqui só carrega o
	 * usuário da sessão, com guarda para não estourar caso o filtro seja desmapeado.
	 */
	@PostConstruct
	public void init()
	{
		Usuario logado = Sessao.getUsuarioLogado();
		entidade = logado != null ? usuarioDAO.carrega(logado.getId()) : new Usuario();
	}

	public String redefinir()
	{
		if(entidade == null || entidade.getId() == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Sessão expirada. Entre novamente.");
			return null;
		}

		try
		{
			entidade.setSenha(usuarioService.hashPassword(senha));
			entidade.setResetSenha(false);
			entidade = usuarioDAO.salvar(entidade);
			sessaoBean.updateSession(entidade);

			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "Senha redefinida com sucesso.");
			return "/inicio.xhtml?faces-redirect=true";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível redefinir a senha.");
			return null;
		}
	}
}
