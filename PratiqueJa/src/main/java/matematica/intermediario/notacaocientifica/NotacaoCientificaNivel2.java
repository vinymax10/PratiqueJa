package matematica.intermediario.notacaocientifica;

import matematica.GeradorExercicio;

public class NotacaoCientificaNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.NotacaoCientifica1",
		".nivel2package.NotacaoCientifica2",
		".nivel2package.NotacaoCientifica3",
		".nivel2package.NotacaoCientifica4",
		".nivel2package.NotacaoCientifica5",
		".nivel2package.NotacaoCientifica6",
		".nivel2package.NotacaoCientifica7",
		".nivel2package.NotacaoCientifica8"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
