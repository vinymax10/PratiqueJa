package service.seguranca;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.LongSupplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * A trava contra a bomba de e-mail do "esqueci a senha": cada destino recebe no
 * máximo um link por vez na janela, venha o pedido de onde vier.
 */
@DisplayName("Throttle da recuperação de senha")
class ThrottleRecuperacaoSenhaTest
{
	private Relogio relogio;
	private ThrottleRecuperacaoSenha throttle;

	@BeforeEach
	void iniciar()
	{
		relogio = new Relogio();
		throttle = new ThrottleRecuperacaoSenha(relogio);
	}

	@Test
	void primeiroEnvioPassaOSegundoImediatoNao()
	{
		assertTrue(throttle.podeEnviar("vitima@x.com"), "o primeiro pedido envia");
		assertFalse(throttle.podeEnviar("vitima@x.com"), "o segundo em seguida é a bomba, e é barrado");
	}

	@Test
	void liberaDepoisDoCooldown()
	{
		assertTrue(throttle.podeEnviar("vitima@x.com"));

		relogio.avancar(ThrottleRecuperacaoSenha.COOLDOWN_MS - 1);
		assertFalse(throttle.podeEnviar("vitima@x.com"), "ainda dentro do intervalo");

		relogio.avancar(1);
		assertTrue(throttle.podeEnviar("vitima@x.com"), "passado o intervalo, envia de novo");
	}

	@Test
	void destinosDiferentesSaoIndependentes()
	{
		assertTrue(throttle.podeEnviar("a@x.com"));
		assertTrue(throttle.podeEnviar("b@x.com"), "a trava de um destino não segura o outro");
	}

	@Test
	void mesmoDestinoEscritoDiferenteCompartilhaATrava()
	{
		assertTrue(throttle.podeEnviar("Vitima@X.com"));
		assertFalse(throttle.podeEnviar("  vitima@x.com  "), "caixa e espaço não furam a trava");
	}

	/** Relógio mutável em milissegundos. */
	private static final class Relogio implements LongSupplier
	{
		private long t = 1_000_000L;

		@Override
		public long getAsLong()
		{
			return t;
		}

		void avancar(long ms)
		{
			t += ms;
		}
	}
}
