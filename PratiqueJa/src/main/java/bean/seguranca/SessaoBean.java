package bean.seguranca;

import java.io.Serializable;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import modelo.usuario.Usuario;
import service.AutorizacaoService;
import session.Sessao;

@Data
@SessionScoped
@Named
public class SessaoBean implements Serializable
{
	private Usuario usuario;

	private String urlInicial = "/matematica/painel.xhtml";
	private String urlLogin = "/login/login.xhtml";
	
//	@Inject
//	private AutorizacaoService autorizacaoService;

	public boolean isLogado()
	{
		return usuario!=null;
	}
	
	public boolean isPodeVerAuditoria()
	{
		return AutorizacaoService.podeVerAuditoria(usuario);
	}
	
	public boolean podeVerAdministracao()
	{
		return AutorizacaoService.podeVerAdministracao(usuario);
	}

	public void iniciarSession(Usuario usuario)
	{
		HttpServletRequest request = (HttpServletRequest) Sessao.externalContext().getRequest();
		HttpSession oldSession = request.getSession(false);
		if(oldSession != null)
			oldSession.invalidate();

		request.getSession(true);
		updateSession(usuario);
		
		HttpServletResponse response = (HttpServletResponse) Sessao.externalContext().getResponse();
		
		String token = UUID.randomUUID().toString();
		Sessao.set("SESSION_TOKEN", token);
		Cookie cookie = new Cookie("SESSION_TOKEN", token);
		cookie.setHttpOnly(true);
		cookie.setSecure(request.isSecure());
		cookie.setPath("/");
		cookie.setMaxAge(-1);
		
		response.addCookie(cookie);
	}

	public void updateSession(Usuario usuario)
	{
		this.usuario = usuario;
		Sessao.set("UsuarioLogado", usuario);
	}

	@PostConstruct
	public void init()
	{
		usuario = (Usuario) Sessao.get("UsuarioLogado");
	}
}
