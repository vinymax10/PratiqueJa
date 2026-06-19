package matematica.avancado.combinatoria;

import matematica.GeradorExercicio;

public class CombinatoriaNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Combinatoria1",
		".nivel1package.Combinatoria2",
		".nivel1package.Combinatoria3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
