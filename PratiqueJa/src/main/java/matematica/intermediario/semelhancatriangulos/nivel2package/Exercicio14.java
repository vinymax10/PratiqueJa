package matematica.intermediario.semelhancatriangulos.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int p = 2 + rand.nextInt(3);
		int base = 3 + rand.nextInt(8);
		int A = base;
		int resultado = p * p * base;

		addParagrafo("Dois triângulos são semelhantes com razão de semelhança \\(k = " + p + "\\). "
				+ "A área do triângulo menor mede \\(" + A + "\\,\\text{cm}^2\\). "
				+ "Qual é a área do triângulo maior?");

		gerarAlternativasInteiras(resultado);

		String res = "A razão das áreas é igual ao quadrado da razão de semelhança:"
				+ "\\(\\\\\\)"
				+ "\\(\\dfrac{A'}{A} = k^2 = " + p + "^2 = " + (p * p) + "\\\\"
				+ "A' = A \\times " + (p * p) + " = " + A + " \\times " + (p * p)
				+ " = \\mathbf{" + resultado + "}\\,\\text{cm}^2\\)";
		setResolucao(res);
	}
}
