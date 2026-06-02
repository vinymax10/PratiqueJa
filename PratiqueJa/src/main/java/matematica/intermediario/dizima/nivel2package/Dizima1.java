package matematica.intermediario.dizima.nivel2package;

import matematica.intermediario.dizima.ResolucaoDizima;
import modelo.matematica.Conta;

public class Dizima1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Dizima1(int index)
	{
		super(index);

		int inteira = 1 + rand.nextInt(10);
		int periodica = 1 + rand.nextInt(8);

		String strNaoPeriodica = "";
		String strInteira = String.valueOf(inteira);
		String strPeriodica = String.valueOf(periodica);

		textLatex = ResolucaoDizima.textLatex(strInteira, strNaoPeriodica, strPeriodica) + " = ";

		resultadoCorreto = ResolucaoDizima.resultadoCorreto(strInteira, strNaoPeriodica, strPeriodica);

		resolucaoLatex = ResolucaoDizima.resolucaoSimples(strInteira, strPeriodica);
	}

	public static void main(String[] args)
	{
		new Dizima1(1);
	}

}
