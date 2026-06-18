package matematica.basico.mmcmdc;

import matematica.GeradorExercicio;

public class MmcMdcNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel2package.MmcMdc" + tipo));
	}
}
