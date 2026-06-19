package matematica.avancado.funcaoquadratica;

import matematica.GeradorExercicio;

public class FuncaoQuadraticaNivel2 extends GeradorExercicio
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
		".nivel2package.Expressao9"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
