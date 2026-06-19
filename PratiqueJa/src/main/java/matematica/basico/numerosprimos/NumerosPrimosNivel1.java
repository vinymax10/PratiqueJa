package matematica.basico.numerosprimos;

import matematica.GeradorExercicio;

public class NumerosPrimosNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Primo1",
		".nivel1package.Primo2",
		".nivel1package.Primo3",
		".nivel1package.Primo4",
		".nivel1package.Primo5",
		".nivel1package.Primo6",
		".nivel1package.Primo7",
		".nivel1package.Primo8",
		".nivel1package.Primo9",
		".nivel1package.Primo10",
		".nivel1package.Primo11",
		".nivel1package.Primo12",
		".nivel1package.Primo13",
		".nivel1package.Primo14",
		".nivel1package.Primo15",
		".nivel1package.Primo16"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
