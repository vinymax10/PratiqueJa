package matematica.basico.subtracaonatural;



import modelo.matematica.Conta;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import jakarta.persistence.Entity;

import matematica.basico.resolucaonatural.ResolucaoNatural;

@Entity
public class SubtracaoNaturalNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public SubtracaoNaturalNivel2(int indice)
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

	public SubtracaoNaturalNivel2()
	{
	}

	public static void main(String[] args)
	{
	}

}
