package matematica.intermediario.razoestrigonometricas;

import matematica.GeradorExercicio;

public class RazoesTrigonometricasNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(12);
		delegar(instanciar(".nivel2package.Image" + tipo));
	}
}
