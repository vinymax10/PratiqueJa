package Matematica.Avancado.PG.Nivel1Package;

import Matematica.Racional;
import Matematica.Avancado.PG.ResolucaoPG;
import Modelo.Matematica.Conta;

public class Expressao3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao3(int index)
	{
		super(index);

		Racional a1=new Racional(1 + rand.nextInt(10));
		
		Racional q=new Racional(2 + rand.nextInt(6));
		
		textLatex = "" + a1.showDfrac() + 
		", x, " + ResolucaoPG.a(a1,q,3).showDfrac()+", "+ResolucaoPG.a(a1,q,4).showDfrac()+"";

		pergunta="Qual o valor de \\(x\\)?";
		
		resultadoCorreto = "" + ResolucaoPG.a(a1,q,2).toString();
		
		resolucaoLatex=ResolucaoPG.x2(a1, q);
		
	}
	
	public static void main(String[] args)
	{
		new Expressao3(1);
	}
}
