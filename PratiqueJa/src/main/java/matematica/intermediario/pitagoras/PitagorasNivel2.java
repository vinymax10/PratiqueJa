package matematica.intermediario.pitagoras;

import matematica.GeradorExercicio;

public class PitagorasNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(6);
		delegar(instanciar(".nivel2package.Image" + tipo));
	}
}
