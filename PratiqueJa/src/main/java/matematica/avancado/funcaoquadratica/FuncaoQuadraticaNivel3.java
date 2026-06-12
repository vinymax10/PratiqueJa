package matematica.avancado.funcaoquadratica;

import matematica.GeradorExercicio;

public class FuncaoQuadraticaNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int sorteio = rand.nextInt(9);
		if(sorteio < 6)
			delegar(instanciar(".nivel3package.Image" + (sorteio + 1)));
		else
			delegar(instanciar(".nivel3package.Expressao" + (sorteio - 5)));
	}
}
