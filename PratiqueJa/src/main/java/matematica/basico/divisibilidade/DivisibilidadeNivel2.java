package matematica.basico.divisibilidade;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.nivel2package.*;

public class DivisibilidadeNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		GeradorExercicio[] tipos = {
			new Divisibilidade4(), new Divisibilidade6(), new Divisibilidade7(), new Divisibilidade8(), new Divisibilidade9(),
			new Por11(),
			new Mc4(), new Mc6(), new Mc8(), new Mc9(), new Mc11(),
			new Resto4(), new Resto6(), new Resto7(), new Resto8(), new Resto9(), new Resto11(),
			new Nao7()
		};
		delegar(tipos[rand.nextInt(tipos.length)]);
	}
}
