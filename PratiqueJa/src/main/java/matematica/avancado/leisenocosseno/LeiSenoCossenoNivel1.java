package matematica.avancado.leisenocosseno;

import matematica.GeradorExercicio;

public class LeiSenoCossenoNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Image1",
		".nivel1package.Image2",
		".nivel1package.Image3",
		".nivel1package.Image4",
		".nivel1package.Image5",
		".nivel1package.Image6",
		".nivel1package.Image7"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
