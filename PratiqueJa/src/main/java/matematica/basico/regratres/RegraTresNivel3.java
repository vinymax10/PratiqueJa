package matematica.basico.regratres;

import matematica.GeradorExercicio;

public class RegraTresNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(2);
		delegar(instanciar(".nivel3package.RegraTres" + tipo));
	}
}
