package matematica.avancado.polinomios;

import matematica.GeradorExercicio;

public class PolinomiosNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(4);
		delegar(instanciar(".nivel3package.Expressao" + tipo));
	}
}
