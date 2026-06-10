package matematica.basico.divisibilidade.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisores;

public class Divisibilidade3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ResolucaoDivisores resolucaoDivisores = new ResolucaoDivisores();

		int number = NumerosDivisibilidade.getNumero();

		addParagrafo("Quantos divisores naturais ímpares possui o número " + number + "?");
		gerarAlternativas("" + resolucaoDivisores.numerosDividoresResultadoPares(number, false));
		setResolucao(resolucaoDivisores.numerosDividoresParesImpares(number, false));
	}
}
