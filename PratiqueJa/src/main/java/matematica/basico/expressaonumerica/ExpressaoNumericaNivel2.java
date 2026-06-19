package matematica.basico.expressaonumerica;

import matematica.GeradorExercicio;

public class ExpressaoNumericaNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Expressao1",
		".nivel2package.Expressao2",
		".nivel2package.Expressao3",
		".nivel2package.Expressao4",
		".nivel2package.Expressao5",
		".nivel2package.Expressao6",
		".nivel2package.Expressao7",
		".nivel2package.Expressao8",
		".nivel2package.Expressao9",
		".nivel2package.Expressao10",
		".nivel2package.Expressao11",
		".nivel2package.Expressao12",
		".nivel2package.Expressao13",
		".nivel2package.Expressao14",
		".nivel2package.Expressao16"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
