package matematica.basico.sistemametrico;

import matematica.GeradorExercicio;

public class SistemaMetricoNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel2package.SistemaMetrico" + tipo));
	}
}
