package matematica.avancado.geometriaespacial.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Prisma de base quadrada com altura h = 2a → V = 2a³
		// a=3: V=54  |  a=4: V=128  |  a=5: V=250
		int a = 3 + rand.nextInt(3); // 3..5
		int V = 2 * a * a * a;

		addParagrafo("Um prisma de base quadrada tem altura \\(h = 2a\\), onde \\(a\\) é o lado da base. "
				+ "Se o volume é \\(" + V + "\\,\\text{cm}^3\\), calcule a aresta \\(a\\).");

		gerarAlternativasInteiras(a, 4, true);

		addResolucao("\\(V = a^2 \\cdot h = a^2 \\cdot 2a = 2a^3\\)");
		addResolucao("\\(2a^3 = " + V + "\\)");
		addResolucao("\\(a^3 = \\dfrac{" + V + "}{2} = " + (V / 2) + "\\)");
		addResolucao("\\(a = \\sqrt[3]{" + (V / 2) + "} = \\mathbf{" + a + "}\\,\\text{cm}\\)");
	}
}
