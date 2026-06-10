package matematica.intermediario.dizima;

import matematica.GeradorExercicio;

public class DizimaNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(3);
		delegar(instanciar(".nivel1package.Dizima" + tipo));
	}
}
