package matematica.intermediario.dizima;

import matematica.GeradorExercicio;

public class DizimaNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(2);
		delegar(instanciar(".nivel2package.Dizima" + tipo));
	}
}
