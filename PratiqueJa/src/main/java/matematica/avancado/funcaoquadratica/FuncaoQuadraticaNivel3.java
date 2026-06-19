package matematica.avancado.funcaoquadratica;

import matematica.GeradorExercicio;

public class FuncaoQuadraticaNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Expressao1",
		".nivel3package.Expressao2",
		".nivel3package.Expressao3",
		".nivel3package.Image1",
		".nivel3package.Image2",
		".nivel3package.Image3",
		".nivel3package.Image4",
		".nivel3package.Image5",
		".nivel3package.Image6"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
