package matematica.avancado.pg.nivel2package;

import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;
import modelo.matematica.Conta;

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
		
		textLatex = "" + a1.showDfrac() + ", "+ResolucaoPG.a(a1, q, 2).showDfrac()+
		", \\ldots ";

		pergunta="Qual é a soma da PG que possui "+n+" termos?";
		
		resultadoCorreto = "" + ResolucaoPG.soma(a1, q, n).toString();
		
		resolucaoLatex=ResolucaoPG.resolucaoSoma(a1, q, n);
		
	}

	public static void main(String[] args)
	{
		new Expressao1(1);
	}
}
