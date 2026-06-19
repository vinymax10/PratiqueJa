package matematica.avancado.funcaomodular;

import matematica.GeradorExercicio;

public class FuncaoModularNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Expressao1",
		".nivel3package.Expressao2",
		".nivel3package.Expressao3",
		".nivel3package.Expressao4",
		".nivel3package.Image1",
		".nivel3package.Image2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
