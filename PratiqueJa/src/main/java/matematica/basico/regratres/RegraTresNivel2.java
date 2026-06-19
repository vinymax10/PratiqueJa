package matematica.basico.regratres;

import matematica.GeradorExercicio;

public class RegraTresNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.RegraTres1",
		".nivel2package.RegraTres2",
		".nivel2package.RegraTres3",
		".nivel2package.RegraTres7",
		".nivel2package.RegraTres8",
		".nivel2package.RegraTres10",
		".nivel2package.RegraTres11",
		".nivel2package.RegraTres16"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
