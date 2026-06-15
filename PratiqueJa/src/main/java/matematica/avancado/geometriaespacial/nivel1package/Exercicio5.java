package matematica.avancado.geometriaespacial.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Prisma de base triangular retângula com catetos c1 e c2
		// Garante A_base inteiro: c1*c2 deve ser par
		int c1 = 2 + rand.nextInt(5); // 2..6
		int c2 = 2 + rand.nextInt(5); // 2..6
		if ((c1 * c2) % 2 != 0)
			c2++;
		int h     = 2 + rand.nextInt(7); // 2..8
		int aBase = c1 * c2 / 2;
		int V     = aBase * h;

		addParagrafo("A base de um prisma é um triângulo retângulo com catetos \\(" + c1
				+ "\\,\\text{cm}\\) e \\(" + c2 + "\\,\\text{cm}\\). A altura do prisma é \\("
				+ h + "\\,\\text{cm}\\). Calcule o volume.");

		gerarAlternativasInteiras(V, 4, true);

		String res = "A área da base triangular é \\(A_{\\text{base}} = \\dfrac{" + c1 + " \\times " + c2
				+ "}{2} = " + aBase + "\\,\\text{cm}^2\\)."
				+ "\\(\\\\\\)"
				+ "\\(V = A_{\\text{base}} \\times h = " + aBase + " \\times " + h
				+ " = \\mathbf{" + V + "}\\,\\text{cm}^3\\)";
		setResolucao(res);
	}
}
