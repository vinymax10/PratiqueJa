package matematica.intermediario.radiciacao.nivel2package;

import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import modelo.matematica.Conta;
import pdf.util.Convert;


public class Radiciacao6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao6(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(20);
		int b = 1 + rand.nextInt(10);

		textLatex = "\\sqrt{" + (int) (a * a * b) + "\\div" + b + "}" + "=";
		resultadoCorreto = "" + a;
		
		resolucaoLatex="\\sqrt{" + (a * a * b) + "\\div" + b + "}" + "=";
		resolucaoLatex+=ResolucaoRadiciacao.resolucao(a*a,2);
		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,200);

	}

	public static void main(String[] args)
	{
		new Radiciacao6(1);
	}

}
