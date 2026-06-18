package matematica.intermediario.dizima.nivel3package;

import matematica.GeradorExercicio;
import matematica.intermediario.dizima.ResolucaoDizima;

public class Dizima11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Dízima composta com parte inteira zero e parte não periódica de 1 dígito
		int inteira     = 0;
		int naoPeriodica = 1 + rand.nextInt(8);  // 1 dígito, 1..8

		int periodica;
		do
		{
			switch(rand.nextInt(2))
			{
				case 0:
					periodica = 1 + rand.nextInt(8);
					break;
				default:
					do { periodica = 10 + rand.nextInt(89); } while(periodica % 11 == 0);
					break;
			}
		}
		while(periodica == naoPeriodica);

		String strNaoPeriodica = String.valueOf(naoPeriodica);
		String strInteira      = String.valueOf(inteira);
		String strPeriodica    = String.valueOf(periodica);

		addParagrafo("Escreva a dízima periódica a seguir na forma de fração:");
		addParagrafo("\\(" + ResolucaoDizima.textLatex(strInteira, strNaoPeriodica, strPeriodica) + " = \\,?\\)");
		gerarAlternativas(ResolucaoDizima.resultadoCorreto(strInteira, strNaoPeriodica, strPeriodica));
		setResolucao("\\(" + ResolucaoDizima.resolucao(strInteira, strNaoPeriodica, strPeriodica) + "\\)");
	}
}
