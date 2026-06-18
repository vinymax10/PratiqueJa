package matematica.basico.racionais;

import matematica.GeradorExercicio;

public class RacionaisNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel3package.Racionais" + tipo));
	}
}
