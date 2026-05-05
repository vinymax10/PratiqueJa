package Matematica.Avancado.PA.Nivel2Package;

import Matematica.Racional;
import Matematica.Avancado.PA.ResolucaoPA;
import Modelo.Matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int index)
	{
		super(index);

		int n = 4 + rand.nextInt(20);
		
		Racional a1=new Racional(1 + rand.nextInt(20));
		Racional r=new Racional(1 + rand.nextInt(20));
		Racional an = ResolucaoPA.a(a1, r, n);
		
		textLatex = "" + a1.showDfrac() + 
		", \\ldots, "+an.showDfrac()+"";

		pergunta="Qual é a soma da PA que possui "+n+" termos?";
		
		resultadoCorreto = "" + ResolucaoPA.soma(a1, an, n).toString();
		
		resolucaoLatex=ResolucaoPA.resolucaoSoma(a1, r, an, n);
		
	}

	public static void main(String[] args)
	{
		new Expressao1(1);
	}
}
