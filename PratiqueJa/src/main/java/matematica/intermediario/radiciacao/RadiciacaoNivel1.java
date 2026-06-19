package matematica.intermediario.radiciacao;

import matematica.GeradorExercicio;

public class RadiciacaoNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Radiciacao1",
		".nivel1package.Radiciacao2",
		".nivel1package.Radiciacao3",
		".nivel1package.Radiciacao4"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
