package matematica.avancado.funcaomodular;

import matematica.GeradorExercicio;

public class FuncaoModularNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// 0,1,2 → Image1,Image2,Image3 | 3..7 → Expressao1..5
		int sorteio = rand.nextInt(8);
		if (sorteio < 3)
			delegar(instanciar(".nivel2package.Image" + (sorteio + 1)));
		else
			delegar(instanciar(".nivel2package.Expressao" + (sorteio - 2)));
	}
}
