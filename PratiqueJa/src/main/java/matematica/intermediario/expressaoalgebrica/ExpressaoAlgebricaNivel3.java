package matematica.intermediario.expressaoalgebrica;

import matematica.GeradorExercicio;

public class ExpressaoAlgebricaNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Expressao1",
		".nivel3package.Expressao2",
		".nivel3package.Expressao3",
		".nivel3package.Expressao4",
		".nivel3package.Expressao5",
		".nivel3package.Expressao6",
		".nivel3package.Expressao7",
		".nivel3package.Expressao8"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
