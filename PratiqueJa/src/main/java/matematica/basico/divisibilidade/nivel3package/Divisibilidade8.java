package matematica.basico.divisibilidade.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisores;

public class Divisibilidade8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ResolucaoDivisores resolucao = new ResolucaoDivisores();
		int number = NumerosDivisibilidade.getNumero();

		addParagrafo("Quantos divisores naturais ímpares possui o número " + number + "?");
		gerarAlternativas("" + resolucao.numerosDividoresResultadoPares(number, false));
		for(String passo : resolucao.numerosDividoresParesImpares(number, false))
			addResolucao(passo);
	}
}
