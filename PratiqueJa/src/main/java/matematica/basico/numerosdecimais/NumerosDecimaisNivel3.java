package matematica.basico.numerosdecimais;

import matematica.GeradorExercicio;

public class NumerosDecimaisNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(5);
		delegar(instanciar(".nivel3package.Decimal" + tipo));
	}
}
