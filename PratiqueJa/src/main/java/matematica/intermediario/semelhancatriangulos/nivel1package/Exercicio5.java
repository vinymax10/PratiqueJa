package matematica.intermediario.semelhancatriangulos.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int q = 1 + rand.nextInt(3);
		int p = q + 1 + rand.nextInt(3);
		int base = 5 + rand.nextInt(10);
		int P = q * base;
		int resultado = p * base;

		addParagrafo("Dois triângulos são semelhantes com razão de semelhança "
				+ "\\(k = \\dfrac{" + p + "}{" + q + "}\\). "
				+ "O perímetro do triângulo menor mede \\(" + P + "\\,\\text{cm}\\). "
				+ "Qual é o perímetro do triângulo maior?");

		gerarAlternativasInteiras(resultado);

		addResolucao("A razão dos perímetros é igual à razão de semelhança:");
		addResolucao("\\(P' = P \\times \\dfrac{" + p + "}{" + q + "}"
				+ " = " + P + " \\times \\dfrac{" + p + "}{" + q + "}"
				+ " = \\mathbf{" + resultado + "}\\,\\text{cm}\\)");
	}
}
