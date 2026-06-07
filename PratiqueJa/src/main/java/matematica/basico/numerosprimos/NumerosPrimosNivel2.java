package matematica.basico.numerosprimos;

import matematica.GeradorExercicio;

public class NumerosPrimosNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(5);
		delegar(instanciar(".nivel2package.Primo" + tipo));
	}
}
