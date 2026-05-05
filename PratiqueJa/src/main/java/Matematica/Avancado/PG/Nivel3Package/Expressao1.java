package Matematica.Avancado.PG.Nivel3Package;

import Matematica.Racional;
import Matematica.Avancado.PG.ResolucaoPG;
import Modelo.Matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int index)
	{
		super(index);

		Racional um=new Racional(1);

		Racional a1;
		do
		{
			a1=new Racional(1 + rand.nextInt(10),1 + rand.nextInt(10));
			a1.fatoracao(2);
		}
		while(a1.igual(um)||a1.denominador==1);

		Racional q;
		do
		{
			q=new Racional(1 + rand.nextInt(9),2 + rand.nextInt(8));
			q.fatoracao(2);
		}
		while(q.igual(um)||q.denominador==1);
		
		double nMaximo=(Math.log(50000/a1.numerador)/Math.log(q.numerador))+1;
		double dMaximo=(Math.log(50000/a1.denominador)/Math.log(q.denominador))+1;
		double maximo=Math.min(nMaximo, dMaximo);
		
		int n = 4 + rand.nextInt(Math.min(Math.max(1,(int)(maximo-4)),20));
		
		
		Racional an = ResolucaoPG.a(a1, q, n);
		
		textLatex = "" + a1.showDfrac() + 
		", " + ResolucaoPG.a(a1, q, 2).showDfrac()
		+", \\ldots";

		pergunta="Qual é o "+n+"º termo?";
		
		resultadoCorreto = "" + an.toString();
		
		resolucaoLatex=ResolucaoPG.n_esimoFrac(a1, q, n);
		resolucaoLatex = resolucaoLatex.replace("(", "\\left(").replace(")", "\\right)");

	}

	public static void main(String[] args)
	{
		new Expressao1(1);
	}
}
