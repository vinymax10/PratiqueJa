package matematica.basico.racionais.nivel3package;

import matematica.Racional;
import matematica.basico.racionais.ResolucaoRacionais;
import modelo.matematica.Conta;

public class Racionais6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Racionais6(int indice)
	{
		super(indice);
		int max = 20;
		
		int a = 1 + rand.nextInt(max);
		
		if(rand.nextBoolean()) a*=-1;
		
		int b = 2 + rand.nextInt(max);
		while(a==b)	b = 2 + rand.nextInt(max);

		int c = 1 + rand.nextInt(max);
		
		if(rand.nextBoolean())	c*=-1;
		
		int d = 2 + rand.nextInt(max);
		while(c==d)	d = 2 + rand.nextInt(max);

		Racional aRacional=new Racional(a,b);
		Racional bRacional=new Racional(c,d);
		
		textLatex = aRacional.showDfrac() +" \\div ("+ bRacional.showDfrac()+")=";
		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		Racional resultado=aRacional.div(bRacional);
		resultado.fatoracao(2);
		
		resultadoCorreto = resultado.toString();
		
		resolucaoLatex = ResolucaoRacionais.divisao(a, b, c, d);
	}

	public Racionais6()
	{
	}

	public static void main(String[] args)
	{
		new Racionais6(1);
	}

}
