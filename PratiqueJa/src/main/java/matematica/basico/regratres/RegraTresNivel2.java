package matematica.basico.regratres;

import matematica.GeradorExercicio;

public class RegraTresNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(1);
		delegar(instanciar(".nivel2package.RegraTres" + tipo));
	}
}
