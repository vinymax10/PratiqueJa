package matematica.basico.conjuntos;

import matematica.GeradorExercicio;

public class ConjuntosNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(7);
		delegar(instanciar(".nivel2package.Image" + tipo));
	}
}
