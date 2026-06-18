package matematica.intermediario.dizima.nivel3package;

import matematica.GeradorExercicio;
import matematica.intermediario.dizima.ResolucaoDizima;

public class Dizima1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int inteira = 1 + rand.nextInt(10);
		int naoPeriodica = 1 + rand.nextInt(8);

		int periodica = 0;
		do
		{
			switch(rand.nextInt(3))
			{
				case 0:
					periodica = 1 + rand.nextInt(8);
					break;
				case 1:
					do
						periodica = 10 + rand.nextInt(89);
					while(periodica % 11 == 0);
					break;
				case 2:
					do
						periodica = 100 + rand.nextInt(889);
					while(periodica % 111 == 0);
					break;
			}
		}
		while(periodica == naoPeriodica);

		String strNaoPeriodica = String.valueOf(naoPeriodica);
		String strInteira = String.valueOf(inteira);
		String strPeriodica = String.valueOf(periodica);

		addParagrafo("Escreva a dízima periódica a seguir na forma de fração:");
		addParagrafo("\\(" + ResolucaoDizima.textLatex(strInteira, strNaoPeriodica, strPeriodica) + " = \\,?\\)");
		gerarAlternativas(ResolucaoDizima.resultadoCorreto(strInteira, strNaoPeriodica, strPeriodica));
		setResolucao("\\(" + ResolucaoDizima.resolucao(strInteira, strNaoPeriodica, strPeriodica) + "\\)");
	}
}
