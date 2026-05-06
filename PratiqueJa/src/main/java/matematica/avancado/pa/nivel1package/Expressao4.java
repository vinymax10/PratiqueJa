package matematica.avancado.pa.nivel1package;

import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;
import modelo.matematica.Conta;

public class Expressao4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao4(int index)
	{
		super(index);

		Racional a1=new Racional(1 + rand.nextInt(20));
		Racional r=new Racional(1 + rand.nextInt(20));
		
		textLatex = "" + a1.showDfrac() + ", "+
		ResolucaoPA.a(a1,r,2).showDfrac()+", x, "+ResolucaoPA.a(a1,r,4).showDfrac()+"";

		pergunta="Qual o valor de \\(x\\)?";
		
		resultadoCorreto = "" + ResolucaoPA.a(a1,r,3).toString();
		
		resolucaoLatex=ResolucaoPA.x3(a1, r);
		
	}
	
	
	
	public static void main(String[] args)
	{
		new Expressao4(1);
	}
}
