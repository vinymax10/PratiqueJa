package matematica.basico.racionais;

import matematica.GeradorExercicio;

public class RacionaisNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(2);
		delegar(instanciar(".nivel2package.Racionais" + tipo));
	}
}
