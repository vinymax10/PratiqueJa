package matematica.intermediario.radiciacao;

import matematica.GeradorExercicio;

public class RadiciacaoNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Radiciacao1",
		".nivel3package.Radiciacao2",
		".nivel3package.Radiciacao3",
		".nivel3package.Radiciacao4",
		".nivel3package.Radiciacao5",
		".nivel3package.Radiciacao6",
		".nivel3package.Radiciacao7",
		".nivel3package.Radiciacao8",
		".nivel3package.Radiciacao9"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
