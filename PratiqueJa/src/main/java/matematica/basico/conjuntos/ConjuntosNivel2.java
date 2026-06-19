package matematica.basico.conjuntos;

import matematica.GeradorExercicio;

public class ConjuntosNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Image1",
		".nivel2package.Image2",
		".nivel2package.Image3",
		".nivel2package.Image4",
		".nivel2package.Image5",
		".nivel2package.Image6",
		".nivel2package.Image7"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
