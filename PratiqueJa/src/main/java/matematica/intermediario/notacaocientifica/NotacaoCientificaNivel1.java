package matematica.intermediario.notacaocientifica;

import matematica.GeradorExercicio;

public class NotacaoCientificaNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.NotacaoCientifica1",
		".nivel1package.NotacaoCientifica2",
		".nivel1package.NotacaoCientifica3",
		".nivel1package.NotacaoCientifica4",
		".nivel1package.NotacaoCientifica5"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
