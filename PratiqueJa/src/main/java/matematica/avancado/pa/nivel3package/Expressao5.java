package matematica.avancado.pa.nivel3package;

import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;
import modelo.matematica.Conta;

public class Expressao5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao5(int index)
	{
		super(index);

		Racional a1=new Racional(1 + rand.nextInt(20),1 + rand.nextInt(20));
		a1.fatoracao(2);
		
		Racional r=new Racional(1 + rand.nextInt(20),1 + rand.nextInt(20));
		r.fatoracao(2);
		
		textLatex = "" + a1.showDfrac() + ", "+
		ResolucaoPA.a(a1,r,2).showDfrac()+", "+ResolucaoPA.a(a1,r,3).showDfrac()+", x";

		pergunta="Qual o valor de \\(x\\)?";
		
		resultadoCorreto = "" + ResolucaoPA.a(a1,r,4).toString();
		
		resolucaoLatex=ResolucaoPA.x4(a1, r);
		
	}
	
	public static void main(String[] args)
	{
		new Expressao5(1);
	}
}
