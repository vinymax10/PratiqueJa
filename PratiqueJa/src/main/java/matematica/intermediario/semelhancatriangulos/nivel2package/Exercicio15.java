package matematica.intermediario.semelhancatriangulos.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		int b = 1 + rand.nextInt(5);
		if (a == b)
			b = (b % 5) + 1;
		int m = a * a;
		int n = b * b;
		int h = a * b;

		addParagrafo("Em um triângulo retângulo, a altura relativa à hipotenusa divide a hipotenusa "
				+ "em dois segmentos de comprimentos \\(" + m + "\\) e \\(" + n + "\\). "
				+ "Calcule essa altura \\(h\\).");

		gerarAlternativasInteiras(h);

		String res = "Pela relação métrica no triângulo retângulo:"
				+ "\\(\\\\\\)"
				+ "\\(h^2 = m \\cdot n\\\\"
				+ "h^2 = " + m + " \\times " + n + " = " + (m * n) + "\\\\"
				+ "h = \\sqrt{" + (m * n) + "} = \\mathbf{" + h + "}\\)";
		setResolucao(res);
	}
}
