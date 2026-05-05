package Matematica.Avancado.PA.Nivel3Package;

import Matematica.Racional;
import Matematica.Avancado.PA.ResolucaoPA;
import Modelo.Matematica.Conta;

public class Expressao3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao3(int index)
	{
		super(index);

		Racional a1=new Racional(1 + rand.nextInt(20),1 + rand.nextInt(20));
		a1.fatoracao(2);
		
		Racional r=new Racional(1 + rand.nextInt(20),1 + rand.nextInt(20));
		r.fatoracao(2);
		
		textLatex = "" + a1.showDfrac() + 
		", x, " + ResolucaoPA.a(a1,r,3).showDfrac()+", "+ResolucaoPA.a(a1,r,4).showDfrac()+"";

		pergunta="Qual o valor de \\(x\\)?";
		
		resultadoCorreto = "" + ResolucaoPA.a(a1,r,2).toString();
		
		resolucaoLatex=ResolucaoPA.x2(a1, r);
		
	}
	
	
	
	public static void main(String[] args)
	{
		new Expressao3(1);
	}
}
