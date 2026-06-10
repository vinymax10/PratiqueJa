package matematica.intermediario.expressaoalgebrica;

import matematica.GeradorExercicio;

public class ExpressaoAlgebricaNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(2);
		delegar(instanciar(".nivel1package.Expressao" + tipo));
	}
}
