package matematica.basico.expressaonumerica;

import matematica.GeradorExercicio;

public class ExpressaoNumericaNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel2package.Expressao" + tipo));
	}
}
