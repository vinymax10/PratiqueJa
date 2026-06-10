package matematica.intermediario.expressaoalgebrica;

import matematica.GeradorExercicio;

public class ExpressaoAlgebricaNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 7 + rand.nextInt(2);
		delegar(instanciar(".nivel3package.Expressao" + tipo));
	}
}
