package matematica.basico.conjuntos;

import matematica.GeradorExercicio;

public class ConjuntosNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Conjuntos1",
		".nivel3package.Conjuntos2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
