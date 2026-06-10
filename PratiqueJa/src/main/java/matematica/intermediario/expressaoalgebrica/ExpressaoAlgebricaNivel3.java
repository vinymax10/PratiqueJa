package matematica.intermediario.expressaoalgebrica;

import matematica.GeradorExercicio;

public class ExpressaoAlgebricaNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(8);
		delegar(instanciar(".nivel3package.Expressao" + tipo));
	}
}
