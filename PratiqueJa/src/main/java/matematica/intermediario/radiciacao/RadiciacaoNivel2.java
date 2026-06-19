package matematica.intermediario.radiciacao;

import matematica.GeradorExercicio;

public class RadiciacaoNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Radiciacao1",
		".nivel2package.Radiciacao2",
		".nivel2package.Radiciacao3",
		".nivel2package.Radiciacao4",
		".nivel2package.Radiciacao5",
		".nivel2package.Radiciacao6",
		".nivel2package.Radiciacao7",
		".nivel2package.Radiciacao8",
		".nivel2package.Radiciacao9",
		".nivel2package.Radiciacao10",
		".nivel2package.Radiciacao11",
		".nivel2package.Radiciacao12",
		".nivel2package.Radiciacao13",
		".nivel2package.Radiciacao14"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
