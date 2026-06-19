package matematica.basico.mmcmdc;

import matematica.GeradorExercicio;

public class MmcMdcNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.MmcMdc1",
		".nivel3package.MmcMdc2",
		".nivel3package.MmcMdc3",
		".nivel3package.MmcMdc4",
		".nivel3package.MmcMdc7",
		".nivel3package.MmcMdc8",
		".nivel3package.MmcMdc9",
		".nivel3package.MmcMdc10",
		".nivel3package.MmcMdc11",
		".nivel3package.MmcMdc12",
		".nivel3package.MmcMdc13",
		".nivel3package.MmcMdc15",
		".nivel3package.MmcMdc18"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
