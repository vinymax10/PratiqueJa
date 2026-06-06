package matematica.basico.mmcmdc;

import matematica.GeradorExercicio;

public class MmcMdcNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(1);
		delegar(instanciar(".nivel3package.MmcMdc" + tipo));
	}
}
