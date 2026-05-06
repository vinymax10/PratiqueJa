package matematica.intermediario.radiciacao.nivel2package;

import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import modelo.matematica.Conta;
import pdf.Convert;


public class Radiciacao8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao8(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(20);
		int b = 1 + rand.nextInt((int) Math.pow(a, 2));

		textLatex = "\\sqrt{" + (int) (Math.pow(a, 2) + b) + "-" + b + "}" + "=";
		resultadoCorreto = "" + a;

		resolucaoLatex="\\sqrt{" + ((a*a) + b) + "-" + b + "}" + "=";
		resolucaoLatex+=ResolucaoRadiciacao.resolucao(a*a,2);
		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,200);

	}

	public static void main(String[] args)
	{
		new Radiciacao8(1);
	}

}
