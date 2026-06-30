package matematica.intermediario.radiciacao.nivel2package;

import java.util.Arrays;

import matematica.GeradorExercicio;

// Potência de expoente fracionário: a^(p/q) = (√[q]{a})^p
public class Radiciacao13 extends GeradorExercicio
{
	// {base, p, q, resultado, √[q]{base}}
	private static final int[][] CASOS = {
		{4,   1, 2,  2,  2},
		{4,   3, 2,  8,  2},
		{8,   1, 3,  2,  2},
		{8,   2, 3,  4,  2},
		{9,   1, 2,  3,  3},
		{9,   3, 2, 27,  3},
		{16,  1, 2,  4,  4},
		{16,  1, 4,  2,  2},
		{25,  1, 2,  5,  5},
		{27,  1, 3,  3,  3},
		{27,  2, 3,  9,  3},
		{32,  1, 5,  2,  2},
		{64,  1, 3,  4,  4},
		{64,  2, 3, 16,  4},
		{100, 1, 2, 10, 10},
		{125, 1, 3,  5,  5},
	};

	@Override
	protected void construir()
	{
		int[] caso = CASOS[rand.nextInt(CASOS.length)];
		int base = caso[0], p = caso[1], q = caso[2], res = caso[3], raiz = caso[4];

		String expoente = "\\dfrac{" + p + "}{" + q + "}";

		addParagrafo("Calcule:");
		addParagrafo("\\(" + base + "^{" + expoente + "}\\)");

		int e1 = res + 1;
		int e2 = res > 2 ? res - 1 : res + 2;
		int e3 = (base == res) ? res + 4 : base;
		// Quando {4,1,2,2,2}: res=2 → e2=4=base=e3; ajustar e3
		if (e3 == e2) e3 = e2 + 1;
		embaralharEAdicionarAlternativas("" + res, Arrays.asList("" + e1, "" + e2, "" + e3));

		if(p == 1)
		{
			addResolucao(
				"\\(" + base + "^{" + expoente + "} = \\sqrt[" + q + "]{" + base + "} = \\mathbf{" + res + "}\\)"
			);
		}
		else
		{
			addResolucao(
				"\\(" + base + "^{" + expoente + "} = \\left(\\sqrt[" + q + "]{" + base + "}\\right)^{" + p + "} = " + raiz + "^{" + p + "} = \\mathbf{" + res + "}\\)"
			);
		}
	}
}
