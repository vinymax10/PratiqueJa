package matematica.avancado.funcaologaritmica;

import matematica.GeradorExercicio;

public class FuncaoLogaritmicaNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// 0,1 → Image1,Image2 | 2..6 → Expressao1..5
		int sorteio = rand.nextInt(7);
		if (sorteio < 2)
			delegar(instanciar(".nivel2package.Image" + (sorteio + 1)));
		else
			delegar(instanciar(".nivel2package.Expressao" + (sorteio - 1)));
	}
}
