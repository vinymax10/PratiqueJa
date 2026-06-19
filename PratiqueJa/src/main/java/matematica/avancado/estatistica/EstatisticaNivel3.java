package matematica.avancado.estatistica;

import matematica.GeradorExercicio;

public class EstatisticaNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Estatistica1",
		".nivel3package.Estatistica2",
		".nivel3package.Estatistica3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
