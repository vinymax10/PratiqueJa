package Matematica.Intermediario.Dizima.Nivel3Package;

import Matematica.Intermediario.Dizima.ResolucaoDizima;
import Modelo.Matematica.Conta;

public class Dizima extends Conta
{
	private static final long serialVersionUID = 1L;

	public Dizima(int index)
	{
		super(index);

		int inteira = 1 + rand.nextInt(10);
		int naoPeriodica = 1 + rand.nextInt(8);

		int periodica = 0;
		do
		{
			switch (rand.nextInt(3)) {
			case 0:
				periodica = 1 + rand.nextInt(8);
				break;
			case 1:
				do
					periodica = 10 + rand.nextInt(89);
				while (periodica % 11 == 0);
				break;
			case 2:
				do
					periodica = 100 + rand.nextInt(889);
				while (periodica % 111 == 0);
				break;
			}
		}
		while (periodica == naoPeriodica);

		String strNaoPeriodica = String.valueOf(naoPeriodica);

		String strInteira = String.valueOf(inteira);
		String strPeriodica = String.valueOf(periodica);

		textLatex = ResolucaoDizima.textLatex(strInteira, strNaoPeriodica, strPeriodica) + " = ";

		resultadoCorreto = ResolucaoDizima.resultadoCorreto(strInteira, strNaoPeriodica, strPeriodica);

		resolucaoLatex = ResolucaoDizima.resolucao(strInteira, strNaoPeriodica, strPeriodica);
	}

	public static void main(String[] args)
	{
		new Dizima(1);
	}

}
