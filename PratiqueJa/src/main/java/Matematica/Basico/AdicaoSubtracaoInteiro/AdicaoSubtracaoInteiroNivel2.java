package Matematica.Basico.AdicaoSubtracaoInteiro;



import javax.persistence.Entity;

import Matematica.Auxiliar;
import Modelo.Matematica.Conta;

@Entity
public class AdicaoSubtracaoInteiroNivel2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public AdicaoSubtracaoInteiroNivel2(int indice)
	{
		super(indice);
		int a = 1 + rand.nextInt(100);
		if(rand.nextBoolean())
			a*=-1;
		
		int b = 1 + rand.nextInt(100);
		if(rand.nextBoolean())
			b*=-1;
		
		textLatex = Auxiliar.getNumber(a,"",true) + Auxiliar.getNumber(b,"",false)+"=";
		resultadoCorreto = ""+(a+b);
		resolucaoLatex=ResolucaoASInteiro.soma(a,b);
	}

	public AdicaoSubtracaoInteiroNivel2()
	{
	}

	public static void main(String[] args)
	{
		new AdicaoSubtracaoInteiroNivel2(1);
	}

}
