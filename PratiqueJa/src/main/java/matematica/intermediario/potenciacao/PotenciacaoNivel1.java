package matematica.intermediario.potenciacao;

import matematica.GeradorExercicio;

public class PotenciacaoNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Potenciacao1",
		".nivel1package.Potenciacao2",
		".nivel1package.Potenciacao3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
