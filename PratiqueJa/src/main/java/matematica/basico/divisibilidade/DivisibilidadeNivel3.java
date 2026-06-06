package matematica.basico.divisibilidade;

import matematica.GeradorExercicio;

public class DivisibilidadeNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(4);
		delegar(instanciar(".nivel3package.Divisibilidade" + tipo));
	}
}
