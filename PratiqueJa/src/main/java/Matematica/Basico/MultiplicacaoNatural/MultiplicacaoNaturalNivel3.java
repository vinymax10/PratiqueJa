package Matematica.Basico.MultiplicacaoNatural;



import javax.persistence.Entity;

import Matematica.Basico.ResolucaoNatural.ResolucaoNatural;
import Modelo.Matematica.Conta;

@Entity
public class MultiplicacaoNaturalNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MultiplicacaoNaturalNivel3(int indice)
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

	public MultiplicacaoNaturalNivel3()
	{
	}
}
