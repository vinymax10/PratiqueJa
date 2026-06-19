package matematica.basico.divisibilidade;

import matematica.GeradorExercicio;

public class DivisibilidadeNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Divisibilidade1",
		".nivel3package.Divisibilidade2",
		".nivel3package.Divisibilidade3",
		".nivel3package.Divisibilidade4",
		".nivel3package.Divisibilidade5",
		".nivel3package.Divisibilidade6",
		".nivel3package.Divisibilidade7",
		".nivel3package.Divisibilidade8",
		".nivel3package.Divisibilidade9",
		".nivel3package.Divisibilidade10",
		".nivel3package.Divisibilidade11",
		".nivel3package.Divisibilidade12",
		".nivel3package.Divisibilidade15",
		".nivel3package.Divisibilidade17",
		".nivel3package.Divisibilidade18"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
