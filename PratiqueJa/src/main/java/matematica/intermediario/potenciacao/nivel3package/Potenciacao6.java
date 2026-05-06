package matematica.intermediario.potenciacao.nivel3package;

import modelo.matematica.Conta;


public class Potenciacao6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Potenciacao6(int indice)
	{
		super(indice);

		int a = 2 + rand.nextInt(9);
		
		int potenciaMaxima=6;
		int maxBase = (int) Math.min((Math.log(1000) / Math.log(a)), potenciaMaxima);
		int p = 2+ rand.nextInt(maxBase + 1);
		
		int resultado=(int)Math.pow(a, p);
		pergunta="Qual o valor de \\(x\\)?";
		
		double divisor=2 + 2*rand.nextDouble();
		
		int termo1=(int)((double)resultado/divisor);
		int termo2=resultado-termo1;
		textLatex=termo1+"+"+termo2+"="+a+"^{x}";
		
		resultadoCorreto = "" + p;
		resolucaoLatex = resultado+"="+a+"^{x}\\\\";
		resolucaoLatex += a+"^{"+(p)+"}="+a+"^{x}\\\\";
		resolucaoLatex+="x="+(p);
	}

	public static void main(String[] args)
	{
		new Potenciacao6(1);
	}

}
