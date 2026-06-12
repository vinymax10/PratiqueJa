package matematica.avancado.funcaoexponencial;

import matematica.GeradorExercicio;

public class FuncaoExponencialNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// 3 Image (sorteio 0-2) + 6 Expressao (sorteio 3-8)
		int sorteio = rand.nextInt(9);
		if (sorteio < 3)
			delegar(instanciar(".nivel2package.Image" + (sorteio + 1)));
		else
			delegar(instanciar(".nivel2package.Expressao" + (sorteio - 2)));
	}
}
