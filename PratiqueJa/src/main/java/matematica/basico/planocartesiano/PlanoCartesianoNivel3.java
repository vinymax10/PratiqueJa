package matematica.basico.planocartesiano;

import matematica.GeradorExercicio;

public class PlanoCartesianoNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Image1",
		".nivel3package.Image2",
		".nivel3package.Image3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
