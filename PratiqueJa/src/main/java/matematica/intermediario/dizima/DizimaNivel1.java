package matematica.intermediario.dizima;

import matematica.GeradorExercicio;

public class DizimaNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Dizima1",
		".nivel1package.Dizima2",
		".nivel1package.Dizima3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
