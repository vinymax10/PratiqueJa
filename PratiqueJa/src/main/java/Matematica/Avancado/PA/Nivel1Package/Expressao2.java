package Matematica.Avancado.PA.Nivel1Package;

import Matematica.Racional;
import Matematica.Avancado.PA.ResolucaoPA;
import Modelo.Matematica.Conta;

public class Expressao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao2(int index)
	{
		super(index);

		int termo = 4 + rand.nextInt(20);
		int razao = 1 + rand.nextInt(20);
		int a1= 1 + rand.nextInt(20);
		
		Racional a1Racional=new Racional(a1);
		Racional razaoRacional=new Racional(razao);
		Racional an = new Racional(a1);
		for(int i = 0; i < termo-1; i++)
			an=an.add(razaoRacional);
		
		textLatex = "" + a1Racional.showDfrac() + 
		", " + (a1Racional.add(razaoRacional).showDfrac())+", \\ldots, "+an.showDfrac()+"";

		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		pergunta="Quantos termos tem a PA?";
		
		resultadoCorreto = "" + termo;
		
		resolucaoLatex=ResolucaoPA.numeroTermos(a1Racional, razaoRacional, an);
		
	}

	public static void main(String[] args)
	{
		new Expressao2(1);
	}
}
