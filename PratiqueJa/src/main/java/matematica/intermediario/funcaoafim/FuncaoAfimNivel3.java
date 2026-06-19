package matematica.intermediario.funcaoafim;

import matematica.GeradorExercicio;

public class FuncaoAfimNivel3 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel3package.Expressao1",
		".nivel3package.Expressao2",
		".nivel3package.Image1",
		".nivel3package.Image2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
