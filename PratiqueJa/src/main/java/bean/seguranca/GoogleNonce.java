package bean.seguranca;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Base64;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/**
 * O nonce do login pelo Google: um valor aleatório que amarra o ID token à
 * sessão do navegador que pediu o login.
 *
 * <p><b>Para que serve.</b> A verificação de assinatura garante que o token é
 * verdadeiro, mas não que ele foi emitido <i>para esta tentativa de login</i>.
 * Um token legítimo capturado em outro lugar — de um log, de um proxy, de outra
 * aba — continua válido pela hora de vida dele e serviria para entrar. O nonce
 * fecha isso: o valor é sorteado aqui, vai no
 * {@code google.accounts.id.initialize} (via {@code js/google.js}) e o Google o
 * carimba dentro do token que emite. Quem tenta reaproveitar um token de outra
 * sessão apresenta um nonce que não é o desta, e o {@code GoogleTokenService}
 * recusa. E não há como o atacante forjar o par: o carimbo acontece do lado do
 * Google, na emissão, sob a assinatura.</p>
 *
 * <p><b>Um por sessão, não um por tela.</b> Sortear a cada renderização seria
 * mais rígido, mas quebraria dois casos comuns e legítimos: duas abas abertas
 * na tela de login (a segunda invalidaria a primeira) e a tentativa seguinte a
 * uma recusa — como a resposta é ajax, a página não é redesenhada e o script
 * continuaria com o nonce velho, de modo que ninguém mais conseguiria entrar.
 * Um valor por sessão já entrega o que importa: o token só vale no navegador
 * que iniciou o login. O logout invalida a sessão HTTP ({@code Sessao.encerrar}),
 * então o próximo visitante sorteia outro.</p>
 */
@Named("googleNonce")
@SessionScoped
public class GoogleNonce implements Serializable
{
	private static final long serialVersionUID = 1L;

	/** 32 bytes: o mesmo tamanho do token de recuperação de senha. */
	private static final int BYTES = 32;

	private static final SecureRandom SORTEIO = new SecureRandom();

	private String valor;

	/**
	 * O nonce desta sessão, sorteado na primeira leitura. Lido pelo template via
	 * EL para o script do Google ({@code js/google.js}).
	 */
	public synchronized String getValor()
	{
		if(valor == null)
		{
			byte[] bytes = new byte[BYTES];
			SORTEIO.nextBytes(bytes);
			valor = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
		}

		return valor;
	}
}
