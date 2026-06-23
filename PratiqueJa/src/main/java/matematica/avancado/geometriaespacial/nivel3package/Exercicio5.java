package matematica.avancado.geometriaespacial.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// [a, h, ap] onde ap² = h² + (a/2)² — tripla pitagórica garante ap inteiro
		int[][] params = {{6, 4, 5}, {8, 3, 5}, {10, 12, 13}, {12, 8, 10}, {16, 6, 10}};
		int[]   p      = params[rand.nextInt(params.length)];
		int a = p[0], h = p[1], ap = p[2];
		int pBase = 4 * a;
		int a2    = a * a;
		int aLat  = 2 * a * ap;
		int aTot  = aLat + a2;

		addParagrafo("Uma pirâmide de base quadrada tem lado \\(a = " + a
				+ "\\,\\text{cm}\\), altura \\(h = " + h
				+ "\\,\\text{cm}\\) e apótema lateral \\(a_p = " + ap
				+ "\\,\\text{cm}\\). Calcule a área total.");

		gerarAlternativasInteiras(aTot, 4, true);

		addResolucao("\\(A_{\\text{lateral}} = \\dfrac{P_{\\text{base}} \\times a_p}{2} = \\dfrac{"
				+ pBase + " \\times " + ap + "}{2} = " + aLat + "\\,\\text{cm}^2\\)");
		addResolucao("\\(A_{\\text{base}} = " + a + "^2 = " + a2 + "\\,\\text{cm}^2\\)");
		addResolucao("\\(A_{\\text{total}} = " + aLat + " + " + a2 + " = \\mathbf{" + aTot + "}\\,\\text{cm}^2\\)");
	}
}
