package service.seguranca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.LongSupplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * O freio de força bruta no login. O que estes testes protegem é o
 * comportamento, não a implementação: depois de tantas falhas o IP espera, a
 * espera cresce e depois passa, o sucesso zera tudo, e a conta só ganha atraso
 * (nunca bloqueio) para não virar um jeito de trancar o acesso alheio.
 *
 * <p>Relógio controlável ({@code LongSupplier}) para não depender do tempo real
 * passar — o mesmo recurso que deixa exercitar a janela de 15 minutos em
 * microssegundos.</p>
 */
@DisplayName("Throttle de login")
class ThrottleLoginTest
{
	private Relogio relogio;
	private ThrottleLogin throttle;

	@BeforeEach
	void iniciar()
	{
		relogio = new Relogio();
		throttle = new ThrottleLogin(relogio);
	}

	@Nested
	@DisplayName("Bloqueio por IP")
	class PorIp
	{
		@Test
		void naoBloqueiaAteOLimiteLivre()
		{
			for(int i = 0; i < ThrottleLogin.LIVRE_IP; i++)
				throttle.registrarFalha("alvo@x.com", "9.9.9.9");

			assertFalse(throttle.bloqueadoPorIp("9.9.9.9"), "as falhas livres não podem bloquear");
		}

		@Test
		void bloqueiaNaFalhaSeguinteAoLimite()
		{
			for(int i = 0; i < ThrottleLogin.LIVRE_IP + 1; i++)
				throttle.registrarFalha("alvo@x.com", "9.9.9.9");

			assertTrue(throttle.bloqueadoPorIp("9.9.9.9"));
		}

		@Test
		void aEsperaExpiraSozinha()
		{
			for(int i = 0; i < ThrottleLogin.LIVRE_IP + 1; i++)
				throttle.registrarFalha("alvo@x.com", "9.9.9.9");

			relogio.avancar(ThrottleLogin.PASSO_COOLDOWN_MS - 1);
			assertTrue(throttle.bloqueadoPorIp("9.9.9.9"), "ainda dentro da espera");

			relogio.avancar(1);
			assertFalse(throttle.bloqueadoPorIp("9.9.9.9"), "passada a espera, libera");
		}

		@Test
		void aEsperaCresceACadaFalha()
		{
			for(int i = 0; i < ThrottleLogin.LIVRE_IP + 1; i++)
				throttle.registrarFalha("alvo@x.com", "9.9.9.9");

			// 6ª falha: 1 degrau (PASSO). Uma falha a mais dobra para 2 degraus.
			throttle.registrarFalha("alvo@x.com", "9.9.9.9");

			relogio.avancar(ThrottleLogin.PASSO_COOLDOWN_MS);
			assertTrue(throttle.bloqueadoPorIp("9.9.9.9"), "um degrau já não basta depois da falha extra");

			relogio.avancar(ThrottleLogin.PASSO_COOLDOWN_MS);
			assertFalse(throttle.bloqueadoPorIp("9.9.9.9"), "dois degraus liberam");
		}

		@Test
		void falhasVelhasSaemDaJanela()
		{
			for(int i = 0; i < ThrottleLogin.LIVRE_IP + 1; i++)
				throttle.registrarFalha("alvo@x.com", "9.9.9.9");

			relogio.avancar(ThrottleLogin.JANELA_MS + 1);

			assertFalse(throttle.bloqueadoPorIp("9.9.9.9"), "fora da janela, o placar zera");
		}

		@Test
		void ipsDiferentesNaoSeContaminam()
		{
			for(int i = 0; i < ThrottleLogin.LIVRE_IP + 1; i++)
				throttle.registrarFalha("alvo@x.com", "1.1.1.1");

			assertTrue(throttle.bloqueadoPorIp("1.1.1.1"));
			assertFalse(throttle.bloqueadoPorIp("2.2.2.2"), "o bloqueio é do IP que errou, não dos outros");
		}

		@Test
		void sucessoZeraOPlacar()
		{
			for(int i = 0; i < ThrottleLogin.LIVRE_IP + 1; i++)
				throttle.registrarFalha("alvo@x.com", "9.9.9.9");
			assertTrue(throttle.bloqueadoPorIp("9.9.9.9"));

			throttle.registrarSucesso("alvo@x.com", "9.9.9.9");

			assertFalse(throttle.bloqueadoPorIp("9.9.9.9"), "quem acertou a senha não fica preso");
		}
	}

	@Nested
	@DisplayName("Atraso por conta")
	class PorConta
	{
		@Test
		void semAtrasoAteOLimiteLivre()
		{
			long ultimo = 0;
			for(int i = 0; i < ThrottleLogin.LIVRE_CONTA; i++)
				ultimo = throttle.registrarFalha("alvo@x.com", ipNovo(i));

			assertEquals(0, ultimo, "dentro do limite livre não há atraso");
		}

		@Test
		void atrasoCresceERespeitaOTeto()
		{
			// IP diferente a cada falha para o bloqueio por IP não interferir na
			// contagem da conta (o atacante distribui, é esse o cenário).
			for(int i = 0; i < ThrottleLogin.LIVRE_CONTA; i++)
				throttle.registrarFalha("alvo@x.com", ipNovo(i));

			// A primeira falha punida (logo após o limite) é positiva e abaixo do teto.
			long primeiraPunida = throttle.registrarFalha("alvo@x.com", ipNovo(50));
			assertTrue(primeiraPunida > 0 && primeiraPunida < ThrottleLogin.ATRASO_CONTA_MAX_MS,
			"o primeiro atraso é positivo e abaixo do teto");

			// Empilhando mais falhas, o atraso cresce até estacionar no teto.
			long valor = primeiraPunida;
			for(int i = 51; i < 60; i++)
				valor = throttle.registrarFalha("alvo@x.com", ipNovo(i));

			assertEquals(ThrottleLogin.ATRASO_CONTA_MAX_MS, valor, "o atraso não passa do teto");
			assertTrue(primeiraPunida < valor, "o atraso é progressivo");
		}

		@Test
		void contasDiferentesNaoSeContaminam()
		{
			for(int i = 0; i < ThrottleLogin.LIVRE_CONTA + 3; i++)
				throttle.registrarFalha("vitima@x.com", ipNovo(i));

			long delayOutra = throttle.registrarFalha("terceiro@x.com", "5.5.5.5");

			assertEquals(0, delayOutra, "a conta de outra pessoa começa do zero");
		}

		@Test
		void normalizaOEmail()
		{
			for(int i = 0; i < ThrottleLogin.LIVRE_CONTA; i++)
				throttle.registrarFalha("  Alvo@X.CoM  ", ipNovo(i));

			// Mesma conta escrita diferente: a falha seguinte já é a punida.
			long delay = throttle.registrarFalha("alvo@x.com", ipNovo(50));

			assertTrue(delay > 0, "variações de caixa/espaço caem no mesmo balde");
		}
	}

	/** IP diferente por índice, para isolar a dimensão da conta nos testes. */
	private static String ipNovo(int i)
	{
		return "10.0." + (i / 256) + "." + (i % 256);
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
