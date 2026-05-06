package matematica.intermediario.radiciacao.nivel2package;

import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import modelo.matematica.Conta;
import pdf.Convert;


public class Radiciacao4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao4(int indice)
	{
		super(indice);
		int b = 1 + rand.nextInt(10);
		int n = 2 + rand.nextInt(2);
		int d = 1 + rand.nextInt(4);
		int c = n * 2;
		int a = (int) Math.pow(d, c) * b;

		textLatex = " \\sqrt[" + n + "]{ \\sqrt{" + a + "}} \\div \\sqrt[" + c + "]{" + b + "} " + "=";
		resultadoCorreto = "" + d;
		
		resolucaoLatex = " \\sqrt[" + n + "]{ \\sqrt{" + a + "}} \\div \\sqrt[" + c + "]{" + b + "} " + "=";
		resolucaoLatex += " \\sqrt[" + c + "]{ "+ a +" } \\div \\sqrt[" + c + "]{" + b + "} " + "=";
		resolucaoLatex += " \\sqrt[" + c + "]{ "+ a +" \\div " + b + "} =";
		resolucaoLatex+=ResolucaoRadiciacao.resolucao((int)Math.pow(d, c), c);
		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,200);

	}

	public static void main(String[] args)
	{
		new Radiciacao4(1);
	}

}
