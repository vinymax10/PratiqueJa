package matematica.basico.divisibilidade.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisores;

public class Divisibilidade2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ResolucaoDivisores resolucaoDivisores = new ResolucaoDivisores();

		int number = NumerosDivisibilidade.getNumeroPar();

		addParagrafo("Quantos divisores naturais pares possui o número " + number + "?");
		gerarAlternativas("" + resolucaoDivisores.numerosDividoresResultadoPares(number, true));
		setResolucao(resolucaoDivisores.numerosDividoresParesImpares(number, true));
	}
}
