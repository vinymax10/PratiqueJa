package matematica.avancado.estatistica;

import matematica.GeradorExercicio;

public class EstatisticaNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Estatistica1",
		".nivel2package.Estatistica2",
		".nivel2package.Estatistica3",
		".nivel2package.Estatistica4"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
