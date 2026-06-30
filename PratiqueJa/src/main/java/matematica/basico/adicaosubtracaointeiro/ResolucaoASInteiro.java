package matematica.basico.adicaosubtracaointeiro;

import matematica.DefinicaoCores;
import matematica.resolucaonatural.ResolucaoNatural;

public class ResolucaoASInteiro
{
	public static String[] soma(int a, int b)
	{
		String prefixoCores = "\\(" + DefinicaoCores.irisBabypink() + "\\)";

		if((a > 0 && b > 0) || (a < 0 && b < 0))
		{
			int somaAbs = Math.abs(a) + Math.abs(b);
			return new String[]
			{
				prefixoCores + "Sinais iguais: somamos os valores absolutos e repetimos o sinal.",
				"\\(" + ResolucaoNatural.soma(Math.abs(a), Math.abs(b), true) + "\\)",
				"\\(\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
					+ " \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
					+ " = \\textcolor{iris}{\\mathbf{" + sinal(a) + somaAbs + "}}\\)"
			};
		}
		else
		{
			int maior = Math.abs(a) >= Math.abs(b) ? Math.abs(a) : Math.abs(b);
			int menor = Math.abs(a) < Math.abs(b) ? Math.abs(a) : Math.abs(b);
			int soma = a + b;
			String somaStr = soma == 0
					? "\\mathbf{0}"
					: "\\textcolor{iris}{\\mathbf{" + sinal(soma) + Math.abs(soma) + "}}";
			return new String[]
			{
				prefixoCores + "Sinais diferentes: subtraímos os valores absolutos e usamos o sinal do maior.",
				"\\(" + ResolucaoNatural.subtracao(maior, menor, true) + "\\)",
				"\\(\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
					+ " \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
					+ " = " + somaStr + "\\)"
			};
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