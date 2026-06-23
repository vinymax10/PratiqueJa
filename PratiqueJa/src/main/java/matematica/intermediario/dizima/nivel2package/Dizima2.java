package matematica.intermediario.dizima.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.dizima.ResolucaoDizima;

public class Dizima2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int inteira = 1 + rand.nextInt(10);
		int periodica;

		if(rand.nextBoolean())
		{
			do
				periodica = 10 + rand.nextInt(89);
			while(periodica % 11 == 0);
		}
		else
		{
			do
				periodica = 100 + rand.nextInt(889);
			while(periodica % 111 == 0);
		}

		String strNaoPeriodica = "";
		String strInteira = String.valueOf(inteira);
		String strPeriodica = String.valueOf(periodica);

		addParagrafo("Escreva a dízima periódica a seguir na forma de fração:");
		addParagrafo("\\(" + ResolucaoDizima.textLatex(strInteira, strNaoPeriodica, strPeriodica) + " = \\,?\\)");
		gerarAlternativas(ResolucaoDizima.resultadoCorreto(strInteira, strNaoPeriodica, strPeriodica));
		for(String passo : ResolucaoDizima.resolucaoSimples(strInteira, strPeriodica))
			addResolucao(passo);
	}
}
