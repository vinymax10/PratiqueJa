package matematica.avancado.funcaologaritmica;

import matematica.GeradorExercicio;

public class FuncaoLogaritmicaNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Expressao1",
		".nivel1package.Expressao2",
		".nivel1package.Expressao3",
		".nivel1package.Expressao4"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
