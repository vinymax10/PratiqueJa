package matematica.avancado.geometriaespacial.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// [a, h, ap] onde ap² = h² + (a/2)² — tripla pitagórica garante ap inteiro
		// a=6,h=4 → a/2=3, ap=5  |  a=8,h=3 → a/2=4, ap=5
		// a=10,h=12 → a/2=5, ap=13  |  a=12,h=8 → a/2=6, ap=10
		// a=16,h=6 → a/2=8, ap=10
		int[][] params = {{6, 4, 5}, {8, 3, 5}, {10, 12, 13}, {12, 8, 10}, {16, 6, 10}};
		int[]   p      = params[rand.nextInt(params.length)];
		int a = p[0], h = p[1], ap = p[2];
		int pBase = 4 * a;
		int aLat  = 2 * a * ap; // P_base * ap / 2 = 4a * ap / 2

		addParagrafo("Uma pirâmide de base quadrada tem lado \\(a = " + a
				+ "\\,\\text{cm}\\), altura \\(h = " + h
				+ "\\,\\text{cm}\\) e apótema lateral \\(a_p = " + ap
				+ "\\,\\text{cm}\\). Calcule a área lateral.");

		gerarAlternativasInteiras(aLat, 4, true);

		String res = "\\(A_{\\text{lateral}} = \\dfrac{P_{\\text{base}} \\times a_p}{2} =\\\\"
		+ " \\dfrac{"
				+ pBase + " \\times " + ap + "}{2} = \\dfrac{" + (pBase * ap) + "}{2} = \\mathbf{"
				+ aLat + "}\\,\\text{cm}^2\\)";
		setResolucao(res);
	}
}
