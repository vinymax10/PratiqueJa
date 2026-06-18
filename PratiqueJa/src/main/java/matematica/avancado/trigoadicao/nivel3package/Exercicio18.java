package matematica.avancado.trigoadicao.nivel3package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// Bissecção cos²(α/2) = (1+cos(α))/2 com α no 2.º quadrante (cos(α)<0) → resultado pequeno
public class Exercicio18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// α em Q2: cos(α) = -q/r → cos²(α/2) = (1 + (-q/r))/2 = (r-q)/(2r)
		int[][] triples = {{3, 4, 5}, {5, 12, 13}, {8, 15, 17}, {7, 24, 25}};
		int[] t = triples[rand.nextInt(triples.length)];
		int p = t[0], q = t[1], r = t[2];

		// cos²(α/2) = (r-q)/(2r): simplificar com MDC
		int numRes = r - q;
		int denRes = 2 * r;
		int g = gcd(numRes, denRes);
		numRes /= g;
		denRes /= g;

		// sen²(α/2) = (r+q)/(2r) para comparação como distrator
		int numSin = r + q;
		int denSin = 2 * r;
		int gS = gcd(numSin, denSin);
		numSin /= gS;
		denSin /= gS;

		addParagrafo("Dado que \\(\\cos\\alpha = -\\dfrac{" + q + "}{" + r + "}\\)"
				+ " (2.º quadrante), use a fórmula \\(\\cos^2\\!\\dfrac{\\alpha}{2} = \\dfrac{1+\\cos\\alpha}{2}\\)"
				+ " para calcular \\(\\cos^2\\!\\dfrac{\\alpha}{2}\\).");

		String correta = "\\(\\dfrac{" + numRes + "}{" + denRes + "}\\)";
		List<String> distratores = new ArrayList<>();
		distratores.add("\\(\\dfrac{" + numSin + "}{" + denSin + "}\\)");             // sen²(α/2): confundiu + com -
		distratores.add("\\(\\dfrac{" + (q * q) + "}{" + (r * r) + "}\\)");          // cos²(α) direto
		distratores.add("\\(\\dfrac{" + q + "}{" + r + "}\\)");                       // só |cos(α)|, sem bissecção
		embaralharEAdicionarAlternativas(correta, distratores);

		setResolucao("No 2.º quadrante, \\(\\cos\\alpha = -\\dfrac{" + q + "}{" + r + "}\\)."
				+ "\\(\\\\\\)"
				+ "Aplicando a fórmula de bissecção:"
				+ "\\(\\\\\\)"
				+ "\\(\\cos^2\\!\\dfrac{\\alpha}{2} = \\dfrac{1 + \\left(-\\dfrac{" + q + "}{" + r + "}\\right)}{2} = \\\\"
				+ "\\dfrac{1 - \\dfrac{" + q + "}{" + r + "}}{2} = "
				+ "\\dfrac{\\dfrac{" + r + " - " + q + "}{" + r + "}}{2} = \\\\"
				+ "\\dfrac{" + (r - q) + "}{" + (2 * r) + "} = \\mathbf{\\dfrac{" + numRes + "}{" + denRes + "}}\\)");
	}

	private static int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
}
