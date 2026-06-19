package matematica.basico.numerosprimos;

import matematica.GeradorExercicio;

public class NumerosPrimosNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Primo1",
		".nivel2package.Primo2",
		".nivel2package.Primo3",
		".nivel2package.Primo4",
		".nivel2package.Primo5",
		".nivel2package.Primo6",
		".nivel2package.Primo7",
		".nivel2package.Primo8",
		".nivel2package.Primo9",
		".nivel2package.Primo10",
		".nivel2package.Primo11",
		".nivel2package.Primo12",
		".nivel2package.Primo13",
		".nivel2package.Primo14",
		".nivel2package.Primo15",
		".nivel2package.Primo17",
		".nivel2package.Primo18"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
