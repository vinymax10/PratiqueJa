package matematica.basico.divisibilidade.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisibilidade;

public class Divisibilidade3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int ref = 3;
		int number = 1000 + rand.nextInt(9000);

		boolean correta = rand.nextBoolean();
		int resto = number % ref;
		if(correta)
			number += (ref - resto);
		else if(resto == 0)
			number++;

		addParagrafo("" + number + " é divisível por " + ref + "?");
		gerarAlternativasBoolean(correta);
		setResolucao(ResolucaoDivisibilidade.resolucao3(correta, number));
	}
}
