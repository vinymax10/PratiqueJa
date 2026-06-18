package matematica.avancado.geometriaanalitica.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int cx = -3 + rand.nextInt(7);
		int cy = -3 + rand.nextInt(7);
		int r  = 2 + rand.nextInt(6);
		int r2 = r * r;
		int D  = -2 * cx;
		int E  = -2 * cy;
		int F  = cx * cx + cy * cy - r2;

		String sinalD = D >= 0 ? " + " + D : " - " + Math.abs(D);
		String sinalE = E >= 0 ? " + " + E : " - " + Math.abs(E);
		String sinalF = F >= 0 ? " + " + F : " - " + Math.abs(F);

		int hD = D / 2;
		int hE = E / 2;
		int hD2 = hD * hD;
		int hE2 = hE * hE;

		addParagrafo("Identifique o raio da circunferência de equação geral "
				+ "\\(x^2 + y^2" + sinalD + "x" + sinalE + "y" + sinalF + " = 0\\).");

		gerarAlternativasInteiras(r);

		String sinalCx = cx >= 0 ? " - " + cx : " + " + Math.abs(cx);
		String sinalCy = cy >= 0 ? " - " + cy : " + " + Math.abs(cy);

		String res = "Completando o quadrado em \\(x\\) e em \\(y\\):"
				+ "\\(\\\\\\)"
				+ "\\((x^2" + sinalD + "x + " + hD2 + ") + (y^2" + sinalE + "y + " + hE2 + ") = -(" + F + ") + " + hD2 + " + " + hE2 + "\\\\"
				+ "(x" + sinalCx + ")^2 + (y" + sinalCy + ")^2 = " + r2 + "\\\\"
				+ "r^2 = " + r2 + " \\Rightarrow r = \\mathbf{" + r + "}\\)";
		setResolucao(res);
	}
}
