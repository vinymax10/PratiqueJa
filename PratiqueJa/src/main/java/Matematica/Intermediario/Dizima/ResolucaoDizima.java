package Matematica.Intermediario.Dizima;

import Auxiliar.Algebra;
import Matematica.ParCor;
import Matematica.Racional;

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

	public static String resolucao(String strInteira, String strNaoPeriodica, String strPeriodica)
	{
		String resolucaoLatex = formula()+"\\\\";

		String valorLatex = textLatex(strInteira, strNaoPeriodica, strPeriodica);

		int n = strNaoPeriodica.length();
		int p = strPeriodica.length();

		resolucaoLatex +="n="+n+",\\quad p="+p+"\\\\";
		
		resolucaoLatex += "\\dfrac{" + valorLatex + "\\cdot 10^{" + (n + p) + "} - " + valorLatex + "\\cdot 10^{" + n
		+ "}}" + "{10^{" + (n + p) + "}-10^{" + n + "}}= \\\\";

		String primeiroTermoNumerador = strInteira + strNaoPeriodica + strPeriodica + "," + strPeriodica + strPeriodica
		+ "\\ldots";

		String segundoTermoNumerador = strInteira + strNaoPeriodica + "," + strPeriodica + strPeriodica + "\\ldots";

		int primeiroTermoDenominador = (int) Math.pow(10, (n + p));
		int segundoTermoDenominador = (int) Math.pow(10, n);
		
		resolucaoLatex += "\\dfrac{" + valorLatex + "\\cdot "+primeiroTermoDenominador
				+" - " + valorLatex + "\\cdot "+segundoTermoDenominador+"}" 
				+ "{"+ primeiroTermoDenominador + "-" + segundoTermoDenominador +"}= \\\\";
		
		resolucaoLatex += "\\dfrac{" + primeiroTermoNumerador + "-" + segundoTermoNumerador + "}" + "{"
		+ primeiroTermoDenominador + "-" + segundoTermoDenominador + "}=\\\\";
		
		resolucaoLatex += "\\dfrac{" + 
		Integer.valueOf(strInteira + strNaoPeriodica + strPeriodica) 
		+ "-" + Integer.valueOf(strInteira + strNaoPeriodica) + "}" 
		+ "{"+ primeiroTermoDenominador + "-" + segundoTermoDenominador + "}=";

		int resultadoNumerador = Integer.valueOf(strInteira + strNaoPeriodica + strPeriodica)
		- Integer.valueOf(strInteira + strNaoPeriodica);
		int resultadoDenominador = primeiroTermoDenominador - segundoTermoDenominador;

		Racional fracSimplificada = new Racional(resultadoNumerador, resultadoDenominador);
		resolucaoLatex += "" + fracSimplificada.showDfrac();
		fracSimplificada.fatoracao(2);

		if(fracSimplificada.isSimplificou())
			resolucaoLatex += "=" + fracSimplificada.showDfrac();

		return resolucaoLatex;
	}
	
	public static String resolucaoSimples(String inteira, String strPeriodica)
	{
		String resolucaoLatex = formulaSimples()+"\\\\";

		String valorLatex = textLatex(inteira, "", strPeriodica);

		int p = strPeriodica.length();
		
		resolucaoLatex +="p="+p+"\\\\";
		
		resolucaoLatex += "\\dfrac{" + valorLatex + "\\cdot 10^{" + p + "} - " + valorLatex  
		+ "}" + "{10^{" + p + "}-1}= \\\\";

		String primeiroTermoNumerador = Integer.valueOf(inteira+strPeriodica) + "," + strPeriodica + strPeriodica
		+ "\\ldots";

		String segundoTermoNumerador = inteira + "," + strPeriodica + strPeriodica + "\\ldots";

		int primeiroTermoDenominador = (int) Math.pow(10, (p));
		int segundoTermoDenominador = (int) Math.pow(10, 0);
		
		resolucaoLatex += "\\dfrac{" + valorLatex + "\\cdot "+primeiroTermoDenominador
				+" - " + valorLatex + "}" 
				+ "{"+ primeiroTermoDenominador + "-" + segundoTermoDenominador +"}= \\\\";
		
		int resultadoDenominador = primeiroTermoDenominador - segundoTermoDenominador;

		resolucaoLatex += "\\dfrac{" + primeiroTermoNumerador + "-" + segundoTermoNumerador + "}" 
		+ "{" + resultadoDenominador + "}=";

		int resultadoNumerador = Integer.valueOf(inteira+strPeriodica)-Integer.valueOf(inteira);

		Racional fracSimplificada = new Racional(resultadoNumerador, resultadoDenominador);
		resolucaoLatex += "" + fracSimplificada.showDfrac();
		fracSimplificada.fatoracao(2);

		if(fracSimplificada.isSimplificou())
			resolucaoLatex += "=" + fracSimplificada.showDfrac();

		return resolucaoLatex;
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