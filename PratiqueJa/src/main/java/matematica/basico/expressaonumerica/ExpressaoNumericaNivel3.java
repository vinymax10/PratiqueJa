package matematica.basico.expressaonumerica;

import matematica.GeradorExercicio;

public class ExpressaoNumericaNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Expressao1",
		".nivel3package.Expressao2",
		".nivel3package.Expressao3",
		".nivel3package.Expressao4",
		".nivel3package.Expressao5",
		".nivel3package.Expressao6",
		".nivel3package.Expressao7",
		".nivel3package.Expressao8",
		".nivel3package.Expressao9",
		".nivel3package.Expressao10",
		".nivel3package.Expressao11",
		".nivel3package.Expressao12",
		".nivel3package.Expressao13",
		".nivel3package.Expressao14",
		".nivel3package.Expressao15"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
