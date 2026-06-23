package matematica.basico.divisibilidade.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisibilidade;

public class Divisibilidade6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int ref = 6;
		int number = 1000 + rand.nextInt(9000);

		boolean correta = rand.nextBoolean();
		int resto = number % ref;
		if(correta)
			number += (ref - resto);
		else if(resto == 0)
			number++;

		addParagrafo("" + number + " é divisível por " + ref + "?");
		gerarAlternativasBoolean(correta);
		for(String passo : ResolucaoDivisibilidade.resolucao6(correta, number))
			addResolucao(passo);
	}
}
