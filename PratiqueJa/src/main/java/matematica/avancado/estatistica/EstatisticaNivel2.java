package matematica.avancado.estatistica;

import matematica.GeradorExercicio;

public class EstatisticaNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(4);
		delegar(instanciar(".nivel2package.Estatistica" + tipo));
	}
}
