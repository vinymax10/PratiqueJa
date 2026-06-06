package matematica.intermediario.dizima;

import matematica.GeradorExercicio;

public class DizimaNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		delegar(instanciar(".nivel1package.Dizima1"));
	}
}
