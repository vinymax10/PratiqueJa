package matematica.avancado.combinatoria;

import matematica.GeradorExercicio;

public class CombinatoriaNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Combinatoria1",
		".nivel3package.Combinatoria2",
		".nivel3package.Combinatoria3",
		".nivel3package.Combinatoria4"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
