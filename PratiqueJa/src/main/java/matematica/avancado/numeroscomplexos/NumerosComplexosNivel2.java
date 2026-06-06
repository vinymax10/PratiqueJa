package matematica.avancado.numeroscomplexos;

import matematica.GeradorExercicio;

public class NumerosComplexosNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = 1 + rand.nextInt(3);
		delegar(instanciar(".nivel2package.Expressao" + tipo));
	}
}
