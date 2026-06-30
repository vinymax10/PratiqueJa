package matematica.intermediario.dizima;

import util.Algebra;
import matematica.ParCor;
import matematica.Racional;

public class ResolucaoDizima
{

	public static String textLatex(String strInteira, String strNaoPeriodica, String strPeriodica)
	{
		return "" + strInteira + "," + strNaoPeriodica + strPeriodica + strPeriodica + "\\ldots";
	}

	public static String resultadoCorreto(String strInteira, String strNaoPeriodica, String strPeriodica)
	{
		int primeiraParte = Integer.valueOf(strInteira + strNaoPeriodica + strPeriodica);
		int segundaParte = Integer.valueOf(strInteira + strNaoPeriodica);

		int n = strNaoPeriodica.length();
		int p = strPeriodica.length();

		int numerador = primeiraParte - segundaParte;
		int denominador = (int) (Math.pow(10, n + p) - Math.pow(10, n));

		String resultadoCorreto = Algebra.fatoracao(numerador, denominador, 2);
		return resultadoCorreto;
	}

	public static String[] resolucao(String strInteira, String strNaoPeriodica, String strPeriodica)
	{
		java.util.List<String> passos = new java.util.ArrayList<>();

		passos.add("\\(" + formula() + "\\)");

		String valorLatex = textLatex(strInteira, strNaoPeriodica, strPeriodica);

		int n = strNaoPeriodica.length();
		int p = strPeriodica.length();

		passos.add("\\(n=" + n + ",\\quad p=" + p + "\\)");

		passos.add("\\(\\dfrac{" + valorLatex + "\\cdot 10^{" + (n + p) + "} - " + valorLatex + "\\cdot 10^{" + n
		+ "}}" + "{10^{" + (n + p) + "}-10^{" + n + "}}= \\)");

		String primeiroTermoNumerador = strInteira + strNaoPeriodica + strPeriodica + "," + strPeriodica + strPeriodica
		+ "\\ldots";

		String segundoTermoNumerador = strInteira + strNaoPeriodica + "," + strPeriodica + strPeriodica + "\\ldots";

		int primeiroTermoDenominador = (int) Math.pow(10, (n + p));
		int segundoTermoDenominador = (int) Math.pow(10, n);

		passos.add("\\(\\dfrac{" + valorLatex + "\\cdot " + primeiroTermoDenominador
				+ " - " + valorLatex + "\\cdot " + segundoTermoDenominador + "}"
				+ "{" + primeiroTermoDenominador + "-" + segundoTermoDenominador + "}= \\)");

		passos.add("\\(\\dfrac{" + primeiroTermoNumerador + "-" + segundoTermoNumerador + "}" + "{"
		+ primeiroTermoDenominador + "-" + segundoTermoDenominador + "}=\\)");

		String ultimoPasso = "\\dfrac{" +
		Integer.valueOf(strInteira + strNaoPeriodica + strPeriodica)
		+ "-" + Integer.valueOf(strInteira + strNaoPeriodica) + "}"
		+ "{" + primeiroTermoDenominador + "-" + segundoTermoDenominador + "}=";

		int resultadoNumerador = Integer.valueOf(strInteira + strNaoPeriodica + strPeriodica)
		- Integer.valueOf(strInteira + strNaoPeriodica);
		int resultadoDenominador = primeiroTermoDenominador - segundoTermoDenominador;

		Racional fracSimplificada = new Racional(resultadoNumerador, resultadoDenominador);
		String fracOriginalResolucao = fracSimplificada.showDfrac();
		fracSimplificada.fatoracao(2);

		if(fracSimplificada.isSimplificou())
			ultimoPasso += fracOriginalResolucao + "=\\mathbf{" + fracSimplificada.showDfrac() + "}";
		else
			ultimoPasso += "\\mathbf{" + fracOriginalResolucao + "}";

		passos.add("\\(" + ultimoPasso + "\\)");

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoSimples(String inteira, String strPeriodica)
	{
		java.util.List<String> passos = new java.util.ArrayList<>();

		passos.add("\\(" + formulaSimples() + "\\)");

		String valorLatex = textLatex(inteira, "", strPeriodica);

		int p = strPeriodica.length();

		passos.add("\\(p=" + p + "\\)");

		passos.add("\\(\\dfrac{" + valorLatex + "\\cdot 10^{" + p + "} - " + valorLatex
		+ "}" + "{10^{" + p + "}-1}= \\)");

		String primeiroTermoNumerador = Integer.valueOf(inteira+strPeriodica) + "," + strPeriodica + strPeriodica
		+ "\\ldots";

		String segundoTermoNumerador = inteira + "," + strPeriodica + strPeriodica + "\\ldots";

		int primeiroTermoDenominador = (int) Math.pow(10, (p));
		int segundoTermoDenominador = (int) Math.pow(10, 0);

		passos.add("\\(\\dfrac{" + valorLatex + "\\cdot " + primeiroTermoDenominador
				+ " - " + valorLatex + "}"
				+ "{" + primeiroTermoDenominador + "-" + segundoTermoDenominador + "}= \\)");

		int resultadoDenominador = primeiroTermoDenominador - segundoTermoDenominador;

		String ultimoPasso = "\\dfrac{" + primeiroTermoNumerador + "-" + segundoTermoNumerador + "}"
		+ "{" + resultadoDenominador + "}=";

		int resultadoNumerador = Integer.valueOf(inteira+strPeriodica)-Integer.valueOf(inteira);

		Racional fracSimplificada = new Racional(resultadoNumerador, resultadoDenominador);
		String fracOriginalSimples = fracSimplificada.showDfrac();
		fracSimplificada.fatoracao(2);

		if(fracSimplificada.isSimplificou())
			ultimoPasso += fracOriginalSimples + "=\\mathbf{" + fracSimplificada.showDfrac() + "}";
		else
			ultimoPasso += "\\mathbf{" + fracOriginalSimples + "}";

		passos.add("\\(" + ultimoPasso + "\\)");

		return passos.toArray(new String[0]);
	}

	private static String formula()
	{
		return ParCor.formula("\\dfrac{x \\cdot 10^{(n+p)}- x \\cdot 10^{n}}{10^{(n+p)} - 10^{n} }");
	}
	
	private static String formulaSimples()
	{
		return ParCor.formula("\\dfrac{x \\cdot 10^{p}- x}{10^{p} - 1 }");
	}
}