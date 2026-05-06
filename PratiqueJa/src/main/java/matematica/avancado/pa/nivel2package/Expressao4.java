package matematica.avancado.pa.nivel2package;

import matematica.Racional;
import matematica.avancado.pa.ResolucaoPA;
import modelo.matematica.Conta;

public class Expressao4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao4(int index)
	{
		super(index);

		int n = 4 + rand.nextInt(20);
		
		Racional a1=new Racional(1 + rand.nextInt(20));
		Racional r=new Racional(1 + rand.nextInt(20));
		Racional an = ResolucaoPA.a(a1, r, n);
		
		textLatex = "" +a1.showDfrac()+ 
		"+ \\ldots + x="+ResolucaoPA.soma(a1, an, n).showDfrac();

		pergunta="Qual o valor de \\(x\\) na PA que possui "+n+" termos?";
		
		resultadoCorreto = "" + an.toString();
		
		resolucaoLatex=ResolucaoPA.resolucaoSoma4(a1, r, an, n);
		
	}

	public static void main(String[] args)
	{
		new Expressao4(1);
	}
}
