package matematica.basico.divisibilidade;

import matematica.GeradorExercicio;

public class DivisibilidadeNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Divisibilidade4",
		".nivel2package.Divisibilidade6",
		".nivel2package.Divisibilidade7",
		".nivel2package.Divisibilidade8",
		".nivel2package.Divisibilidade9",
		".nivel2package.Mc4",
		".nivel2package.Nao7",
		".nivel2package.Por11",
		".nivel2package.Resto4"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
