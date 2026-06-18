package matematica.avancado.estatistica;

import matematica.GeradorExercicio;

public class EstatisticaNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel1package.Estatistica" + tipo));
	}
}
