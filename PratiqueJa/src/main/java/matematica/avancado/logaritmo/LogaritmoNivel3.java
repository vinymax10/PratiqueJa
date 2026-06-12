package matematica.avancado.logaritmo;

import matematica.GeradorExercicio;

public class LogaritmoNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(5);
		delegar(instanciar(".nivel3package.Expressao" + tipo));
	}
}
