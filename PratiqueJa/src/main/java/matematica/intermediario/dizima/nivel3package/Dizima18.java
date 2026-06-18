package matematica.intermediario.dizima.nivel3package;

import matematica.GeradorExercicio;
import matematica.intermediario.dizima.ResolucaoDizima;

public class Dizima18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Dízima composta com parte não periódica de 2 dígitos e período de 1 dígito
		int inteira = rand.nextInt(5);  // 0..4

		int naoPeriodica;
		do { naoPeriodica = 11 + rand.nextInt(88); } while(naoPeriodica % 11 == 0);
		// 2 dígitos, evitar múltiplos de 11 para o período não colapsar

		int periodica = 1 + rand.nextInt(8);  // 1 dígito, 1..8

		String strNaoPeriodica = String.valueOf(naoPeriodica);
		String strInteira      = String.valueOf(inteira);
		String strPeriodica    = String.valueOf(periodica);

		addParagrafo("Escreva a dízima periódica a seguir na forma de fração:");
		addParagrafo("\\(" + ResolucaoDizima.textLatex(strInteira, strNaoPeriodica, strPeriodica) + " = \\,?\\)");
		gerarAlternativas(ResolucaoDizima.resultadoCorreto(strInteira, strNaoPeriodica, strPeriodica));
		setResolucao("\\(" + ResolucaoDizima.resolucao(strInteira, strNaoPeriodica, strPeriodica) + "\\)");
	}
}
