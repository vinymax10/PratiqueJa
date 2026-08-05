package bean.seguranca;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import org.primefaces.PrimeFaces;

import bean.download.Diretorio;
import bean.util.Mensagem;
import dao.usuario.UsuarioDAO;
import infra.Navegacao;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import javax.imageio.ImageIO;
import lombok.Data;
import modelo.seguranca.GoogleUserInfo;
import modelo.usuario.Imagem;
import modelo.usuario.Usuario;
import net.coobird.thumbnailator.Thumbnails;
import infra.IpRequisicao;
import service.configuracao.DiretorioService;
import service.seguranca.AcessoService;
import service.seguranca.GoogleTokenService;
import service.seguranca.ThrottleLogin;
import service.seguranca.ThrottleRecuperacaoSenha;
import service.usuario.RecuperacaoSenhaService;
import util.FileAux;
import util.UrlAux;
import web.session.Sessao;

@Data
@Named
@SessionScoped
public class AutenticacaoBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Email inválido.")
	private String email;
	private String senha;

	private String senhaAntiga;

	/** Campo do diálogo "Esqueci minha senha" — separado do e-mail do formulário de login. */
	private String emailRecuperacao;

	private Usuario usuario;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private RecuperacaoSenhaService recuperacaoSenhaService;

	private String urlRedefinirSenha = "/login/redefinirSenha.xhtml";

	@Inject
	private AcessoService acessoService;
	
	@Inject
	private SessaoBean sessaoBean;

	@Inject
	private DiretorioService diretorioService;

	@Inject
	private GoogleTokenService googleTokenService;

	@Inject
	private GoogleNonce googleNonce;

	@Inject
	private ThrottleLogin throttleLogin;

	@Inject
	private ThrottleRecuperacaoSenha throttleRecuperacao;

	private static final String MSG_CREDENCIAIS = "E-mail ou senha incorretos.";
	private static final String MSG_MUITAS_TENTATIVAS =
		"Muitas tentativas de login. Aguarde alguns minutos e tente novamente.";

	/**
	 * Hash de comparação para quando não há senha real (e-mail inexistente ou
	 * conta só-Google). Custo 12, o mesmo dos hashes de verdade
	 * ({@code UsuarioService}) — se fosse mais barato, o tempo denunciaria que
	 * ali não havia conta. Calculado uma vez no carregamento da classe.
	 */
	private static final String HASH_FICTICIO = BCrypt.hashpw("pratiqueja-timing-uniforme", BCrypt.gensalt(12));

	public void init()
	{
		email = "";
		senha = "";
		senhaAntiga="";
		emailRecuperacao = "";
	}

	/**
	 * Ação do diálogo "Esqueci minha senha": envia por e-mail um link com token de uso único
	 * (ver {@link RecuperacaoSenhaService}). A senha atual continua valendo até o link ser usado.
	 */
	public void recuperarSenha()
	{
		if(emailRecuperacao == null || emailRecuperacao.isBlank())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Informe o e-mail cadastrado.");
			return;
		}

		// Responde SEMPRE a mesma coisa, exista ou não a conta (e esteja ela
		// ativa ou não). Antes a tela dizia "E-mail não cadastrado" / "Usuário
		// inativo" / "Enviamos para X..." — três respostas que juntas
		// transformavam o formulário num verificador de cadastro. O envio de
		// fato só acontece com conta ativa e passando pela trava anti-bomba
		// (ThrottleRecuperacaoSenha), mas nada disso muda o que o usuário vê.
		Usuario usuarioRecuperacao = usuarioDAO.getUsuario(emailRecuperacao, "");

		if(usuarioRecuperacao != null && usuarioRecuperacao.isAtivo() && throttleRecuperacao.podeEnviar(emailRecuperacao))
		{
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
			recuperacaoSenhaService.solicitar(usuarioRecuperacao, UrlAux.base(request));
		}

		Mensagem.send("growl", FacesMessage.SEVERITY_INFO,
			"Se existir uma conta com esse e-mail, enviamos um link para você criar uma nova senha. "
			+ "Ele vale por 60 minutos; confira também o spam.");

		emailRecuperacao = "";
		PrimeFaces.current().ajax().addCallbackParam("recuperado", true);
	}
	
	public String login()
	{
		String ip = IpRequisicao.atual();

		if(throttleLogin.bloqueadoPorIp(ip))
		{
			// Recusa antes do BCrypt: não se gasta CPU com o chute e a tentativa
			// não conta como falha nova. A mensagem fala do excesso, não da conta.
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_ERROR, MSG_MUITAS_TENTATIVAS);
			senha = "";
			return "";
		}

		usuario = usuarioDAO.getUsuario(email, "");

		if(!senhaConfere(usuario, senha))
		{
			pausar(throttleLogin.registrarFalha(email, ip));
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_ERROR, MSG_CREDENCIAIS);
			senha = "";
			return "";
		}

		// Senha correta: zera o placar do throttle.
		throttleLogin.registrarSucesso(email, ip);

		// "Inativo" só aparece DEPOIS de a senha bater — não é oráculo de
		// enumeração (o atacante já teria a senha), e é o aviso certo para o dono.
		if(!usuario.isAtivo())
		{
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_ERROR, "Usuário inativo. Entre em contato com o suporte.");
			senha = "";
			return "";
		}

		String paginaOrigem = obterPaginaOrigem();
		iniciarSessaoUsuario();
		if(usuario.isResetSenha())
			Navegacao.redirect(urlRedefinirSenha);
		else
		{
			Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "Login efetuado com sucesso");
			Navegacao.redirect(paginaOrigem);
		}

		return "";
	}

	/**
	 * Chamado via p:remoteCommand após o login com o Google (google.js).
	 *
	 * O único parâmetro aceito é o ID token ("credential"); e-mail, nome e sub saem das claims
	 * dele depois de verificado ({@link GoogleTokenService}). Ler esses campos direto do request
	 * permitiria a qualquer um postar o e-mail de outra pessoa e entrar na conta dela.
	 */
	public void loginGoogle()
	{
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

		GoogleUserInfo info = googleTokenService.verificar(params.get("credential"), googleNonce.getValor());

		if(info == null)
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Não foi possível validar o login com o Google.");
			return;
		}

		usuario = usuarioDAO.getUsuarioGoogle(info.sub());

		if(usuario == null)
		{
			// Vincula a conta já existente de mesmo e-mail: só é seguro porque o e-mail
			// vem do token verificado e com email_verified.
			usuario = usuarioDAO.getUsuario(info.email(), "");
			boolean novo = usuario == null;
			if(novo)
			{
				usuario = new Usuario();
				usuario.setEmail(info.email());
				usuario.setNome(info.name());
			}
			usuario.setSubGoogle(info.sub());
			usuario = usuarioDAO.salvar(usuario);
			salvarFotoGoogle(info.picture());
		}

		if(!usuario.isAtivo())
		{
			Mensagem.send("growl", FacesMessage.SEVERITY_ERROR, "Usuário inativo.");
			return;
		}

		iniciarSessaoUsuario();
		Mensagem.send("growl", FacesMessage.SEVERITY_INFO, "Login efetuado com sucesso.");

		// Sem isso o oncomplete recarregaria a página também quando o login falha, e o
		// growl com o motivo se perderia no reload.
		PrimeFaces.current().ajax().addCallbackParam("loginOk", true);
	}

	private void salvarFotoGoogle(String pictureUrl)
	{
		if(pictureUrl == null || pictureUrl.isBlank() || usuario.getFoto() != null)
			return;

		try
		{
			byte[] bytes = redimensionar(pictureUrl);

			Diretorio diretorio = diretorioService.criarDiretorioSemReserva();
			String endBase = diretorio.getConfig().getEndereco();
			String endRel = "/images/usuario/" + usuario.getId() + "/";
			String nomeArquivo = "google.png";

			FileAux.gravarFile(endBase + endRel, nomeArquivo, bytes);

			Imagem foto = new Imagem();
			foto.setEndereco(endRel + nomeArquivo);
			usuario.setFoto(foto);
			usuario = usuarioDAO.salvar(usuario);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private byte[] redimensionar(String pictureUrl) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(Thumbnails.of(URI.create(pictureUrl).toURL()).size(400, 400).keepAspectRatio(false).asBufferedImage(),
			"png", baos);
		return baos.toByteArray();
	}

	private String obterPaginaOrigem()
	{
		try
		{
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
			String referer = request.getHeader("Referer");
			if(referer != null)
			{
				java.net.URL url = new java.net.URL(referer);
				String path = url.getPath();
				String contextPath = request.getContextPath();
				if(path.startsWith(contextPath))
					path = path.substring(contextPath.length());
				if(!path.contains("/login/") && !path.isBlank())
				{
					String query = url.getQuery();
					return query != null ? path + "?" + query : path;
				}
			}
		}
		catch(Exception ignored) {}
		return sessaoBean.getUrlInicial();
	}

	/**
	 * Confere a senha rodando o BCrypt <b>sempre</b> — inclusive quando o
	 * usuário não existe ou é conta só-Google (sem senha), caso em que a
	 * comparação é contra o {@link #HASH_FICTICIO}. Sem isso, só a conta real
	 * chegaria a rodar o BCrypt e o tempo de resposta viraria um verificador de
	 * cadastro: a diferença de algumas centenas de ms é medível de fora. A
	 * recusa é sempre a mesma mensagem ({@link #MSG_CREDENCIAIS}), sem distinguir
	 * e-mail inexistente de senha errada.
	 */
	private boolean senhaConfere(Usuario u, String senhaDigitada)
	{
		boolean temSenha = u != null && u.getSenha() != null && !u.getSenha().isBlank();
		String hash = temSenha ? u.getSenha() : HASH_FICTICIO;

		boolean confere = BCrypt.checkpw(senhaDigitada == null ? "" : senhaDigitada, hash);

		return temSenha && confere;
	}

	/**
	 * Aplica o atraso do throttle por conta. Interrupção não é motivo para vazar
	 * a diferença de tempo nem para derrubar o login — restaura o flag e volta.
	 */
	private void pausar(long ms)
	{
		if(ms <= 0)
			return;

		try
		{
			Thread.sleep(ms);
		}
		catch(InterruptedException e)
		{
			Thread.currentThread().interrupt();
		}
	}

	private void iniciarSessaoUsuario()
	{
		sessaoBean.iniciarSession(usuario);
		acessoService.registrarLogin(usuario);
	}

	public String logout()
	{
		String paginaAtual = obterPaginaOrigem();

		acessoService.registrarLogout(Sessao.id());
		Sessao.encerrar();
		usuario = null;

		// A flash message precisa ser setada depois de encerrar a sessão antiga, senão
		// ela é descartada junto com o resto dos atributos da sessão invalidada.
		Mensagem.sendRedirect("growl", FacesMessage.SEVERITY_INFO, "Logout efetuado com sucesso.");
		Navegacao.redirect(paginaAtual);

		return "";
	}

}
