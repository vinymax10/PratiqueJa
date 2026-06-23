package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.ParCor;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x1 = 1 + rand.nextInt(9);
		int delta = 2 + rand.nextInt(19);
		int a = 1 + rand.nextInt(9);
		if(rand.nextBoolean()) a *= -1;

		int b = delta - (2 * a * x1);
		int c = (-x1 * delta) + (a * x1 * x1);

		String eq = Auxiliar.getNumber(a, "x^2", true);
		eq += Auxiliar.getNumber(b, "x", false);
		eq += Auxiliar.getNumber(c, "", false);
		eq += "=0";

		int deltaCal = b * b - 4 * a * c;

		addParagrafo("Calcule o discriminante \\(\\Delta\\)");
		addParagrafo("\\(" + eq + "\\)");
		gerarAlternativas("" + deltaCal);
		addResolucao("\\(" + ParCor.formula("\\Delta=b^2-4ac") + "\\)");
		addResolucao("\\(a=" + a + ", \\quad b=" + b + ", \\quad c=" + c + "\\)");
		addResolucao("\\(\\Delta=" + (b < 0 ? "(" + b + ")" : b) + "^2-4\\cdot " + a + "\\cdot " + c + " = \\)");
		addResolucao("\\(\\Delta=" + (b * b) + Auxiliar.getNumber(-4 * a * c, "", false) + "=\\mathbf{" + deltaCal + "}\\)");
	}
}
