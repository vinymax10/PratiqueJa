package matematica.intermediario.expressaoalgebrica;

import matematica.GeradorExercicio;

public class ExpressaoAlgebricaNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		delegar(instanciar(".nivel3package.Expressao1"));
	}
}
