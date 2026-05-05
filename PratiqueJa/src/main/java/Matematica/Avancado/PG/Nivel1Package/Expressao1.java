package Matematica.Avancado.PG.Nivel1Package;

import Matematica.Racional;
import Matematica.Avancado.PG.ResolucaoPG;
import Modelo.Matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int index)
	{
		super(index);

		Racional a1=new Racional(1 + rand.nextInt(10));
		
		Racional q=new Racional(2 + rand.nextInt(6));
		
		double nMaximo=(Math.log(50000/a1.numerador)/Math.log(q.numerador))+1;
		
		int n = 4 + rand.nextInt(Math.min(Math.max(1,(int)(nMaximo-4)),20));
		
		Racional an = ResolucaoPG.a(a1, q, n);
		
		textLatex = "" + a1.showDfrac() + 
		", " + ResolucaoPG.a(a1, q, 2).showDfrac()
		+", "+ResolucaoPG.a(a1, q, 3).showDfrac()+", \\ldots";

		pergunta="Qual é o "+n+"º termo?";
		
		resultadoCorreto = "" + an.toString();
		
		resolucaoLatex=ResolucaoPG.n_esimo(a1, q, n);
		
	}

	public static void main(String[] args)
	{
		new Expressao1(1);
	}
}
