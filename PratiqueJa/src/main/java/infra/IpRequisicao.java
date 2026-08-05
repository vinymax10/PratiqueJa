package infra;

import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;

/**
 * O IP de quem está fazendo a requisição, visto de dentro de um bean JSF.
 *
 * <p>O WildFly fica atrás do nginx (proxy_pass de 127.0.0.1:8080), então o
 * {@code request.getRemoteAddr()} é sempre {@code 127.0.0.1} — inútil para
 * distinguir clientes. O IP verdadeiro chega no cabeçalho {@code X-Real-IP},
 * que o <b>nosso</b> nginx preenche com o {@code $remote_addr} da conexão real
 * (ver {@code sites-available/pratiqueja}).</p>
 *
 * <p>Confia-se no {@code X-Real-IP}, e não no {@code X-Forwarded-For}: o XFF é
 * uma lista que o cliente pode começar com valores forjados (o nginx só
 * <i>acrescenta</i> o IP real ao final), enquanto o {@code X-Real-IP} é
 * sobrescrito pelo nginx a cada requisição. Quem fala direto com o WildFly sem
 * passar pelo nginx só consegue isso de dentro do próprio servidor, e aí o
 * {@code getRemoteAddr} de fallback já entrega um valor tão bom quanto.</p>
 *
 * <p>Serve para chavear controles por origem (throttle de login, de
 * recuperação de senha). Não é identidade nem autorização: um NAT põe várias
 * pessoas atrás do mesmo IP, e é por isso que os controles que o usam nunca
 * bloqueiam <i>conta</i> por IP — só adicionam atrito.</p>
 */
public final class IpRequisicao
{
	private IpRequisicao()
	{
	}

	public static String atual()
	{
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
		.getExternalContext().getRequest();

		String real = request.getHeader("X-Real-IP");

		if(real != null && !real.isBlank())
			return real.trim();

		return request.getRemoteAddr();
	}
}
