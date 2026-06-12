package matematica.avancado.funcaoexponencial;

import matematica.GeradorExercicio;

public class FuncaoExponencialNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// 3 Image (sorteio 0-2) + 5 Expressao (sorteio 3-7)
		int sorteio = rand.nextInt(8);
		if (sorteio < 3)
			delegar(instanciar(".nivel3package.Image" + (sorteio + 1)));
		else
			delegar(instanciar(".nivel3package.Expressao" + (sorteio - 2)));
	}
}
