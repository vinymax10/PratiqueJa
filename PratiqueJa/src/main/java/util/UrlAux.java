package util;

import jakarta.servlet.http.HttpServletRequest;

public class UrlAux
{
	/**
	 * Raiz pública do site (sem barra final), para montar links absolutos em e-mails.
	 * Em produção o WildFly só enxerga http://127.0.0.1:8080 — quem sabe o domínio e o
	 * esquema reais é o nginx, que informa nos cabeçalhos X-Forwarded-*.
	 */
	public static String base(HttpServletRequest request)
	{
		String esquema = primeiroValor(request.getHeader("X-Forwarded-Proto"));
		if(esquema == null || esquema.isBlank())
			esquema = request.getScheme();

		String host = primeiroValor(request.getHeader("X-Forwarded-Host"));
		if(host == null || host.isBlank())
		{
			host = request.getServerName();
			int porta = request.getServerPort();
			boolean portaPadrao = ("http".equals(esquema) && porta == 80) || ("https".equals(esquema) && porta == 443);
			if(!portaPadrao)
				host = host + ":" + porta;
		}

		return esquema + "://" + host + request.getContextPath();
	}

	/** Os cabeçalhos X-Forwarded-* podem vir como lista ("a, b") quando há mais de um proxy. */
	private static String primeiroValor(String cabecalho)
	{
		if(cabecalho == null)
			return null;
		int virgula = cabecalho.indexOf(',');
		return (virgula < 0 ? cabecalho : cabecalho.substring(0, virgula)).trim();
	}
}
