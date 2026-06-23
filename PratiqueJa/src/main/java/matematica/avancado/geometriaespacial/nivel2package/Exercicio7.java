package matematica.avancado.geometriaespacial.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// r=3 → V=36π  |  r=6 → V=288π  (4r³/3 é inteiro para r múltiplo de 3)
		int k     = 1 + rand.nextInt(2);
		int r     = 3 * k;
		int r3    = r * r * r;
		int vCoef = 4 * r3 / 3;

		addParagrafo("O volume de uma esfera é \\(" + vCoef + "\\pi\\,\\text{cm}^3\\). Calcule o raio.");

		gerarAlternativasInteiras(r, 4, true);

		addResolucao("\\(V = \\dfrac{4\\pi r^3}{3} \\Rightarrow " + vCoef + "\\pi = \\dfrac{4\\pi r^3}{3}\\)");
		addResolucao("\\(4r^3 = 3 \\times " + vCoef + " = " + (3 * vCoef) + "\\)");
		addResolucao("\\(r^3 = \\dfrac{" + (3 * vCoef) + "}{4} = " + r3 + "\\)");
		addResolucao("\\(r = \\sqrt[3]{" + r3 + "} = \\mathbf{" + r + "}\\,\\text{cm}\\)");
	}
}
