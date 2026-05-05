package Matematica.Basico.AdicaoSubtracaoInteiro;



import jakarta.persistence.Entity;

import Matematica.Auxiliar;
import Modelo.Matematica.Conta;

@Entity
public class AdicaoSubtracaoInteiroNivel3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public AdicaoSubtracaoInteiroNivel3(int indice)
	{
		super(indice);
		int a = 1 + rand.nextInt(1000);
		if(rand.nextBoolean())
			a*=-1;
		
		int b = 1 + rand.nextInt(1000);
		if(rand.nextBoolean())
			b*=-1;
		
		textLatex = Auxiliar.getNumber(a,"",true) + Auxiliar.getNumber(b,"",false)+"=";
		resultadoCorreto = ""+(a+b);
		resolucaoLatex=ResolucaoASInteiro.soma(a,b);
	}

	public AdicaoSubtracaoInteiroNivel3()
	{
	}

	public static void main(String[] args)
	{
		new AdicaoSubtracaoInteiroNivel3(1);
	}

}
