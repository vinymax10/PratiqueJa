package matematica.avancado.pg.nivel1package;

import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;
import modelo.matematica.Conta;

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
		
		Racional an = ResolucaoPG.a(a1, q, n);
		
		textLatex = "" + a1.showDfrac() + 
		", " + (ResolucaoPG.a(a1,q,2).showDfrac())+", \\ldots, "+an.showDfrac()+"";

		pergunta="Quantos termos tem a PG?";
		
		resultadoCorreto = "" + n;
		
		resolucaoLatex=ResolucaoPG.numeroTermos(a1, q, an,n);
		
	}

	public static void main(String[] args)
	{
		new Expressao2(1);
	}
}
