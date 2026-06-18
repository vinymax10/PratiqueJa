package matematica.intermediario.dizima.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.dizima.ResolucaoDizima;

public class Dizima15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Exibe uma dízima simples e pergunta qual é o valor do período
		int inteira = 1 + rand.nextInt(8);  // 1..8
		int periodica;
		int periodLen;

		switch(rand.nextInt(3))
		{
			case 0:
				periodica = 1 + rand.nextInt(8);  // 1 dígito
				periodLen = 1;
				break;
			case 1:
				do { periodica = 10 + rand.nextInt(89); } while(periodica % 11 == 0);
				periodLen = 2;
				break;
			default:
				do { periodica = 100 + rand.nextInt(889); } while(periodica % 111 == 0);
				periodLen = 3;
				break;
		}

		String strInteira   = String.valueOf(inteira);
		String strPeriodica = String.valueOf(periodica);

		addParagrafo("Qual é o valor do período da dízima a seguir?");
		addParagrafo("\\(" + ResolucaoDizima.textLatex(strInteira, "", strPeriodica) + "\\)");

		gerarAlternativasInteiras(periodica);

		String latexOverline = strInteira + "{,}\\overline{" + strPeriodica + "}";
		String res = "O período é o bloco de algarismos que se repete indefinidamente. \\(\\\\\\)";
		res += "Na dízima \\(" + latexOverline + "\\), o bloco que se repete é \\("
			 + strPeriodica + "\\), formado por \\(" + periodLen + "\\) algarismo"
			 + (periodLen == 1 ? "" : "s") + ".";
		setResolucao(res);
	}
}
