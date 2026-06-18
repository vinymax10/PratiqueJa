package matematica.intermediario.pitagoras.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.pitagoras.ResolucaoPitagoras;

public class Image16 extends GeradorExercicio
{
	// {m, n, h}  onde h² = m·n e h é inteiro
	private static final int[][] CASOS = {
		{4,  9,  6},
		{1,  9,  3},
		{4,  16, 8},
		{9,  16, 12},
		{1,  4,  2},
		{4,  25, 10},
		{9,  25, 15},
		{16, 25, 20},
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int m = caso[0], n = caso[1], h = caso[2];

		addParagrafo("Em um triângulo retângulo, a altura relativa à hipotenusa divide-a em duas projeções "
			+ "\\(m = " + m + "\\) e \\(n = " + n + "\\). Qual o valor da altura \\(h\\)?");

		gerarAlternativas("" + h);

		String res = "Aplicamos a relação métrica da altura em triângulos retângulos:";
		res += "\\(\\\\\\)";
		res += "\\(" + ResolucaoPitagoras.formulaMetricaAltura() + "\\)";
		res += "\\(\\\\\\)";
		res += "\\(h^2 = " + m + " \\cdot " + n + " = " + (m * n) + " \\\\";
		res += "h = \\sqrt{" + (m * n) + "} = \\mathbf{" + h + "}\\)";
		setResolucao(res);
	}
}
