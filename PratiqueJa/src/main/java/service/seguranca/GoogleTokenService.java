package service.seguranca;

import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.jwk.source.JWKSourceBuilder;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jose.util.DefaultResourceRetriever;
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
 *   <li><b>nonce</b> — o token foi emitido para <i>esta</i> sessão, e não é um token
 *       legítimo de outra pessoa sendo reapresentado dentro da validade dele
 *       (ver {@code bean.seguranca.GoogleNonce});</li>
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

	/** Construtor do CDI. */
	public GoogleTokenService()
	{
	}

	/**
	 * Construtor de teste: chaves e client_id na mão, sem rede. Deixa a suíte
	 * assinar tokens com uma chave própria e conferir que os errados (assinatura
	 * de outra chave, aud de outro app, nonce de outra sessão) são recusados.
	 */
	GoogleTokenService(String clientId, JWKSource<SecurityContext> chaves)
	{
		this.processor = montarProcessor(clientId, chaves);
	}

	/**
	 * @param idToken      o JWT cru vindo do Google Identity Services
	 * @param nonceEsperado o nonce desta sessão, o mesmo entregue ao script que pediu o token
	 * @return os dados do usuário extraídos do token, ou {@code null} se o token for
	 *         inválido/ausente/de outra sessão — nesse caso o login deve ser recusado.
	 */
	public GoogleUserInfo verificar(String idToken, String nonceEsperado)
	{
		// Logado, e não recusado em silêncio: token ausente é o sintoma de front
		// desatualizado (script velho em cache mandando os campos soltos, ou nem
		// chegando a mandar). Sem esta linha a falha não deixava rastro nenhum no
		// log e ficava indistinguível de "ninguém tentou".
		if(idToken == null || idToken.isBlank())
		{
			LOG.warn("Login Google recusado: requisição sem o ID token (front desatualizado?)");
			return null;
		}

		try
		{
			JWTClaimsSet claims = processor().process(idToken, null);

			if(!EMISSORES.contains(claims.getIssuer()))
			{
				LOG.warn("Login Google recusado: emissor inesperado '{}'", claims.getIssuer());
				return null;
			}

			if(!nonceConfere(claims.getStringClaim("nonce"), nonceEsperado))
			{
				LOG.warn("Login Google recusado: nonce ausente ou de outra sessão");
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

	/**
	 * Nonce ausente dos dois lados é recusa, não dispensa: token sem nonce é
	 * justamente o que um token reaproveitado de outro lugar tem, e sessão sem
	 * nonce esperado significa chamada que não passou pela tela. Comparação em
	 * tempo constante, como convém a segredo.
	 */
	private boolean nonceConfere(String doToken, String esperado)
	{
		if(doToken == null || doToken.isBlank() || esperado == null || esperado.isBlank())
			return false;

		return MessageDigest.isEqual(
			doToken.getBytes(StandardCharsets.UTF_8), esperado.getBytes(StandardCharsets.UTF_8));
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

		// O padrão do Nimbus é 500ms de connect/read — apertado para a primeira
		// busca (DNS frio + TLS), e quem pagaria seria o primeiro login depois de
		// cada expiração do cache. 3s é folga sem prender a requisição.
		JWKSource<SecurityContext> jwkSource = JWKSourceBuilder.<SecurityContext>create(
			urlCerts, new DefaultResourceRetriever(3000, 3000, JWKSourceBuilder.DEFAULT_HTTP_SIZE_LIMIT))
			.retrying(true)
			.build();

		return montarProcessor(GoogleConfig.getClientId(), jwkSource);
	}

	private ConfigurableJWTProcessor<SecurityContext> montarProcessor(String clientId, JWKSource<SecurityContext> jwkSource)
	{
		ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
		jwtProcessor.setJWSKeySelector(new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, jwkSource));
		jwtProcessor.setJWTClaimsSetVerifier(new DefaultJWTClaimsVerifier<>(
			clientId,
			null,
			Set.of("iss", "sub", "aud", "exp", "email")));

		return jwtProcessor;
	}
}
