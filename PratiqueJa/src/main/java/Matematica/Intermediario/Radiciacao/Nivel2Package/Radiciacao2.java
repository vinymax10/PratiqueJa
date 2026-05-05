package Matematica.Intermediario.Radiciacao.Nivel2Package;

import Matematica.Intermediario.Radiciacao.ResolucaoRadiciacao;
import Modelo.Matematica.Conta;
import Pdf.Convert;


public class Radiciacao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao2(int indice)
	{
		super(indice);
		int b = 1 + rand.nextInt(10);
		int c = 2 + rand.nextInt(10);

		int a = b * c * c;

		textLatex = " \\sqrt{" + a + "} \\div \\sqrt{" + b + "} " + "=";
		resultadoCorreto = "" + c;

		resolucaoLatex=" \\sqrt{" + a + "} \\div \\sqrt{" + b + "} =";
		resolucaoLatex+=" \\sqrt{" + a + " \\div " + b + "} =";

		resolucaoLatex+=ResolucaoRadiciacao.resolucao(c * c,2);
		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,200);

	}

	public static void main(String[] args)
	{
		new Radiciacao2(1);
	}

}
