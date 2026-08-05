package service.seguranca;

import java.net.URI;
import java.net.URL;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.JWKSourceBuilder;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTClaimsVerifier;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

import infra.GoogleConfig;
import jakarta.enterprise.context.ApplicationScoped;
import modelo.seguranca.GoogleUserInfo;

/**
 * Valida o ID token (JWT) devolvido pelo Google Identity Services no login.
 *
 * <p>O token é a <b>única</b> coisa que o navegador manda: decodificá-lo no cliente e enviar
 * os campos soltos (e-mail, sub) equivale a deixar qualquer um postar o e-mail da vítima e
 * entrar na conta dela. Aqui se confere, do lado do servidor:</p>
 *
 * <ul>
 *   <li><b>assinatura</b> — contra as chaves públicas do Google ({@link #URL_CERTS}, com cache);</li>
 *   <li><b>aud</b> — precisa ser o client ID deste site ({@link GoogleConfig}), senão um token
 *       emitido para outro app serviria para entrar aqui;</li>
 *   <li><b>iss</b> — precisa ser o Google;</li>
 *   <li><b>exp/nbf</b> — validade (com a tolerância de relógio padrão do Nimbus);</li>
 *   <li><b>email_verified</b> — sem isso o e-mail não prova posse da conta.</li>
 * </ul>
 *
 * Só as claims desse token verificado podem ser usadas para autenticar — nunca parâmetros
 * de request (ver {@code AutenticacaoBean.loginGoogle}).
 */
@ApplicationScoped
public class GoogleTokenService
{
	private static final Logger LOG = LoggerFactory.getLogger(GoogleTokenService.class);

	/** JWKS do Google (chaves públicas de assinatura dos ID tokens). */
	private static final String URL_CERTS = "https://www.googleapis.com/oauth2/v3/certs";

	/** O Google emite tanto com esquema quanto sem — os dois são válidos. */
	private static final Set<String> EMISSORES = Set.of("https://accounts.google.com", "accounts.google.com");

	private volatile ConfigurableJWTProcessor<SecurityContext> processor;

	/**
	 * @return os dados do usuário extraídos do token, ou {@code null} se o token for
	 *         inválido/ausente — nesse caso o login deve ser recusado.
	 */
	public GoogleUserInfo verificar(String idToken)
	{
		if(idToken == null || idToken.isBlank())
			return null;

		try
		{
			JWTClaimsSet claims = processor().process(idToken, null);

			if(!EMISSORES.contains(claims.getIssuer()))
			{
				LOG.warn("Login Google recusado: emissor inesperado '{}'", claims.getIssuer());
				return null;
			}

			if(!emailVerificado(claims))
			{
				LOG.warn("Login Google recusado: e-mail não verificado ({})", claims.getStringClaim("email"));
				return null;
			}

			String email = claims.getStringClaim("email");
			String sub = claims.getSubject();

			if(email == null || email.isBlank() || sub == null || sub.isBlank())
			{
				LOG.warn("Login Google recusado: token sem email/sub");
				return null;
			}

			return new GoogleUserInfo(email, claims.getStringClaim("name"), sub, claims.getStringClaim("picture"));
		}
		catch(Exception e)
		{
			// Assinatura errada, aud/iss de outro app, token expirado ou lixo qualquer.
			LOG.warn("Login Google recusado: token inválido ({})", e.toString());
			return null;
		}
	}

	/** O Google manda ora booleano, ora a string "true". */
	private boolean emailVerificado(JWTClaimsSet claims)
	{
		Object valor = claims.getClaim("email_verified");

		if(valor instanceof Boolean b)
			return b;

		return valor != null && "true".equalsIgnoreCase(valor.toString());
	}

	/**
	 * Montado sob demanda (e não no start do app) para que uma falha de rede na hora de
	 * buscar as chaves não derrube o deploy inteiro. O JWKSource já faz cache das chaves.
	 */
	private ConfigurableJWTProcessor<SecurityContext> processor() throws Exception
	{
		ConfigurableJWTProcessor<SecurityContext> local = processor;

		if(local == null)
		{
			synchronized(this)
			{
				local = processor;
				if(local == null)
					processor = local = criarProcessor();
			}
		}

		return local;
	}

	private ConfigurableJWTProcessor<SecurityContext> criarProcessor() throws Exception
	{
		URL urlCerts = URI.create(URL_CERTS).toURL();

		JWKSource<SecurityContext> jwkSource = JWKSourceBuilder.<SecurityContext>create(urlCerts)
			.retrying(true)
			.build();

		ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
		jwtProcessor.setJWSKeySelector(new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, jwkSource));
		jwtProcessor.setJWTClaimsSetVerifier(new DefaultJWTClaimsVerifier<>(
			GoogleConfig.getClientId(),
			null,
			Set.of("iss", "sub", "aud", "exp", "email")));

		return jwtProcessor;
	}
}
