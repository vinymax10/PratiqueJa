package bean.seguranca;

import java.io.Serializable;

import bean.util.Mensagem;
import dao.seguranca.TokenSenhaDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import modelo.seguranca.TokenSenha;
import service.usuario.RecuperacaoSenhaService;
import web.session.Sessao;
import web.webhook.RecuperacaoSenhaServlet;

/**
 * Tela /login/novaSenha.xhtml: destino de quem clicou no link do e-mail de recuperação.
 * O token já foi validado pelo {@link RecuperacaoSenhaServlet}, que deixou o id na sessão.
 */
@Data
@Named
@ViewScoped
public class NovaSenhaBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Inject
	private TokenSenhaDAO tokenSenhaDAO;

	@Inject
	private RecuperacaoSenhaService recuperacaoSenhaService;

	private TokenSenha tokenSenha;
	private String senha;
	private String confirmaSenha;

	@PostConstruct
	public void init()
	{
		Object id = Sessao.get(RecuperacaoSenhaServlet.ATRIBUTO_SESSAO);
		tokenSenha = id != null ? tokenSenhaDAO.carrega((Long) id) : null;
	}

	/** Falso quando o link não veio, já foi usado ou passou da validade. */
	public boolean isLinkValido()
	{
		return tokenSenha != null && tokenSenha.isValido();
	}

	public String salvar()
	{
		// Revalida na hora de gravar: a tela pode ter ficado aberta até o token expirar.
		if(!isLinkValido())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR,
				"Este link não vale mais. Peça um novo em \"Esqueci minha senha\".");
			return null;
		}

		try
		{
			recuperacaoSenhaService.redefinir(tokenSenha, senha);
			Sessao.externalContext().getSessionMap().remove(RecuperacaoSenhaServlet.ATRIBUTO_SESSAO);

			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO,
				"Senha alterada com sucesso. Entre com a nova senha.");
			return "/inicio.xhtml?faces-redirect=true";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível alterar a senha.");
			return null;
		}
	}
}
