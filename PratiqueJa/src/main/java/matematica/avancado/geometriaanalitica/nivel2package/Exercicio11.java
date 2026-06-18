package matematica.avancado.geometriaanalitica.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int cx = -4 + rand.nextInt(9);
		int cy = -4 + rand.nextInt(9);
		int r  = 2 + rand.nextInt(6);
		int r2 = r * r;
		int F  = cx * cx + cy * cy - r2;

		String sinalCx = cx >= 0 ? " - " + cx : " + " + Math.abs(cx);
		String sinalCy = cy >= 0 ? " - " + cy : " + " + Math.abs(cy);
		int D = -2 * cx, E = -2 * cy;

		addParagrafo("A circunferência de centro \\(C(" + cx + ";\\;" + cy + ")\\) e raio \\(r = " + r + "\\) "
				+ "tem equação geral \\(x^2 + y^2 + Dx + Ey + F = 0\\). "
				+ "Qual é o valor de \\(F\\)?");

		gerarAlternativasInteiras(F, 4, false);

		String cxStr = cx < 0 ? "(" + cx + ")" : Integer.toString(cx);
		String cyStr = cy < 0 ? "(" + cy + ")" : Integer.toString(cy);

		String res = "A forma canônica é \\((x" + sinalCx + ")^2 + (y" + sinalCy + ")^2 = " + r2 + "\\)."
				+ "\\(\\\\\\)"
				+ "Expandindo e identificando \\(F = a^2 + b^2 - r^2\\):"
				+ "\\(\\\\\\)"
				+ "\\(F = " + cxStr + "^2 + " + cyStr + "^2 - " + r + "^2\\\\"
				+ "F = " + (cx*cx) + " + " + (cy*cy) + " - " + r2 + " = \\mathbf{" + F + "}\\)";
		setResolucao(res);
	}
}
