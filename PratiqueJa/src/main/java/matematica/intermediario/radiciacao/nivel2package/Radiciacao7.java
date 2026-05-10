package matematica.intermediario.radiciacao.nivel2package;

import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import modelo.matematica.Conta;
import pdf.util.Convert;


public class Radiciacao7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao7(int indice)
	{
		super(indice);

		int b = 1 + rand.nextInt(10);
		int x = 1 + rand.nextInt(5);
		
		while((b * x * x)==b)
			x = 1 + rand.nextInt(5);

		int c = b * x;
		int a = b * x * x;

		textLatex = "\\sqrt{" + a + "\\cdot" + b + "}" + "=";
		resultadoCorreto = "" + c;
		
		resolucaoLatex="\\sqrt{" + a + "\\cdot" + b + "}" + "=";
		resolucaoLatex+=ResolucaoRadiciacao.resolucao(a*b,2);
		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,200);

	}

	public static void main(String[] args)
	{
		new Radiciacao7(1);
	}

}
