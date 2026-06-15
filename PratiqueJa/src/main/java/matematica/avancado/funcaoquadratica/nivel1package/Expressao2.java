package matematica.avancado.funcaoquadratica.nivel1package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(4);
		if(rand.nextBoolean()) a *= -1;
		int b = rand.nextInt(9) - 4;
		int c = rand.nextInt(9) - 4;

		String funcao = Auxiliar.getNumber(a, "x^2", true)
			+ Auxiliar.getNumber(b, "x", false)
			+ Auxiliar.getNumber(c, "", false);

		int delta = b * b - 4 * a * c;

		addParagrafo("Calcule o discriminante \\(\\Delta\\) de \\(f(x) = " + funcao + "\\)");

		int bSq = b * b;
		int term4ac = -4 * a * c;

		String res = "\\(\\Delta = b^2 - 4ac\\\\";
		res += "\\Delta = (" + b + ")^2 - 4 \\cdot (" + a + ") \\cdot (" + c + ")\\\\";
		res += "\\Delta = " + bSq + Auxiliar.getNumber(term4ac, "", false) + " = \\mathbf{" + delta + "}\\)";

		gerarAlternativas("" + delta);
		setResolucao(res);
	}
}
