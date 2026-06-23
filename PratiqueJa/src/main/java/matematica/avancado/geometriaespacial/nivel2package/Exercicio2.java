package matematica.avancado.geometriaespacial.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a  = 2 + rand.nextInt(6); // 2..7 (lado da base quadrada)
		int k  = 1 + rand.nextInt(4); // 1..4
		int h  = 3 * k;               // altura múltipla de 3 → V inteiro
		int a2 = a * a;
		int V  = a2 * h / 3;

		addParagrafo("Uma pirâmide de base quadrada tem lado \\(a = " + a + "\\,\\text{cm}\\) e altura \\(h = "
				+ h + "\\,\\text{cm}\\). Calcule o volume.");

		gerarAlternativasInteiras(V, 4, true);

		addResolucao("\\(V = \\dfrac{A_{\\text{base}} \\times h}{3} = \\dfrac{" + a2 + " \\times " + h
				+ "}{3} = \\dfrac{" + (a2 * h) + "}{3} = \\mathbf{" + V + "}\\,\\text{cm}^3\\)");
	}
}
