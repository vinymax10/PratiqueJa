package matematica.basico.conjuntos;

import matematica.GeradorExercicio;

public class ConjuntosNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(2);
		delegar(instanciar(".nivel3package.Conjuntos" + tipo));
	}
}
