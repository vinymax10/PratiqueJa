package matematica.avancado.combinatoria;

import matematica.GeradorExercicio;

public class CombinatoriaNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel1package.Combinatoria" + tipo));
	}
}
