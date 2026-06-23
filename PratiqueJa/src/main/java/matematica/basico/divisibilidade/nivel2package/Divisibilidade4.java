package matematica.basico.divisibilidade.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisibilidade;

public class Divisibilidade4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int ref = 4;
		int number = 1000 + rand.nextInt(9000);

		boolean correta = rand.nextBoolean();
		int resto = number % ref;
		if(correta)
			number += (ref - resto);
		else if(resto == 0)
			number++;

		addParagrafo("" + number + " é divisível por " + ref + "?");
		gerarAlternativasBoolean(correta);
		for(String passo : ResolucaoDivisibilidade.resolucao4(correta, number))
			addResolucao(passo);
	}
}
