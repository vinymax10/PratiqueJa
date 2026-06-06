package matematica.avancado.matrizes;

import matematica.GeradorExercicio;

public class MatrizesNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(3);
		delegar(instanciar(".nivel3package.Matrizes" + tipo));
	}
}
