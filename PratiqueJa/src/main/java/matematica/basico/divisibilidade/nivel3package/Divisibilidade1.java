package matematica.basico.divisibilidade.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisores;

public class Divisibilidade1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ResolucaoDivisores resolucaoDivisores = new ResolucaoDivisores();

		int number = NumerosDivisibilidade.getNumero();

		addParagrafo("Quantos divisores naturais possui o número " + number + "?");
		gerarAlternativas("" + resolucaoDivisores.numerosDividoresResultado(number));
		for(String passo : resolucaoDivisores.numerosDividores(number))
			addResolucao(passo);
	}
}
