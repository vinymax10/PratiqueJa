package matematica.avancado.pg.nivel3package;

import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;
import modelo.matematica.Conta;

public class Expressao6 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao6(int index)
	{
		super(index);

		Racional a1=new Racional(1 + rand.nextInt(10),1 + rand.nextInt(10));
		a1.fatoracao(2);
		
		Racional q;
		Racional um=new Racional(1);
		do
		{
			q=new Racional(1 + rand.nextInt(9),2 + rand.nextInt(8));
			q.fatoracao(2);
		}
		while(q.igual(um)||q.denominador==1);
		
		textLatex = "x, "+ResolucaoPG.a(a1,q,2).showDfrac()+", "+ResolucaoPG.a(a1,q,3).showDfrac()
		+", "+ResolucaoPG.a(a1,q,4).showDfrac();

		pergunta="Qual o valor de \\(x\\)?";
		
		resultadoCorreto = "" + a1.toString();
		
		resolucaoLatex=ResolucaoPG.x1Frac(a1, q);
		resolucaoLatex = resolucaoLatex.replace("(", "\\left(").replace(")", "\\right)");

	}
	
	public static void main(String[] args)
	{
		new Expressao6(1);
	}
}
