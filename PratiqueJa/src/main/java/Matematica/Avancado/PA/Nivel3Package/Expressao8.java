package Matematica.Avancado.PA.Nivel3Package;

import Matematica.Racional;
import Matematica.Avancado.PA.ResolucaoPA;
import Modelo.Matematica.Conta;

public class Expressao8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao8(int index)
	{
		super(index);

		int n = 4 + rand.nextInt(20);
		
		Racional a1=new Racional(1 + rand.nextInt(20),1 + rand.nextInt(20));
		a1.fatoracao(2);
		
		Racional r=new Racional(1 + rand.nextInt(20),1 + rand.nextInt(20));
		r.fatoracao(2);
		
		Racional an = ResolucaoPA.a(a1, r, n);
		
		textLatex = "" + a1.showDfrac() + 
		"+ \\ldots + "+an.showDfrac()+"="+ResolucaoPA.soma(a1, an, n).showDfrac();

		pergunta="Quantos termos tem a PA?";
		
		resultadoCorreto = "" + n;
		
		resolucaoLatex=ResolucaoPA.resolucaoSoma2(a1, r, an, n);
		resolucaoLatex = resolucaoLatex.replace("(", "\\left(").replace(")", "\\right)");
	}

	public static void main(String[] args)
	{
		new Expressao8(1);
	}
}
