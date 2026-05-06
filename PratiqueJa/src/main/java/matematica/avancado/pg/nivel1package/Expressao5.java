package matematica.avancado.pg.nivel1package;

import matematica.Racional;
import matematica.avancado.pg.ResolucaoPG;
import modelo.matematica.Conta;

public class Expressao5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao5(int index)
	{
		super(index);

		Racional a1=new Racional(1 + rand.nextInt(10));
		
		Racional q=new Racional(2 + rand.nextInt(6));
		
		textLatex = "" + a1.showDfrac() + ", "+
		ResolucaoPG.a(a1,q,2).showDfrac()+", "+ResolucaoPG.a(a1,q,3).showDfrac()+", x";

		pergunta="Qual o valor de \\(x\\)?";
		
		resultadoCorreto = "" + ResolucaoPG.a(a1,q,4).toString();
		
		resolucaoLatex=ResolucaoPG.x4(a1, q);
		
	}
	
	public static void main(String[] args)
	{
		new Expressao5(1);
	}
}
