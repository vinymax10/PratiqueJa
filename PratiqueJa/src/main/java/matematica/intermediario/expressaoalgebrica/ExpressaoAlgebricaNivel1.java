package matematica.intermediario.expressaoalgebrica;

import matematica.GeradorExercicio;

public class ExpressaoAlgebricaNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		delegar(instanciar(".nivel1package.Expressao1"));
	}
}
