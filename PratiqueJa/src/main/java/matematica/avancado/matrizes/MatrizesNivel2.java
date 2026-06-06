package matematica.avancado.matrizes;

import matematica.GeradorExercicio;

public class MatrizesNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(5);
		delegar(instanciar(".nivel2package.Matrizes" + tipo));
	}
}
