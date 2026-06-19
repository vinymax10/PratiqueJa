package matematica.basico.racionais;

import matematica.GeradorExercicio;

public class RacionaisNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Racionais1",
		".nivel1package.Racionais2",
		".nivel1package.Racionais3",
		".nivel1package.Racionais4",
		".nivel1package.Racionais5",
		".nivel1package.Racionais6",
		".nivel1package.Racionais7",
		".nivel1package.Racionais8",
		".nivel1package.Racionais9",
		".nivel1package.Racionais10",
		".nivel1package.Racionais11",
		".nivel1package.Racionais12"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
