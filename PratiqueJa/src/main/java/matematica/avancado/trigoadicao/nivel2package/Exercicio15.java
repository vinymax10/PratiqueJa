package matematica.avancado.trigoadicao.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// Bissecção: cos²(α/2) = (1 + cos(α)) / 2 com tripla pitagórica
public class Exercicio15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// cos(α) = q/r; cos²(α/2) = (r+q)/(2r)
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];
		int numRes = r + q;  // numerador de (r+q)/(2r)
		int denRes = 2 * r;  // denominador

		// simplificação: gcd(r+q, 2r) pode ser > 1 para alguns; verificar
		// {3,4,5}: (9,10) gcd=1; {5,12,13}: (25,26) gcd=1; {8,15,17}: (32,34) gcd=2→16/17; {7,24,25}: (49,50) gcd=1
		int g = gcd(numRes, denRes);
		numRes /= g;
		denRes /= g;

		addParagrafo("Dado que \\(\\cos\\alpha = \\dfrac{" + q + "}{" + r
				+ "}\\) (1.º quadrante), use a fórmula de bissecção"
				+ " \\(\\cos^2\\!\\dfrac{\\alpha}{2} = \\dfrac{1+\\cos\\alpha}{2}\\)"
				+ " para calcular \\(\\cos^2\\!\\dfrac{\\alpha}{2}\\).");

		String correta = "\\(\\dfrac{" + numRes + "}{" + denRes + "}\\)";
		List<String> distratores = new ArrayList<>();
		// sen²(α/2) = (r-q)/(2r)
		int numSin = (r - q) / g, denSin = denRes;
		int gS = gcd(r - q, 2 * r); numSin = (r - q) / gS; denSin = 2 * r / gS;
		distratores.add("\\(\\dfrac{" + numSin + "}{" + denSin + "}\\)");
		distratores.add("\\(\\dfrac{" + q + "}{" + r + "}\\)");
		distratores.add("\\(\\dfrac{" + (q * q) + "}{" + (r * r) + "}\\)");
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("Com \\(\\cos\\alpha = \\dfrac{" + q + "}{" + r + "}\\):"
				+ "\\(\\\\\\)"
				+ "\\(\\cos^2\\!\\dfrac{\\alpha}{2} = \\dfrac{1 + \\dfrac{" + q + "}{" + r + "}}{2} = \\\\"
				+ "\\dfrac{\\dfrac{" + r + " + " + q + "}{" + r + "}}{2} = "
				+ "\\dfrac{" + (r + q) + "}{" + (2 * r) + "} = \\mathbf{\\dfrac{" + numRes + "}{" + denRes + "}}\\)");
	}

	private static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
}
