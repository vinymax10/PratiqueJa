package matematica.basico.divisibilidade.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisibilidade.ResolucaoDivisores;

public class Divisibilidade7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ResolucaoDivisores resolucao = new ResolucaoDivisores();
		int number = NumerosDivisibilidade.getNumeroPar();

		addParagrafo("Quantos divisores naturais pares possui o número " + number + "?");
		gerarAlternativas("" + resolucao.numerosDividoresResultadoPares(number, true));
		setResolucao(resolucao.numerosDividoresParesImpares(number, true));
	}
}
