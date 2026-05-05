package Matematica.Avancado.PA.Nivel2Package;

import Matematica.Racional;
import Matematica.Avancado.PA.ResolucaoPA;
import Modelo.Matematica.Conta;

public class Expressao3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao3(int index)
	{
		super(index);

		int n = 4 + rand.nextInt(20);
		
		Racional a1=new Racional(1 + rand.nextInt(20));
		Racional r=new Racional(1 + rand.nextInt(20));
		Racional an = ResolucaoPA.a(a1, r, n);
		
		textLatex = "x" + 
		"+ \\ldots + "+an.showDfrac()+"="+ResolucaoPA.soma(a1, an, n).showDfrac();

		pergunta="Qual o valor de \\(x\\) na PA que possui "+n+" termos?";
		
		resultadoCorreto = "" + a1.toString();
		
		resolucaoLatex=ResolucaoPA.resolucaoSoma3(a1, r, an, n);
		
	}

	public static void main(String[] args)
	{
		new Expressao3(1);
	}
}
