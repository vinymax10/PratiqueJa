package Matematica.Avancado.PG.Nivel3Package;

import Matematica.Racional;
import Matematica.Avancado.PG.ResolucaoPG;
import Modelo.Matematica.Conta;

public class Expressao4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao4(int index)
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
		
		textLatex = "" + a1.showDfrac() + ", "+
		ResolucaoPG.a(a1,q,2).showDfrac()+", x, "+ResolucaoPG.a(a1,q,4).showDfrac()+"";

		pergunta="Qual o valor de \\(x\\)?";
		
		resultadoCorreto = "" + ResolucaoPG.a(a1,q,3).toString();
		
		resolucaoLatex=ResolucaoPG.x3Frac(a1, q);
		resolucaoLatex = resolucaoLatex.replace("(", "\\left(").replace(")", "\\right)");

	}
	
	public static void main(String[] args)
	{
		new Expressao4(1);
	}
}
