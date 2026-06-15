package matematica.avancado.leisenocosseno;

import matematica.GeradorExercicio;

public class LeiSenoCossenoNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(7);
		delegar(instanciar(".nivel1package.Image" + tipo));
	}
}
