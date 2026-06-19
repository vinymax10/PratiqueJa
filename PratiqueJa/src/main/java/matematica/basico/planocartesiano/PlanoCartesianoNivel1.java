package matematica.basico.planocartesiano;

import matematica.GeradorExercicio;

public class PlanoCartesianoNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Expressao1",
		".nivel1package.Expressao2",
		".nivel1package.Expressao3",
		".nivel1package.Expressao4",
		".nivel1package.Expressao5",
		".nivel1package.Expressao6"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
