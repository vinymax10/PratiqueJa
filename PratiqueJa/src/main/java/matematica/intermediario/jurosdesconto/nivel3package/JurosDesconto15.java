package matematica.intermediario.jurosdesconto.nivel3package;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import matematica.GeradorExercicio;
import matematica.ParCor;

public class JurosDesconto15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		DecimalFormat deci = new DecimalFormat("#.####", new DecimalFormatSymbols(Locale.FRANCE));
		DecimalFormat pct  = new DecimalFormat("#.##",   new DecimalFormatSymbols(Locale.FRANCE));

		int iMensal = 1 + rand.nextInt(3);  // 1%, 2% ou 3%
		double im = iMensal / 100.0;

		// tipo: 0=anual→mensal, 1=trimestral→mensal, 2=mensal→anual, 3=mensal→trimestral
		int tipo = rand.nextInt(4);

		switch(tipo)
		{
			case 0:
			{
				double ia = Math.pow(1 + im, 12) - 1;
				String iaStr = pct.format(ia * 100);
				String formula = ParCor.formula("i_m = (1+i_a)^{\\frac{1}{12}} - 1");
				String resolucao = formula + "\\\\";
				resolucao += "i_a=" + iaStr + "\\%=" + deci.format(ia) + "\\\\";
				resolucao += "i_m=(1+" + deci.format(ia) + ")^{\\frac{1}{12}}-1\\\\";
				resolucao += "i_m=(" + deci.format(1 + ia) + ")^{\\frac{1}{12}}-1\\\\";
				resolucao += "i_m=" + deci.format(Math.pow(1 + ia, 1.0 / 12)) + "-1=" + deci.format(im) + "\\\\";
				resolucao += "i_m=" + iMensal + "\\%";

				addParagrafo("Uma aplicação rende " + iaStr + "% ao ano em regime de juros compostos. "
						+ "Qual é a taxa mensal equivalente?");
				gerarAlternativas(iMensal + "\\%");
				setResolucao("\\(" + resolucao + "\\)");
				break;
			}
			case 1:
			{
				double it = Math.pow(1 + im, 3) - 1;
				String itStr = pct.format(it * 100);
				String formula = ParCor.formula("i_m = (1+i_t)^{\\frac{1}{3}} - 1");
				String resolucao = formula + "\\\\";
				resolucao += "i_t=" + itStr + "\\%=" + deci.format(it) + "\\\\";
				resolucao += "i_m=(1+" + deci.format(it) + ")^{\\frac{1}{3}}-1\\\\";
				resolucao += "i_m=(" + deci.format(1 + it) + ")^{\\frac{1}{3}}-1\\\\";
				resolucao += "i_m=" + deci.format(Math.pow(1 + it, 1.0 / 3)) + "-1=" + deci.format(im) + "\\\\";
				resolucao += "i_m=" + iMensal + "\\%";

				addParagrafo("Uma aplicação rende " + itStr + "% ao trimestre em regime de juros compostos. "
						+ "Qual é a taxa mensal equivalente?");
				gerarAlternativas(iMensal + "\\%");
				setResolucao("\\(" + resolucao + "\\)");
				break;
			}
			case 2:
			{
				double ia = Math.pow(1 + im, 12) - 1;
				String iaStr = pct.format(ia * 100);
				String formula = ParCor.formula("(1+i_a) = (1+i_m)^{12}");
				String resolucao = formula + "\\\\";
				resolucao += "i_m=" + iMensal + "\\%=" + deci.format(im) + "\\\\";
				resolucao += "(1+i_a)=(1+" + deci.format(im) + ")^{12}\\\\";
				resolucao += "(1+i_a)=(" + deci.format(1 + im) + ")^{12}=" + deci.format(Math.pow(1 + im, 12)) + "\\\\";
				resolucao += "i_a=" + deci.format(Math.pow(1 + im, 12)) + "-1=" + deci.format(ia) + "\\\\";
				resolucao += "i_a \\approx " + iaStr + "\\%";

				addParagrafo("Uma aplicação rende " + iMensal + "% ao mês em regime de juros compostos. "
						+ "Qual é a taxa anual equivalente aproximada?");
				gerarAlternativas(iaStr + "\\%");
				setResolucao("\\(" + resolucao + "\\)");
				break;
			}
			default:
			{
				double it = Math.pow(1 + im, 3) - 1;
				String itStr = pct.format(it * 100);
				String formula = ParCor.formula("(1+i_t) = (1+i_m)^{3}");
				String resolucao = formula + "\\\\";
				resolucao += "i_m=" + iMensal + "\\%=" + deci.format(im) + "\\\\";
				resolucao += "(1+i_t)=(1+" + deci.format(im) + ")^{3}\\\\";
				resolucao += "(1+i_t)=(" + deci.format(1 + im) + ")^{3}=" + deci.format(Math.pow(1 + im, 3)) + "\\\\";
				resolucao += "i_t=" + deci.format(Math.pow(1 + im, 3)) + "-1=" + deci.format(it) + "\\\\";
				resolucao += "i_t \\approx " + itStr + "\\%";

				addParagrafo("Uma aplicação rende " + iMensal + "% ao mês em regime de juros compostos. "
						+ "Qual é a taxa trimestral equivalente aproximada?");
				gerarAlternativas(itStr + "\\%");
				setResolucao("\\(" + resolucao + "\\)");
				break;
			}
		}
	}
}
