package matematica.basico.areaperimetro;

import matematica.GeradorExercicio;

public class AreaPerimetroNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(20);
		delegar(instanciar(".nivel3package.Image" + tipo));
	}
}
