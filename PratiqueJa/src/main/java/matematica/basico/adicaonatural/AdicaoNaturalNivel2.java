package matematica.basico.adicaonatural;

import modelo.matematica.Conta;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import jakarta.persistence.Entity;

import matematica.basico.resolucaonatural.ResolucaoNatural;

@Entity
public class AdicaoNaturalNivel2 extends Conta {
	private static final long serialVersionUID = 1L;

	public AdicaoNaturalNivel2(int indice) {
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

		textLatex=ResolucaoNatural.soma(a,b,false);

		resultadoCorreto = "" + (a+b);
		
		resolucaoLatex=ResolucaoNatural.soma(a,b,true);
	}

	public AdicaoNaturalNivel2() {
	}

	public static void main(String[] args) {
	}

}
