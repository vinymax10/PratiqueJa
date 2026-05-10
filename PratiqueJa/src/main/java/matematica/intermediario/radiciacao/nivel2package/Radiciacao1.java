package matematica.intermediario.radiciacao.nivel2package;

import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import modelo.matematica.Conta;
import pdf.util.Convert;


public class Radiciacao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao1(int indice)
	{
		super(indice);
		int b = 1 + rand.nextInt(10);
		int x = 2 + rand.nextInt(10);

		int a = b * x * x;
		int c = b * x;

		textLatex = "\\sqrt{" + a + "} \\cdot \\sqrt{" + b + "}" + "=";
		
		resolucaoLatex="\\sqrt{" + a + "} \\cdot \\sqrt{" + b + "}" + "=";
		
		resolucaoLatex+="\\sqrt{" + a + "\\cdot" + b + "}" + "=";
		resolucaoLatex+=ResolucaoRadiciacao.resolucao(a*b,2);
		
		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,200);
		
		resultadoCorreto = "" + c;

	}

	public static void main(String[] args)
	{
		new Radiciacao1(1);
	}

}
