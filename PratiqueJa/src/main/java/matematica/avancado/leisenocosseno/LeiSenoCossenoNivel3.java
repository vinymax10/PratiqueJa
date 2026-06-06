package matematica.avancado.leisenocosseno;

import matematica.GeradorExercicio;

public class LeiSenoCossenoNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(9);
		delegar(instanciar(".nivel3package.Image" + tipo));
	}
}
