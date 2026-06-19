package matematica.basico.regratres;

import matematica.GeradorExercicio;

public class RegraTresNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.RegraTres1",
		".nivel3package.RegraTres2",
		".nivel3package.RegraTres13",
		".nivel3package.RegraTres14",
		".nivel3package.RegraTres15"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
