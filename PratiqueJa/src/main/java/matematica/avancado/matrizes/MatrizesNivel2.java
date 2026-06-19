package matematica.avancado.matrizes;

import matematica.GeradorExercicio;

public class MatrizesNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Matrizes1",
		".nivel2package.Matrizes2",
		".nivel2package.Matrizes3",
		".nivel2package.Matrizes4",
		".nivel2package.Matrizes5",
		".nivel2package.Matrizes6",
		".nivel2package.Matrizes7"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
