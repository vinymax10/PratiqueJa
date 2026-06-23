package matematica.basico.divisibilidade.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisores;

public class Divisibilidade18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ResolucaoDivisores resolucao = new ResolucaoDivisores();
		int number = NumerosDivisibilidade.getNumeroImpares();

		addParagrafo("Qual é a soma dos divisores naturais do número " + number + "?");
		gerarAlternativas("" + resolucao.somaDividoresResultado(number));
		for(String passo : resolucao.somaDivisores(number))
			addResolucao(passo);
	}
}
