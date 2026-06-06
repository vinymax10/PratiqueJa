package matematica.intermediario.pitagoras;

import matematica.GeradorExercicio;

public class PitagorasNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(2);
		delegar(instanciar(".nivel3package.Exercicio" + tipo));
	}
}
