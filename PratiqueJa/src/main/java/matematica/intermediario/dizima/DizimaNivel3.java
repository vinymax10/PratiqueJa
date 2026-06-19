package matematica.intermediario.dizima;

import matematica.GeradorExercicio;

public class DizimaNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Dizima1",
		".nivel3package.Dizima2",
		".nivel3package.Dizima3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
