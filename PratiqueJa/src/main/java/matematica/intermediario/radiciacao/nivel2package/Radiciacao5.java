package matematica.intermediario.radiciacao.nivel2package;

import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import modelo.matematica.Conta;
import pdf.util.Convert;


public class Radiciacao5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao5(int indice)
	{
		super(indice);
		int b = 2 + rand.nextInt(9);
		int x = 2 + rand.nextInt(10);

		int a = b * x * x;

		textLatex = " \\dfrac{ \\sqrt{" + a + "} }{ \\sqrt{" + b + "} }" + "=";
		
		resolucaoLatex = " \\dfrac{ \\sqrt{" + a + "} }{ \\sqrt{" + b + "} }" + "=";
		resolucaoLatex += " \\sqrt{ \\dfrac{ " + a + "}{" + b + "} }" + "=";
		resolucaoLatex += ResolucaoRadiciacao.resolucao(( x * x), 2);
		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,200);

		resultadoCorreto = "" +x;

	}

	public static void main(String[] args)
	{
		new Radiciacao5(1);
	}

}
