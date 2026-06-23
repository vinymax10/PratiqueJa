package matematica.intermediario.semelhancatriangulos.nivel1package;

import matematica.GeradorExercicio;

public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int q = 1 + rand.nextInt(3);
		int p = q + 1 + rand.nextInt(3);
		int base = 2 + rand.nextInt(6);
		int M = p * base;
		int resposta = q * base;

		addParagrafo("Dois triângulos são semelhantes com razão de semelhança "
				+ "\\(k = \\dfrac{" + p + "}{" + q + "}\\). "
				+ "O lado do triângulo maior mede \\(" + M + "\\). "
				+ "Qual é a medida do lado correspondente no triângulo menor?");

		gerarAlternativasInteiras(resposta);

		addResolucao("A razão de semelhança relaciona lados correspondentes: "
				+ "\\(\\dfrac{\\text{maior}}{\\text{menor}} = \\dfrac{" + p + "}{" + q + "}\\).");
		addResolucao("\\(\\text{menor} = " + M + " \\times \\dfrac{" + q + "}{" + p + "}"
				+ " = \\dfrac{" + (q * M) + "}{" + p + "} = \\mathbf{" + resposta + "}\\)");
	}
}
