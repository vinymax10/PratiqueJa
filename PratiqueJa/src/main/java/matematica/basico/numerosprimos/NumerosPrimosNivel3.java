package matematica.basico.numerosprimos;

import matematica.GeradorExercicio;

public class NumerosPrimosNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Primo1",
		".nivel3package.Primo2",
		".nivel3package.Primo3",
		".nivel3package.Primo4",
		".nivel3package.Primo5",
		".nivel3package.Primo7",
		".nivel3package.Primo8",
		".nivel3package.Primo9",
		".nivel3package.Primo10",
		".nivel3package.Primo11",
		".nivel3package.Primo12",
		".nivel3package.Primo13",
		".nivel3package.Primo14",
		".nivel3package.Primo18"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
