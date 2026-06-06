package matematica.intermediario.dizima;

import matematica.GeradorExercicio;

public class DizimaNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		delegar(instanciar(".nivel3package.Dizima"));
	}
}
