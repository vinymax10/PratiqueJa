package matematica.basico.numerosdecimais.nivel2package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// a,b ÷ n — divisão de decimal por inteiro (resultado exato em décimos)
public class Decimal3 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		// n divisor em [2,5]; resultado em décimos em [12,49]
		int n = 2 + rand.nextInt(4);
		int resT = 12 + rand.nextInt(38);
		int aT = resT * n;

		String a = fmtT(aT);
		String result = fmtT(resT);

		addParagrafo("Calcule a divisão do número decimal pelo inteiro:");
		addParagrafo("\\(" + a + " \\div " + n + " = \\,?\\)");
		gerarAltT(resT);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& " + a + " \\div " + n + " = \\\\" +
			"& " + aT + " \\div " + n + " = " + resT + " \\\\" +
			"& \\text{1 casa decimal: } " + result + "\\end{aligned}\\)"
		);
	}
}
