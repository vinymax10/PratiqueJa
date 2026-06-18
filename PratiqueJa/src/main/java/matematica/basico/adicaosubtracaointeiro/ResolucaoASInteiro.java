package matematica.basico.adicaosubtracaointeiro;

import matematica.DefinicaoCores;
import matematica.resolucaonatural.ResolucaoNatural;

public class ResolucaoASInteiro
{
	public static String soma(int a, int b)
	{
		String res = "\\(" + DefinicaoCores.irisBabypink() + "\\)";

		if((a > 0 && b > 0) || (a < 0 && b < 0))
		{
			res += "Sinais iguais: somamos os valores absolutos e repetimos o sinal. \\(\\\\\\)";
			res += "\\(" + ResolucaoNatural.soma(Math.abs(a), Math.abs(b), true) + "\\) \\(\\\\\\)";
			int somaAbs = Math.abs(a) + Math.abs(b);
			res += "\\(\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
				 + " \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
				 + " = \\textcolor{iris}{" + sinal(a) + "}" + somaAbs + "\\)";
		}
		else
		{
			res += "Sinais diferentes: subtraímos os valores absolutos e usamos o sinal do maior. \\(\\\\\\)";
			int maior = Math.abs(a) >= Math.abs(b) ? Math.abs(a) : Math.abs(b);
			int menor = Math.abs(a) < Math.abs(b) ? Math.abs(a) : Math.abs(b);
			res += "\\(" + ResolucaoNatural.subtracao(maior, menor, true) + "\\) \\(\\\\\\)";
			int soma = a + b;
			String somaStr = soma == 0
					? "0"
					: "\\textcolor{iris}{" + sinal(soma) + "}" + Math.abs(soma);
			res += "\\(\\textcolor{iris}{" + sinal(a) + "}" + Math.abs(a)
				 + " \\textcolor{iris}{" + sinal(b) + "}" + Math.abs(b)
				 + " = " + somaStr + "\\)";
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