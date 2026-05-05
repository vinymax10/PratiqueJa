package Matematica.Avancado.PA.Nivel3Package;

import Matematica.Racional;
import Matematica.Avancado.PA.ResolucaoPA;
import Modelo.Matematica.Conta;

public class Expressao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao2(int index)
	{
		super(index);

		int n = 4 + rand.nextInt(20);

		Racional a1=new Racional(1 + rand.nextInt(20),1 + rand.nextInt(20));
		a1.fatoracao(2);
		
		Racional r=new Racional(1 + rand.nextInt(20),1 + rand.nextInt(20));
		r.fatoracao(2);
		
		Racional an = ResolucaoPA.a(a1, r, n);
		
		textLatex = "" + a1.showDfrac() + 
		", " + (a1.add(r).showDfrac())+", \\ldots, "+an.showDfrac()+"";

		pergunta="Quantos termos tem a PA?";
		
		resultadoCorreto = "" + n;
		
		resolucaoLatex=ResolucaoPA.numeroTermos(a1, r, an);
		
	}

	public static void main(String[] args)
	{
		new Expressao2(1);
	}
}
