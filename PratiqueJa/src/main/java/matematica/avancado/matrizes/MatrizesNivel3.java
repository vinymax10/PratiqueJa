package matematica.avancado.matrizes;

import matematica.GeradorExercicio;

public class MatrizesNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Matrizes1",
		".nivel3package.Matrizes2",
		".nivel3package.Matrizes3",
		".nivel3package.Matrizes4",
		".nivel3package.Matrizes5"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
