package Matematica.Avancado.PG.Nivel1Package;

import Matematica.Racional;
import Matematica.Avancado.PG.ResolucaoPG;
import Modelo.Matematica.Conta;

public class Expressao4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao4(int index)
	{
		super(index);

		Racional a1=new Racional(1 + rand.nextInt(10));
		
		Racional q=new Racional(2 + rand.nextInt(6));
		
		textLatex = "" + a1.showDfrac() + ", "+
		ResolucaoPG.a(a1,q,2).showDfrac()+", x, "+ResolucaoPG.a(a1,q,4).showDfrac()+"";

		pergunta="Qual o valor de \\(x\\)?";
		
		resultadoCorreto = "" + ResolucaoPG.a(a1,q,3).toString();
		
		resolucaoLatex=ResolucaoPG.x3(a1, q);
		
	}
	
	public static void main(String[] args)
	{
		new Expressao4(1);
	}
}
