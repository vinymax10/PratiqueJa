package matematica.avancado.estatistica;

import matematica.GeradorExercicio;

public class EstatisticaNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Estatistica1",
		".nivel1package.Estatistica2",
		".nivel1package.Estatistica3",
		".nivel1package.Estatistica4"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
