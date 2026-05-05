package Matematica.Basico.AdicaoSubtracaoInteiro;



import javax.persistence.Entity;

import Matematica.Auxiliar;
import Modelo.Matematica.Conta;

@Entity
public class AdicaoSubtracaoInteiroNivel1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public AdicaoSubtracaoInteiroNivel1(int indice)
	{
		super(indice);

		int a = 1 + rand.nextInt(10);
		if(rand.nextBoolean())
			a*=-1;
		
		int b = 1 + rand.nextInt(10);
		if(rand.nextBoolean())
			b*=-1;
		
		textLatex = Auxiliar.getNumber(a,"",true) + Auxiliar.getNumber(b,"",false)+"=";
		resultadoCorreto = ""+(a+b);
		resolucaoLatex=ResolucaoASInteiro.soma(a,b);
	}

	public AdicaoSubtracaoInteiroNivel1()
	{
	}

	public static void main(String[] args)
	{
		new AdicaoSubtracaoInteiroNivel1(1);
	}

}
