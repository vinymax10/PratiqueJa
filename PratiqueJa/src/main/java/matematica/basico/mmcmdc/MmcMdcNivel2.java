package matematica.basico.mmcmdc;

import matematica.GeradorExercicio;

public class MmcMdcNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.MmcMdc1",
		".nivel2package.MmcMdc2",
		".nivel2package.MmcMdc4",
		".nivel2package.MmcMdc5",
		".nivel2package.MmcMdc6",
		".nivel2package.MmcMdc7",
		".nivel2package.MmcMdc8",
		".nivel2package.MmcMdc9",
		".nivel2package.MmcMdc11",
		".nivel2package.MmcMdc12",
		".nivel2package.MmcMdc16"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
