package matematica.avancado.geometriaespacial.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int r    = 1 + rand.nextInt(5); // 1..5
		int r2   = r * r;
		int coef = 4 * r2; // A = coef·π

		addParagrafo("A superfície de uma esfera mede \\(" + coef + "\\pi\\,\\text{cm}^2\\). Calcule o raio.");

		gerarAlternativasInteiras(r, 4, true);

		addResolucao("\\(A_{\\text{sup}} = 4\\pi r^2 \\Rightarrow " + coef + "\\pi = 4\\pi r^2\\)");
		addResolucao("\\(r^2 = \\dfrac{" + coef + "}{4} = " + r2 + "\\)");
		addResolucao("\\(r = \\sqrt{" + r2 + "} = \\mathbf{" + r + "}\\,\\text{cm}\\)");
	}
}
