package matematica.avancado.funcaomodular;

import matematica.GeradorExercicio;

public class FuncaoModularNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// 0,1 → Image1,Image2 | 2..5 → Expressao1..4
		int sorteio = rand.nextInt(6);
		if (sorteio < 2)
			delegar(instanciar(".nivel3package.Image" + (sorteio + 1)));
		else
			delegar(instanciar(".nivel3package.Expressao" + (sorteio - 1)));
	}
}
