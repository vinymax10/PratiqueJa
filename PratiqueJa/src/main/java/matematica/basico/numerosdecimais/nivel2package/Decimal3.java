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

		addResolucao("Dividimos ignorando a vírgula e depois recolocamos:");
		addResolucao("\\(" + aT + " \\div " + n + " = " + resT + "\\)");
		addResolucao("O dividendo tem 1 casa decimal, então o resultado também: \\(\\mathbf{" + result + "}\\)");
	}
}
