package matematica.basico.multiplicacaodivisaointeiro;

import matematica.DefinicaoCores;
import matematica.basico.resolucaonatural.ResolucaoNatural;

public class ResolucaoMDInteiro
{
	public static String multiplicacao(int a, int b)
	{
		String resolucaoLatex="";
		resolucaoLatex=DefinicaoCores.irisBabypink();
		if((a>0&&b>0)||(a<0&&b<0))
		{
			resolucaoLatex+=ResolucaoNatural.multiplicacao(Math.abs(a),Math.abs(b),true)+"\\\\\\\\";
			resolucaoLatex+="\\text{Sinais iguais, mais (+)}\\\\";
			resolucaoLatex+="\\textcolor{iris}{"+sinal(a)+"}"+Math.abs(a);
			resolucaoLatex+=" \\times \\textcolor{iris}{"+sinal(b)+"}"+Math.abs(b);
			resolucaoLatex+="=\\textcolor{iris}{+}"+(Math.abs(a)*Math.abs(b));
		}
		else
		{
			resolucaoLatex+=ResolucaoNatural.multiplicacao(Math.abs(a),Math.abs(b),true)+"\\\\\\\\";
			resolucaoLatex+="\\text{Sinais diferentes, menos (-)}\\\\";
			resolucaoLatex+="\\textcolor{iris}{"+sinal(a)+"}"+Math.abs(a);
			resolucaoLatex+=" \\times \\textcolor{iris}{"+sinal(b)+"}"+Math.abs(b);
			resolucaoLatex+="=\\textcolor{iris}{-}"+(Math.abs(a*b));
		}
		return resolucaoLatex;
	}
	
	public static String divisao(int a, int b)
	{
		String resolucaoLatex="";
		resolucaoLatex=DefinicaoCores.irisBabypink();
		if((a>0&&b>0)||(a<0&&b<0))
		{
			resolucaoLatex+=ResolucaoNatural.divisao(Math.abs(a),Math.abs(b),true)+"\\\\\\\\";
			resolucaoLatex+="\\text{Sinais iguais, mais (+)}\\\\";
			resolucaoLatex+="\\textcolor{iris}{"+sinal(a)+"}"+Math.abs(a);
			resolucaoLatex+=" \\div \\textcolor{iris}{"+sinal(b)+"}"+Math.abs(b);
			resolucaoLatex+="=\\textcolor{iris}{+}"+(Math.abs(a)/Math.abs(b));
		}
		else
		{
			resolucaoLatex+=ResolucaoNatural.divisao(Math.abs(a),Math.abs(b),true)+"\\\\\\\\";
			resolucaoLatex+="\\text{Sinais diferentes, menos (-)}\\\\";
			resolucaoLatex+="\\textcolor{iris}{"+sinal(a)+"}"+Math.abs(a);
			resolucaoLatex+=" \\div \\textcolor{iris}{"+sinal(b)+"}"+Math.abs(b);
			resolucaoLatex+="=\\textcolor{iris}{-}"+(Math.abs(a/b));
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