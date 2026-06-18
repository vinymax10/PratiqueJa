package matematica.intermediario.dizima;

import matematica.GeradorExercicio;

public class DizimaNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel3package.Dizima" + tipo));
	}
}
