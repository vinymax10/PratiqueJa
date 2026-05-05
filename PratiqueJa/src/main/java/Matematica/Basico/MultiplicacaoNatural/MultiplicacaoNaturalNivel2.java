package Matematica.Basico.MultiplicacaoNatural;



import javax.persistence.Entity;

import Matematica.Basico.ResolucaoNatural.ResolucaoNatural;
import Modelo.Matematica.Conta;

@Entity
public class MultiplicacaoNaturalNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MultiplicacaoNaturalNivel2(int indice)
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
		
		if(a<b)
		{
			int aux=a;
			a=b;
			b=aux;
		}
		
		textLatex = ResolucaoNatural.multiplicacao(a,b,false);

		resultadoCorreto = ""+(a*b);
		
		resolucaoLatex=ResolucaoNatural.multiplicacao(a,b,true);

	}

	public MultiplicacaoNaturalNivel2()
	{
	}

}
