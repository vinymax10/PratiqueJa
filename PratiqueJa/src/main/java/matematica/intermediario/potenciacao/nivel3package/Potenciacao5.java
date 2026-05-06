package matematica.intermediario.potenciacao.nivel3package;

import modelo.matematica.Conta;


public class Potenciacao5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Potenciacao5(int indice)
	{
		super(indice);

		int a = 2 + rand.nextInt(19);
		
		int p1 =2+ rand.nextInt(9);
		int p2 =2+ rand.nextInt(9);

		pergunta="Qual o valor de \\(x\\)?";
		
		textLatex="\\dfrac{"+a+"^{"+p1+"}}{"+a+"^{"+p2+"}}="+a+"^{x}";
		
		resultadoCorreto = "" + (p1-p2);
		resolucaoLatex = a+"^{"+p1+"-"+p2+"}="+a+"^{x}\\\\";
		resolucaoLatex += a+"^{"+(p1-p2)+"}="+a+"^{x}\\\\";
		resolucaoLatex+="x="+(p1-p2);

	}

	public static void main(String[] args)
	{
		new Potenciacao5(1);
	}

}
