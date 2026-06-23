package matematica.basico.divisibilidade.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisores;

public class Divisibilidade9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ResolucaoDivisores resolucao = new ResolucaoDivisores();
		int number = NumerosDivisibilidade.getNumeroPar();

		addParagrafo("Quantos divisores naturais possui o número " + number + "?");
		gerarAlternativas("" + resolucao.numerosDividoresResultado(number));
		for(String passo : resolucao.numerosDividores(number))
			addResolucao(passo);
	}
}
