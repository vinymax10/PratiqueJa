package matematica.intermediario.potenciacao;

import matematica.GeradorExercicio;

public class PotenciacaoNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Potenciacao1",
		".nivel1package.Potenciacao2"
	};

	/** Exercício de expoente zero (\(a^0 = 1\)): sorteado à parte para não dominar o assunto. */
	private static final String EXPOENTE_ZERO = ".nivel1package.Potenciacao3";
	private static final int CHANCE_EXPOENTE_ZERO = 5; // em 100

	@Override
	protected void construir()
	{
		String tipo = rand.nextInt(100) < CHANCE_EXPOENTE_ZERO
			? EXPOENTE_ZERO
			: TIPOS[rand.nextInt(TIPOS.length)];

		delegar(instanciar(tipo));
	}
}
