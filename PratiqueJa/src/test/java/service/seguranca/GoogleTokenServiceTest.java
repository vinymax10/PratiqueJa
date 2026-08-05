package service.seguranca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.PlainJWT;
import com.nimbusds.jwt.SignedJWT;

import modelo.seguranca.GoogleUserInfo;

/**
 * O portão do login pelo Google. O que importa aqui é que um token que não
 * deveria passar volte {@code null}: assinatura de outra chave, sem assinatura,
 * de outro app (aud), ou de outra sessão (nonce).
 *
 * <p>Sem rede: em vez das chaves públicas do Google, o serviço recebe um JWKS
 * montado na hora, e os testes assinam os tokens com a chave privada
 * correspondente — mesmo caminho de código, só a origem das chaves muda.</p>
 */
@DisplayName("Verificação do ID token do Google")
class GoogleTokenServiceTest
{
	private static final String CLIENT_ID = "app-de-teste.apps.googleusercontent.com";
	private static final String NONCE = "nonce-desta-sessao";

	private static RSAKey chaveGoogle;
	private static RSAKey chaveIntrusa;
	private static GoogleTokenService service;

	@BeforeAll
	static void prepararChaves() throws JOSEException
	{
		chaveGoogle = new RSAKeyGenerator(2048).keyID("chave-1").generate();
		chaveIntrusa = new RSAKeyGenerator(2048).keyID("chave-1").generate();

		JWKSet publicadas = new JWKSet(chaveGoogle.toPublicJWK());
		service = new GoogleTokenService(CLIENT_ID, new ImmutableJWKSet<SecurityContext>(publicadas));
	}

	@Nested
	@DisplayName("Token legítimo")
	class Aceita
	{
		@Test
		void devolveAsClaims() throws JOSEException
		{
			GoogleUserInfo info = service.verificar(assinar(claimsValidas().build(), chaveGoogle), NONCE);

			assertNotNull(info, "token válido não pode ser recusado");
			assertEquals("maria@gmail.com", info.email());
			assertEquals("Maria Andrade", info.name());
			assertEquals("1234567890", info.sub());
		}
	}

	@Nested
	@DisplayName("Token que não pode passar")
	class Recusa
	{
		@Test
		void assinadoComOutraChave() throws JOSEException
		{
			assertNull(service.verificar(assinar(claimsValidas().build(), chaveIntrusa), NONCE));
		}

		@Test
		void semAssinaturaNenhuma()
		{
			assertNull(service.verificar(new PlainJWT(claimsValidas().build()).serialize(), NONCE));
		}

		@Test
		void emitidoParaOutraAplicacao() throws JOSEException
		{
			JWTClaimsSet claims = claimsValidas().audience("outro-app.apps.googleusercontent.com").build();
			assertNull(service.verificar(assinar(claims, chaveGoogle), NONCE));
		}

		@Test
		void vencido() throws JOSEException
		{
			JWTClaimsSet claims = claimsValidas().expirationTime(Date.from(Instant.now().minusSeconds(7200))).build();
			assertNull(service.verificar(assinar(claims, chaveGoogle), NONCE));
		}

		@Test
		void comEmailNaoVerificado() throws JOSEException
		{
			assertNull(service.verificar(assinar(claimsValidas().claim("email_verified", false).build(), chaveGoogle), NONCE));
		}

		@Test
		void ausenteOuLixo()
		{
			assertNull(service.verificar(null, NONCE));
			assertNull(service.verificar("", NONCE));
			assertNull(service.verificar("nem-parece-um-jwt", NONCE));
		}
	}

	@Nested
	@DisplayName("Nonce (token de outra sessão)")
	class Nonce
	{
		@Test
		void recusaTokenDeOutraSessao() throws JOSEException
		{
			String deOutraSessao = assinar(claimsValidas().claim("nonce", "nonce-de-outra-sessao").build(), chaveGoogle);
			assertNull(service.verificar(deOutraSessao, NONCE));
		}

		@Test
		void recusaTokenSemNonce() throws JOSEException
		{
			assertNull(service.verificar(assinar(claimsValidas().claim("nonce", null).build(), chaveGoogle), NONCE));
		}

		@Test
		void recusaQuandoASessaoNaoTemNonce() throws JOSEException
		{
			String token = assinar(claimsValidas().build(), chaveGoogle);
			assertNull(service.verificar(token, null));
			assertNull(service.verificar(token, ""));
		}
	}

	// -----------------------------------------------------------------

	private static JWTClaimsSet.Builder claimsValidas()
	{
		return new JWTClaimsSet.Builder()
			.issuer("https://accounts.google.com")
			.audience(CLIENT_ID)
			.subject("1234567890")
			.issueTime(Date.from(Instant.now()))
			.expirationTime(Date.from(Instant.now().plusSeconds(600)))
			.claim("nonce", NONCE)
			.claim("email", "maria@gmail.com")
			.claim("email_verified", true)
			.claim("name", "Maria Andrade")
			.claim("picture", "https://lh3.googleusercontent.com/foto");
	}

	private static String assinar(JWTClaimsSet claims, RSAKey chave) throws JOSEException
	{
		SignedJWT jwt = new SignedJWT(
			new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(chave.getKeyID()).build(), claims);

		jwt.sign(new RSASSASigner(chave));

		return jwt.serialize();
	}
}
