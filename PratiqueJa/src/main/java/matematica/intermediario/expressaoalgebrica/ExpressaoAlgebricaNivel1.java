package matematica.intermediario.expressaoalgebrica;

import matematica.GeradorExercicio;

public class ExpressaoAlgebricaNivel1 extends GeradorExercicio
{
	private static final String[] TIPOS = {
		".nivel1package.Expressao1",
		".nivel1package.Expressao2"
	};

	@Override
	protected void construir()
	{
		delegar(instanciar(TIPOS[rand.nextInt(TIPOS.length)]));
	}
}
