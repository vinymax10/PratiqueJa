package matematica.basico.divisibilidade;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.nivel1package.*;

public class DivisibilidadeNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		GeradorExercicio[] tipos = {
			new Divisibilidade2(), new Divisibilidade3(), new Divisibilidade5(), new Divisibilidade10(),
			new Mc2(), new Mc3(), new Mc5(), new Mc10(),
			new Resto2(), new Resto3(), new Resto5(), new Resto10(),
			new Nao2(), new Nao3(), new Nao5(), new Nao10(),
			new EhDivisor2(), new EhDivisor5()
		};
		delegar(tipos[rand.nextInt(tipos.length)]);
	}
}
