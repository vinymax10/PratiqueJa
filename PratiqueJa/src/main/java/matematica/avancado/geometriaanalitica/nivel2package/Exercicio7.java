package matematica.avancado.geometriaanalitica.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int cx = -4 + rand.nextInt(9);
		int cy = -4 + rand.nextInt(9);
		int r  = 2 + rand.nextInt(6);
		int r2 = r * r;

		boolean askX = rand.nextBoolean();
		int answer = askX ? cx : cy;

		String sinalCx = cx >= 0 ? " - " + cx : " + " + Math.abs(cx);
		String sinalCy = cy >= 0 ? " - " + cy : " + " + Math.abs(cy);

		String equacao = "(x" + sinalCx + ")^2 + (y" + sinalCy + ")^2 = " + r2;
		String coord   = askX ? "abscissa" : "ordenada";
		String coordLatex = askX ? "abscissa" : "ordenada";

		addParagrafo("Dada a circunferência \\(" + equacao + "\\), "
				+ "identifique a " + coord + " do centro.");

		gerarAlternativasInteiras(answer, 4, false);

		String res = "Na forma canônica \\((x-a)^2 + (y-b)^2 = r^2\\) o centro é \\(C(a;\\;b)\\):"
				+ "\\(\\\\\\)"
				+ "\\(C = (" + cx + ";\\;" + cy + ") \\Rightarrow " + coordLatex + " = \\mathbf{" + answer + "}\\)";
		setResolucao(res);
	}
}
