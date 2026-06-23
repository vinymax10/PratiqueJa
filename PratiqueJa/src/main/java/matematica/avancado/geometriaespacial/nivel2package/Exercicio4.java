package matematica.avancado.geometriaespacial.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r     = 2 + rand.nextInt(4); // 2..5
		int h     = 2 + rand.nextInt(8); // 2..9
		int r2    = r * r;
		int vCoef = r2 * h; // V = π·r²·h → V/π = r²·h

		addParagrafo("O volume de um cilindro é \\(" + vCoef + "\\pi\\,\\text{cm}^3\\) e o raio é \\(r = " + r
				+ "\\,\\text{cm}\\). Calcule a altura.");

		gerarAlternativasInteiras(h, 4, true);

		addResolucao("\\(V = \\pi r^2 h \\Rightarrow " + vCoef + "\\pi = \\pi \\cdot " + r2 + " \\cdot h\\)");
		addResolucao("\\(" + r2 + "h = " + vCoef + "\\)");
		addResolucao("\\(h = \\dfrac{" + vCoef + "}{" + r2 + "} = \\mathbf{" + h + "}\\,\\text{cm}\\)");
	}
}
