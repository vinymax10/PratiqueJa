package Matematica.Basico.SubtracaoNatural;



import javax.persistence.Entity;

import Modelo.Matematica.Conta;

@Entity
public class SubtracaoNaturalNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SubtracaoNaturalNivel1(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);

		if(a < b)
		{
			int c = a;
			a = b;
			b = c;
		}

		textLatex=a+"-"+b+"=";

		resultadoCorreto = "" + (a-b);
		
		resolucaoLatex=a+"-"+b+"="+(a-b);
	}

	public SubtracaoNaturalNivel1()
	{
	}

}
