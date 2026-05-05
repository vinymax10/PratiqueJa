package Matematica.Basico.DivisaoNatural;

import javax.persistence.Entity;

import Matematica.Basico.ResolucaoNatural.ResolucaoNatural;
import Modelo.Matematica.Conta;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

@Entity
public class DivisaoNaturalNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public DivisaoNaturalNivel2(int indice)
	{
		super(indice);
		int a,b;
		if(rand.nextBoolean())
		{
			a = 10 + rand.nextInt(90);
			b = 1 + rand.nextInt(9);
		}
		else
		{
			a = 10 + rand.nextInt(90);
			b = 10 + rand.nextInt(90);
		}
		
		textLatex = ResolucaoNatural.divisao((a * b), b, false);
		resultadoCorreto = "" + a;
		resolucaoLatex = ResolucaoNatural.divisao((a * b), b, true);

	}

	public DivisaoNaturalNivel2()
	{
	}

	public static void main(String[] args)
	{
	}

}
