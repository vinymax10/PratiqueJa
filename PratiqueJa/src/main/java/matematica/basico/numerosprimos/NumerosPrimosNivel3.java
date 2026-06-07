package matematica.basico.numerosprimos;

import matematica.GeradorExercicio;

public class NumerosPrimosNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(5);
		delegar(instanciar(".nivel3package.Primo" + tipo));
	}
}
