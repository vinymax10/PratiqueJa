package Matematica.Intermediario.Dizima.Nivel1Package;

import Matematica.Intermediario.Dizima.ResolucaoDizima;
import Modelo.Matematica.Conta;

public class Dizima1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Dizima1(int index)
	{
		super(index);

		int inteira = 0;
		
		int periodica=0;
		switch(rand.nextInt(3))
		{
			case 0: periodica = 1 + rand.nextInt(8);
				break;
			case 1: do
						periodica = 10 + rand.nextInt(89);
					while (periodica % 11 == 0); 
				break;
			case 2: do
						periodica = 100 + rand.nextInt(889);
					while (periodica % 111 == 0);
				break;
		}
		
		String strNaoPeriodica = "";
		String strInteira = String.valueOf(inteira);
		String strPeriodica = String.valueOf(periodica);

		textLatex = ResolucaoDizima.textLatex(strInteira, strNaoPeriodica, strPeriodica) + " = ";

		resultadoCorreto = ResolucaoDizima.resultadoCorreto(strInteira, strNaoPeriodica, strPeriodica);

		resolucaoLatex = ResolucaoDizima.resolucaoSimples(strInteira,strPeriodica);
		System.out.println("textLatex: "+textLatex);
		System.out.println("resultadoCorreto: "+resultadoCorreto);
		System.out.println("resolucaoLatex: "+resolucaoLatex);
	}

	public static void main(String[] args)
	{
		new Dizima1(1);
	}

}
