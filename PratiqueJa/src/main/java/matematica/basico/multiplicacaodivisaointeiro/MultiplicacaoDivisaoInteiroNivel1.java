package matematica.basico.multiplicacaodivisaointeiro;



import jakarta.persistence.Entity;

import modelo.matematica.Conta;

@Entity
public class MultiplicacaoDivisaoInteiroNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public MultiplicacaoDivisaoInteiroNivel1(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(10);
		if(rand.nextBoolean())
			a*=-1;
		
		int b = 1 + rand.nextInt(10);
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

	public MultiplicacaoDivisaoInteiroNivel1()
	{
	}

	public static void main(String[] args)
	{
		new MultiplicacaoDivisaoInteiroNivel1(1);
	}

}
