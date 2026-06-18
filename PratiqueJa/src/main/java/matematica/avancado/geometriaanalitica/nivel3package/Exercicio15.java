package matematica.avancado.geometriaanalitica.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x1 = -5 + rand.nextInt(11);
		int y1 = -5 + rand.nextInt(11);
		int mx = -5 + rand.nextInt(11);
		int my = -5 + rand.nextInt(11);
		int x2 = 2 * mx - x1;
		int y2 = 2 * my - y1;

		boolean askX = rand.nextBoolean();
		int answer = askX ? x2 : y2;
		String coord = askX ? "abscissa" : "ordenada";
		String letter = askX ? "x_B" : "y_B";

		addParagrafo("O ponto médio do segmento \\(AB\\) é \\(M(" + mx + ";\\;" + my + ")\\). "
				+ "Sabendo que \\(A(" + x1 + ";\\;" + y1 + ")\\), "
				+ "encontre a " + coord + " do ponto \\(B\\).");

		gerarAlternativasInteiras(answer, 4, false);

		String coord1 = askX ? "x" : "y";
		int m_val = askX ? mx : my;
		int p1_val = askX ? x1 : y1;

		String res = "Usando a fórmula do ponto médio e isolando a coordenada desconhecida:"
				+ "\\(\\\\\\)"
				+ "\\(M_{" + coord1 + "} = \\dfrac{" + coord1 + "_A + " + coord1 + "_B}{2}\\\\"
				+ m_val + " = \\dfrac{" + p1_val + " + " + letter + "}{2}\\\\"
				+ letter + " = 2 \\times " + m_val + " - (" + p1_val + ") = \\mathbf{" + answer + "}\\)";
		setResolucao(res);
	}
}
