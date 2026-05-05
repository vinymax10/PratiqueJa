package Matematica.Basico.SubtracaoNatural;



import Modelo.Matematica.Conta;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import javax.persistence.Entity;

import Matematica.Basico.ResolucaoNatural.ResolucaoNatural;

@Entity
public class SubtracaoNaturalNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SubtracaoNaturalNivel3(int indice)
	{
		super(indice);

		int a,b;
		if(rand.nextBoolean())
		{
			a = 100 + rand.nextInt(900);
			b = 100 + rand.nextInt(900);
		}
		else
		{
			a = 100 + rand.nextInt(900);
			b = 10 + rand.nextInt(90);
		}

		if(a < b)
		{
			int c = a;
			a = b;
			b = c;
		}

		textLatex=ResolucaoNatural.subtracao(a,b,false);

		resultadoCorreto = "" + (a-b);
		
		resolucaoLatex=ResolucaoNatural.subtracao(a,b,true);
	}

	public SubtracaoNaturalNivel3()
	{
	}

	public static void main(String[] args)
	{
	}

}
