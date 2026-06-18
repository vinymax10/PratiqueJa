package matematica.intermediario.dizima.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.dizima.ResolucaoDizima;

public class Dizima13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int inteira = 1 + rand.nextInt(10);
		int periodica = 1 + rand.nextInt(8);

		String strNaoPeriodica = "";
		String strInteira = String.valueOf(inteira);
		String strPeriodica = String.valueOf(periodica);

		addParagrafo("Escreva a dízima periódica a seguir na forma de fração:");
		addParagrafo("\\(" + ResolucaoDizima.textLatex(strInteira, strNaoPeriodica, strPeriodica) + " = \\,?\\)");
		gerarAlternativas(ResolucaoDizima.resultadoCorreto(strInteira, strNaoPeriodica, strPeriodica));
		setResolucao("\\(" + ResolucaoDizima.resolucaoSimples(strInteira, strPeriodica) + "\\)");
	}
}
