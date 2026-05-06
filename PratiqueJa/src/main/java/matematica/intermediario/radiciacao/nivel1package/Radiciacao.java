package matematica.intermediario.radiciacao.nivel1package;

import matematica.intermediario.radiciacao.ResolucaoRadiciacao;
import modelo.matematica.Conta;
import pdf.Convert;


public class Radiciacao extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(20);

		textLatex = "\\sqrt{" + (int) (Math.pow(a, 2)) + "}" + "=";
		resultadoCorreto = "" + a;
		
		resolucaoLatex=ResolucaoRadiciacao.resolucao(a*a,2);
		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,180);

	}

	public static void main(String[] args)
	{
		new Radiciacao(1);
	}

}
