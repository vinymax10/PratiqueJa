package matematica.basico.planocartesiano;

import matematica.GeradorExercicio;

public class PlanoCartesianoNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Image1",
		".nivel2package.Image2",
		".nivel2package.Image3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
