package matematica.avancado.combinatoria;

import matematica.GeradorExercicio;

public class CombinatoriaNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(4);
		delegar(instanciar(".nivel3package.Combinatoria" + tipo));
	}
}
