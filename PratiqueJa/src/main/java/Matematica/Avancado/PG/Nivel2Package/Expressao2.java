package Matematica.Avancado.PG.Nivel2Package;

import Matematica.Racional;
import Matematica.Avancado.PG.ResolucaoPG;
import Modelo.Matematica.Conta;

public class Expressao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao2(int index)
	{
		super(index);

		Racional a1=new Racional(1 + rand.nextInt(10));
		
		Racional q=new Racional(2 + rand.nextInt(6));
		
		double nMaximo=(Math.log(50000/a1.numerador)/Math.log(q.numerador))+1;
		
		int n = 4 + rand.nextInt(Math.min(Math.max(1,(int)(nMaximo-4)),20));
		
		textLatex = "" + a1.showDfrac() + "+"+ResolucaoPG.a(a1, q, 2).showDfrac()+
		"+ \\ldots ="+ResolucaoPG.soma(a1, q, n).showDfrac();

		pergunta="Quantos termos tem a PG?";
		
		resultadoCorreto = "" + n;
		
		resolucaoLatex=ResolucaoPG.resolucaoSoma2(a1, q,  n);
		
	}

	public static void main(String[] args)
	{
		new Expressao2(1);
	}
}
