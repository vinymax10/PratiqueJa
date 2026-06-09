package matematica.basico.planocartesiano;

import matematica.GeradorExercicio;

public class PlanoCartesianoNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(3);
		delegar(instanciar(".nivel2package.Image" + tipo));
	}
}
