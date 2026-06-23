package matematica.basico.divisibilidade.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisores;

public class Divisibilidade4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ResolucaoDivisores resolucaoDivisores = new ResolucaoDivisores();

		int number = NumerosDivisibilidade.getNumero();

		addParagrafo("Qual é a soma dos divisores naturais do número " + number + "?");
		gerarAlternativas("" + resolucaoDivisores.somaDividoresResultado(number));
		for(String passo : resolucaoDivisores.somaDivisores(number))
			addResolucao(passo);
	}
}
