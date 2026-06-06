package matematica.basico.areaperimetro;

import matematica.GeradorExercicio;

public class AreaPerimetroNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(19);
		delegar(instanciar(".nivel1package.Image" + tipo));
	}
}
