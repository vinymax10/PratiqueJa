package matematica.basico.areaperimetro;

import matematica.GeradorExercicio;

public class AreaPerimetroNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(39);
		delegar(instanciar(".nivel2package.Image" + tipo));
	}
}
