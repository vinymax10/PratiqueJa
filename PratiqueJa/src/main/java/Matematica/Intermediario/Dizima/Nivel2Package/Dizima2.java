package Matematica.Intermediario.Dizima.Nivel2Package;

import Matematica.Intermediario.Dizima.ResolucaoDizima;
import Modelo.Matematica.Conta;


public class Dizima2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Dizima2(int index)
	{
		super(index);

		int inteira = 1 + rand.nextInt(10);
		int periodica;

		if (rand.nextBoolean())
		{
			do
				periodica = 10 + rand.nextInt(89);
			while (periodica % 11 == 0);
		}
		else
		{
			do
				periodica = 100 + rand.nextInt(889);
			while (periodica % 111 == 0);
		}

		String strNaoPeriodica = "";

		String strInteira = String.valueOf(inteira);
		String strPeriodica = String.valueOf(periodica);

		textLatex = ResolucaoDizima.textLatex(strInteira, strNaoPeriodica, strPeriodica) + " = ";

		resultadoCorreto = ResolucaoDizima.resultadoCorreto(strInteira, strNaoPeriodica, strPeriodica);

		resolucaoLatex = ResolucaoDizima.resolucaoSimples(strInteira, strPeriodica);
//		System.out.println("textLatex: "+textLatex);
//		System.out.println("resultadoCorreto: "+resultadoCorreto);
//		System.out.println("resolucaoLatex: "+resolucaoLatex);
	}

	public static void main(String[] args)
	{
		new Dizima2(1);
	}

}
