package Matematica.Basico.AdicaoSubtracaoInteiro;

import Matematica.DefinicaoCores;
import Matematica.Basico.ResolucaoNatural.ResolucaoNatural;

public class ResolucaoASInteiro
{
	public static String soma(int a, int b)
	{
		String resolucaoLatex="";
		resolucaoLatex=DefinicaoCores.irisBabypink();
		if((a>0&&b>0)||(a<0&&b<0))
		{
			resolucaoLatex+=ResolucaoNatural.soma(Math.abs(a),Math.abs(b),true)+"\\\\\\\\";
			resolucaoLatex+="\\text{Sinais iguais,} \\\\  \\text{soma e repete o sinal}\\\\";
			resolucaoLatex+="\\textcolor{iris}{"+sinal(a)+"}"+Math.abs(a);
			resolucaoLatex+=" \\textcolor{iris}{"+sinal(b)+"}"+Math.abs(b);
			resolucaoLatex+="=\\textcolor{iris}{"+sinal(b)+"}"+(Math.abs(a)+Math.abs(b));
		}
		else
		{
			if(Math.abs(a)>Math.abs(b))
				resolucaoLatex+=ResolucaoNatural.subtracao(Math.abs(a),Math.abs(b),true)+"\\\\\\\\";
			else
				resolucaoLatex+=ResolucaoNatural.subtracao(Math.abs(b),Math.abs(a),true)+"\\\\\\\\";

			resolucaoLatex+="\\text{Sinais diferentes, subtraímos} \\\\  \\text{e damos o sinal do maior.}\\\\";
			resolucaoLatex+="\\textcolor{iris}{"+sinal(a)+"}"+Math.abs(a);
			resolucaoLatex+=" \\textcolor{iris}{"+sinal(b)+"}"+Math.abs(b);
			resolucaoLatex+="=\\textcolor{iris}{"+sinal(a+b)+"}"+(Math.abs(a+b));
		}
		return resolucaoLatex;
	}
	
	private static String sinal(int num)
	{
		if(num>0)
			return "+";
		else
			return "-";
	}
	
}