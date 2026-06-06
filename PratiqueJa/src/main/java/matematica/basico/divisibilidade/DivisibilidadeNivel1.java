package matematica.basico.divisibilidade;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.nivel1package.Divisibilidade10;
import matematica.basico.divisibilidade.nivel1package.Divisibilidade2;
import matematica.basico.divisibilidade.nivel1package.Divisibilidade3;
import matematica.basico.divisibilidade.nivel1package.Divisibilidade5;

public class DivisibilidadeNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		switch(rand.nextInt(4))
		{
			case 0:
				delegar(new Divisibilidade10());
				break;
			case 1:
				delegar(new Divisibilidade2());
				break;
			case 2:
				delegar(new Divisibilidade5());
				break;
			case 3:
				delegar(new Divisibilidade3());
				break;
		}
	}
}
