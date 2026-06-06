package matematica.avancado.combinatoria;

import matematica.GeradorExercicio;

public class CombinatoriaNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(2);
		delegar(instanciar(".nivel2package.Combinatoria" + tipo));
	}
}
