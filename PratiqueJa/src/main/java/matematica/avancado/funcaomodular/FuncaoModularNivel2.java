package matematica.avancado.funcaomodular;

import matematica.GeradorExercicio;

public class FuncaoModularNivel2 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel2package.Expressao1",
		".nivel2package.Expressao2",
		".nivel2package.Expressao3",
		".nivel2package.Expressao4",
		".nivel2package.Expressao5",
		".nivel2package.Image1",
		".nivel2package.Image2",
		".nivel2package.Image3"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
