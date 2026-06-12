package matematica.basico.multiplicacaodivisaointeiro;

import matematica.DefinicaoCores;
import matematica.basico.resolucaonatural.ResolucaoNatural;

public class ResolucaoMDInteiro
{
	public static String multiplicacao(int a, int b)
	{
		String res = "\\(" + DefinicaoCores.irisBabypink() + "\\)";

		if((a > 0 && b > 0) || (a < 0 && b < 0))
		{
			res += "Sinais iguais: o produto é positivo. \\(\\\\\\)";
			res += "\\(" + ResolucaoNatural.multiplicacao(Math.abs(a), Math.abs(b), true) + "\\\\";
			res += "\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
				 + " \\times \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
				 + " = \\textcolor{iris}{+}" + (Math.abs(a) * Math.abs(b)) + "\\)";
		}
		else
		{
			res += "Sinais diferentes: o produto é negativo. \\(\\\\\\)";
			res += "\\(" + ResolucaoNatural.multiplicacao(Math.abs(a), Math.abs(b), true) + "\\\\";
			res += "\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
				 + " \\times \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
				 + " = \\textcolor{iris}{-}" + Math.abs(a * b) + "\\)";
		}
		return res;
	}

	public static String divisao(int a, int b)
	{
		String res = "\\(" + DefinicaoCores.irisBabypink() + "\\)";

		if((a > 0 && b > 0) || (a < 0 && b < 0))
		{
			res += "Sinais iguais: o quociente é positivo. \\(\\\\\\)";
			res += "\\(" + ResolucaoNatural.divisao(Math.abs(a), Math.abs(b), true) + "\\\\";
			res += "\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
				 + " \\div \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
				 + " = \\textcolor{iris}{+}" + (Math.abs(a) / Math.abs(b)) + "\\)";
		}
		else
		{
			res += "Sinais diferentes: o quociente é negativo. \\(\\\\\\)";
			res += "\\(" + ResolucaoNatural.divisao(Math.abs(a), Math.abs(b), true) + "\\\\";
			res += "\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
				 + " \\div \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
				 + " = \\textcolor{iris}{-}" + Math.abs(a / b) + "\\)";
		}
		return res;
	}
	
	private static String sinal(int num)
	{
		if(num>0)
			return "+";
		else
			return "-";
	}
	
}