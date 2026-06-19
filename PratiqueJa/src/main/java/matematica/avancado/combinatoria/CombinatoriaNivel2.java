package matematica.avancado.combinatoria;

import matematica.GeradorExercicio;

public class CombinatoriaNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Combinatoria1",
		".nivel2package.Combinatoria2",
		".nivel2package.Combinatoria3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
