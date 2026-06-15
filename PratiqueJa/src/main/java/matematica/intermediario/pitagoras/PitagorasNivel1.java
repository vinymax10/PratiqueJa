package matematica.intermediario.pitagoras;

import matematica.GeradorExercicio;

public class PitagorasNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(6);
		delegar(instanciar(".nivel1package.Image" + tipo));
	}
}
