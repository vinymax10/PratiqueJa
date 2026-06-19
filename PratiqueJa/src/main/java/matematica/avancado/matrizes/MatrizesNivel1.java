package matematica.avancado.matrizes;

import matematica.GeradorExercicio;

public class MatrizesNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Matrizes1",
		".nivel1package.Matrizes2",
		".nivel1package.Matrizes3",
		".nivel1package.Matrizes4",
		".nivel1package.Matrizes5"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
