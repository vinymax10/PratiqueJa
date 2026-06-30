package matematica.basico.multiplicacaodivisaointeiro;

import matematica.DefinicaoCores;
import matematica.resolucaonatural.ResolucaoNatural;

public class ResolucaoMDInteiro
{
	public static String[] multiplicacao(int a, int b)
	{
		String cores = "\\(" + DefinicaoCores.irisBabypink() + "\\)";

		if((a > 0 && b > 0) || (a < 0 && b < 0))
		{
			String prosa = "Sinais iguais: o produto é positivo.";
			String contas = "\\(" + ResolucaoNatural.multiplicacao(Math.abs(a), Math.abs(b), true) + "\\\\"
				+ "\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
				+ " \\times \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
				+ " = \\textcolor{iris}{\\mathbf{+" + (Math.abs(a) * Math.abs(b)) + "}}\\)";
			return new String[] { cores, prosa, contas };
		}
		else
		{
			String prosa = "Sinais diferentes: o produto é negativo.";
			String contas = "\\(" + ResolucaoNatural.multiplicacao(Math.abs(a), Math.abs(b), true) + "\\\\"
				+ "\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
				+ " \\times \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
				+ " = \\textcolor{iris}{\\mathbf{-" + Math.abs(a * b) + "}}\\)";
			return new String[] { cores, prosa, contas };
		}
	}

	public static String[] divisao(int a, int b)
	{
		String cores = "\\(" + DefinicaoCores.irisBabypink() + "\\)";

		if((a > 0 && b > 0) || (a < 0 && b < 0))
		{
			String prosa = "Sinais iguais: o quociente é positivo.";
			String contas = "\\(" + ResolucaoNatural.divisao(Math.abs(a), Math.abs(b), true) + "\\\\"
				+ "\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
				+ " \\div \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
				+ " = \\textcolor{iris}{\\mathbf{+" + (Math.abs(a) / Math.abs(b)) + "}}\\)";
			return new String[] { cores, prosa, contas };
		}
		else
		{
			String prosa = "Sinais diferentes: o quociente é negativo.";
			String contas = "\\(" + ResolucaoNatural.divisao(Math.abs(a), Math.abs(b), true) + "\\\\"
				+ "\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
				+ " \\div \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
				+ " = \\textcolor{iris}{\\mathbf{-" + Math.abs(a / b) + "}}\\)";
			return new String[] { cores, prosa, contas };
		}
	}
	
	private static String sinal(int num)
	{
		if(num>0)
			return "+";
		else
			return "-";
	}
	
}