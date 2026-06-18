package matematica.avancado.matrizes;

import matematica.GeradorExercicio;

public class MatrizesNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel1package.Matrizes" + tipo));
	}
}
