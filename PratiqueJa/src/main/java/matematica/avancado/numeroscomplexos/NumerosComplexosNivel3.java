package matematica.avancado.numeroscomplexos;

import matematica.GeradorExercicio;

public class NumerosComplexosNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(18);
		delegar(instanciar(".nivel3package.Expressao" + tipo));
	}
}
