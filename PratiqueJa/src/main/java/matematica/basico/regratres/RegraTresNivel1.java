package matematica.basico.regratres;

import matematica.GeradorExercicio;

public class RegraTresNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.RegraTres1",
		".nivel1package.RegraTres2",
		".nivel1package.RegraTres3",
		".nivel1package.RegraTres6",
		".nivel1package.RegraTres7",
		".nivel1package.RegraTres8",
		".nivel1package.RegraTres9",
		".nivel1package.RegraTres10",
		".nivel1package.RegraTres11",
		".nivel1package.RegraTres12"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
