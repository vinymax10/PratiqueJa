package Matematica.Intermediario.Radiciacao.Nivel2Package;

import Matematica.Intermediario.Radiciacao.ResolucaoRadiciacao;
import Modelo.Matematica.Conta;
import Pdf.Convert;


public class Radiciacao3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao3(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(20);
		int b = 1 + rand.nextInt((int) Math.pow(a, 2));

		textLatex = "\\sqrt{" + ((a*a) - b) + "+" + b + "}" + "=";
		resultadoCorreto = "" + a;
		
		resolucaoLatex="\\sqrt{" + ((a*a) - b) + "+" + b + "}" + "=";
		resolucaoLatex+=ResolucaoRadiciacao.resolucao(a*a,2);
		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,200);

	}

	public static void main(String[] args)
	{
		new Radiciacao3(1);
	}

}
