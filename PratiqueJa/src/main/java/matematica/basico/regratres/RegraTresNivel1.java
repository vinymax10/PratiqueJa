package matematica.basico.regratres;

import matematica.GeradorExercicio;

public class RegraTresNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel1package.RegraTres" + tipo));
	}
}
