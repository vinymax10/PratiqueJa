package matematica.intermediario.notacaocientifica;

import matematica.GeradorExercicio;

public class NotacaoCientificaNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.NotacaoCientifica1",
		".nivel3package.NotacaoCientifica2",
		".nivel3package.NotacaoCientifica3",
		".nivel3package.NotacaoCientifica4",
		".nivel3package.NotacaoCientifica5",
		".nivel3package.NotacaoCientifica6"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
