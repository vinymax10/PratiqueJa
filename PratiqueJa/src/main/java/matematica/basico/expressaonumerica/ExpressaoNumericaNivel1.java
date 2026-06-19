package matematica.basico.expressaonumerica;

import matematica.GeradorExercicio;

public class ExpressaoNumericaNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Expressao1",
		".nivel1package.Expressao2",
		".nivel1package.Expressao3",
		".nivel1package.Expressao4",
		".nivel1package.Expressao7",
		".nivel1package.Expressao10",
		".nivel1package.Expressao13"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
