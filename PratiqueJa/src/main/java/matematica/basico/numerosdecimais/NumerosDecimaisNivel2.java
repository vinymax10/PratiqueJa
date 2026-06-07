package matematica.basico.numerosdecimais;

import matematica.GeradorExercicio;

public class NumerosDecimaisNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(5);
		delegar(instanciar(".nivel2package.Decimal" + tipo));
	}
}
