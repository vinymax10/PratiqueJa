package Matematica.Basico.AdicaoNatural;

import Modelo.Matematica.Conta;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import javax.persistence.Entity;

import Matematica.Basico.ResolucaoNatural.ResolucaoNatural;

@Entity
public class AdicaoNaturalNivel3 extends Conta {
	private static final long serialVersionUID = 1L;

	public AdicaoNaturalNivel3(int indice) {
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

		textLatex=ResolucaoNatural.soma(a,b,false);

		resultadoCorreto = "" + (a+b);
		
		resolucaoLatex=ResolucaoNatural.soma(a,b,true);
	}

	public AdicaoNaturalNivel3() {
	}

	public static void main(String[] args) {
	}
}
