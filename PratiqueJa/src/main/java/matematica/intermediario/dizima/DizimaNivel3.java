package matematica.intermediario.dizima;

import matematica.GeradorExercicio;

public class DizimaNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		switch(rand.nextInt(3))
		{
			case 1:  delegar(instanciar(".nivel3package.Dizima2")); break;
			case 2:  delegar(instanciar(".nivel3package.Dizima3")); break;
			default: delegar(instanciar(".nivel3package.Dizima"));  break;
		}
	}
}
