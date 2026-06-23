package matematica.avancado.geometriaanalitica.nivel3package;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public class Exercicio4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int cx = -3 + rand.nextInt(7);
		int cy = -3 + rand.nextInt(7);
		int r  = 2 + rand.nextInt(5);
		int r2 = r * r;

		int caseType = rand.nextInt(3);
		int x0, y0, d2;

		if (caseType == 0)
		{
			x0 = cx + 1; y0 = cy; d2 = 1;
		}
		else if (caseType == 1)
		{
			x0 = cx + r; y0 = cy; d2 = r2;
		}
		else
		{
			x0 = cx + r + 1; y0 = cy; d2 = (r + 1) * (r + 1);
		}

		int dx = x0 - cx, dy = y0 - cy;
		String posicao = d2 < r2 ? "interior" : (d2 == r2 ? "na circunferência" : "exterior");
		String sinalD2 = d2 < r2 ? "<" : (d2 == r2 ? "=" : ">");

		addParagrafo("Em relação à circunferência de centro \\(C(" + cx + ";\\;" + cy + ")\\) e raio \\(r = " + r + "\\), "
				+ "determine a posição do ponto \\(P(" + x0 + ";\\;" + y0 + ")\\):");
		addParagrafo("Calcule \\(d^2(P,C)\\) e compare com \\(r^2 = " + r2 + "\\).");

		List<String> distratores = new ArrayList<>();
		if (!posicao.equals("interior"))         distratores.add("\\(\\text{interior}\\)");
		if (!posicao.equals("na circunferência")) distratores.add("\\(\\text{na circunferência}\\)");
		if (!posicao.equals("exterior"))         distratores.add("\\(\\text{exterior}\\)");
		embaralharEAdicionarAlternativas("\\(\\text{" + posicao + "}\\)", distratores);

		addResolucao("Calculamos \\(d^2(P,C) = (x_0 - a)^2 + (y_0 - b)^2\\):");
		addResolucao("\\(d^2 = (" + dx + ")^2 + (" + dy + ")^2 = " + (dx * dx) + " + " + (dy * dy) + " = \\mathbf{" + d2 + "}\\)");
		addResolucao("Como \\(d^2 = " + d2 + " " + sinalD2 + " r^2 = " + r2 + "\\), "
				+ "o ponto \\(P\\) está \\(\\mathbf{" + posicao + "}\\) da circunferência.");
	}
}
