package matematica.avancado.pg.nivel2package;

import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;
import modelo.matematica.Conta;

public class Expressao3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao3(int index)
	{
		super(index);

		Racional a1=new Racional(1 + rand.nextInt(10));
		
		Racional q=new Racional(2 + rand.nextInt(6));
		
		double nMaximo=(Math.log(50000/a1.numerador)/Math.log(q.numerador))+1;
		
		int n = 4 + rand.nextInt(Math.min(Math.max(1,(int)(nMaximo-4)),20));
		Racional an = ResolucaoPG.a(a1, q, n);
		
		textLatex = "x" + 
		"+ \\ldots + "+ResolucaoPG.a(a1, q, n-1).showDfrac()+"+"+an.showDfrac()+"="+ResolucaoPG.soma(a1, q, n).showDfrac();

		pergunta="Qual o valor de \\(x\\) na PG que possui "+n+" termos?";
		
		resultadoCorreto = "" + a1.toString();
		
		resolucaoLatex=ResolucaoPG.resolucaoSoma3(a1, q, n);
		
	}

	public static void main(String[] args)
	{
		new Expressao3(1);
	}
}
