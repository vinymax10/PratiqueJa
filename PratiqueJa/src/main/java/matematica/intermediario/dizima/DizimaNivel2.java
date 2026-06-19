package matematica.intermediario.dizima;

import matematica.GeradorExercicio;

public class DizimaNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Dizima1",
		".nivel2package.Dizima2",
		".nivel2package.Dizima3",
		".nivel2package.Dizima4"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
