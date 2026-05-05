package Matematica.Basico.MultiplicacaoDivisaoInteiro;



import jakarta.persistence.Entity;

import Modelo.Matematica.Conta;

@Entity
public class MultiplicacaoDivisaoInteiroNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MultiplicacaoDivisaoInteiroNivel2(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(100);
		if(rand.nextBoolean())
			a*=-1;
		
		int b = 1 + rand.nextInt(100);
		if(rand.nextBoolean())
			b*=-1;
		
		boolean divisao=rand.nextBoolean();
		
		if(divisao)
		{
			textLatex=""+(a * b)+"\\div "+b+"=";
			resultadoCorreto = ""+a;
			resolucaoLatex=ResolucaoMDInteiro.divisao((a * b),b);
		}
		else
		{
			textLatex=""+a+"\\times "+b+"=";
			resultadoCorreto = ""+(a * b);
			resolucaoLatex=ResolucaoMDInteiro.multiplicacao(a,b);
		}
		
	}

	public MultiplicacaoDivisaoInteiroNivel2()
	{
	}

	public static void main(String[] args)
	{
		new MultiplicacaoDivisaoInteiroNivel2(1);
	}

}
