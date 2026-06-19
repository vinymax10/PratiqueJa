package matematica.basico.sistemametrico;

import matematica.GeradorExercicio;

public class SistemaMetricoNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.SistemaMetrico1",
		".nivel3package.SistemaMetrico2",
		".nivel3package.SistemaMetrico3",
		".nivel3package.SistemaMetrico4",
		".nivel3package.SistemaMetrico5",
		".nivel3package.SistemaMetrico6",
		".nivel3package.SistemaMetrico7",
		".nivel3package.SistemaMetrico8",
		".nivel3package.SistemaMetrico9",
		".nivel3package.SistemaMetrico10",
		".nivel3package.SistemaMetrico11",
		".nivel3package.SistemaMetrico12",
		".nivel3package.SistemaMetrico13",
		".nivel3package.SistemaMetrico17",
		".nivel3package.SistemaMetrico18"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
