package Matematica.Intermediario.Radiciacao.Nivel3Package;

import Matematica.Intermediario.Radiciacao.FatoresPrimos;
import Matematica.Intermediario.Radiciacao.ResolucaoRadiciacao;
import Modelo.Matematica.Conta;
import Pdf.Convert;


public class Radiciacao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao1(int indice)
	{
		super(indice);
		int x = 2 + rand.nextInt(3);
		int y = 2 + rand.nextInt(9);
		int z = 2 + rand.nextInt(9);

		int b = (int) Math.pow(z, x);
		int n = 2;
		int c = n * x;
		int d = z * y;
		int a = y * y * z;

		textLatex = " \\sqrt{" + a + "} \\cdot \\sqrt[" + c + "]{" + b + "} " + "=";
		
		resolucaoLatex="\\sqrt{" + a + "} \\cdot \\sqrt[" + c + "]{" + b + "} " + "=";
		resolucaoLatex+="\\sqrt[" + c + "]{" + a + "^"+ x +"} \\cdot \\sqrt[" + c + "]{" + b + "} " + "=";
		resolucaoLatex+="\\sqrt[" + c + "]{" + a + "^"+ x +"\\cdot " + b + "}=";
		FatoresPrimos fatoresPrimosA=ResolucaoRadiciacao.fatoresPrimos(a);
		resolucaoLatex+="\\sqrt[" + c + "]{(" + fatoresPrimosA.latex() + ")^"+ x +"\\cdot " + b + "}=";
		
		fatoresPrimosA.multiplicarPotencias(x);
		resolucaoLatex+="\\sqrt[" + c + "]{" + fatoresPrimosA.latex() + "\\cdot " + b + "}=";

		FatoresPrimos fatoresPrimosB=ResolucaoRadiciacao.fatoresPrimos(b);

		resolucaoLatex+="\\sqrt[" + c + "]{" + fatoresPrimosA.latex() + "\\cdot " + fatoresPrimosB.latex() + "}=";

		fatoresPrimosA.juntar(fatoresPrimosB);
		
//		resolucaoLatex+="\\sqrt[" + c + "]{" + fatoresPrimosA.latex() + "}=";

		resolucaoLatex+=ResolucaoRadiciacao.resolucao(fatoresPrimosA, c);
		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,200);

		resultadoCorreto = "" + d;

	}

	public static void main(String[] args)
	{
		new Radiciacao1(1);
	}

}
