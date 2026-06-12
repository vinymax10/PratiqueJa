package matematica.avancado.pa;

import matematica.GeradorExercicio;

public class PANivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(6);
		delegar(instanciar(".nivel2package.Expressao" + tipo));
	}
}
