package matematica.basico.divisibilidade;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.nivel2package.Divisibilidade4;
import matematica.basico.divisibilidade.nivel2package.Divisibilidade6;
import matematica.basico.divisibilidade.nivel2package.Divisibilidade7;
import matematica.basico.divisibilidade.nivel2package.Divisibilidade8;
import matematica.basico.divisibilidade.nivel2package.Divisibilidade9;

public class DivisibilidadeNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		switch(rand.nextInt(5))
		{
			case 0:
				delegar(new Divisibilidade6());
				break;
			case 1:
				delegar(new Divisibilidade9());
				break;
			case 2:
				delegar(new Divisibilidade4());
				break;
			case 3:
				delegar(new Divisibilidade7());
				break;
			case 4:
				delegar(new Divisibilidade8());
				break;
		}
	}
}
