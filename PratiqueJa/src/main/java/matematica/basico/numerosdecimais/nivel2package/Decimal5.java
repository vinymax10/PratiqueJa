package matematica.basico.numerosdecimais.nivel2package;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// Frações usuais (1/2, 1/4, 3/4, 1/5…) → decimal
public class Decimal5 extends AgrupadorDecimal
{
	// {numerador, denominador, resultado em centésimos}
	private static final int[][] TABELA = {
		{1, 2, 50},
		{1, 4, 25},
		{3, 4, 75},
		{1, 5, 20},
		{2, 5, 40},
		{3, 5, 60},
		{4, 5, 80},
	};

	@Override
	protected void construir()
	{
		int[] entrada = TABELA[rand.nextInt(TABELA.length)];
		int num = entrada[0], den = entrada[1], resH = entrada[2];

		String result = fmtH(resH);

		addParagrafo("Qual é o número decimal equivalente à fração abaixo?");
		addParagrafo("\\(\\dfrac{" + num + "}{" + den + "}\\)");
		gerarAltH(resH);

		addResolucao("\\(\\dfrac{" + num + "}{" + den + "} = " + num + " \\div " + den + " = " + result + "\\)");
	}
}
