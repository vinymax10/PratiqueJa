package Matematica.Avancado.PA.Nivel1Package;

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
		", " + ResolucaoPA.a(a1, r, 2).showDfrac()
		+", "+ResolucaoPA.a(a1, r, 3).showDfrac()+", \\ldots";

		pergunta="Qual é o "+n+"º termo?";
		
		resultadoCorreto = "" + an.toString();
		
		resolucaoLatex=ResolucaoPA.n_esimo(a1, r, n);
		
	}

	public static void main(String[] args)
	{
		new Expressao1(1);
	}
}
