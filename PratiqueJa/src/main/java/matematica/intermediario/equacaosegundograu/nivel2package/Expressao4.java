package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.ParCor;
import matematica.Racional;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x1 = 1 + rand.nextInt(9);
		int x2 = 1 + rand.nextInt(9);
		if(rand.nextBoolean()) x2 *= -1;

		int a = 1 + rand.nextInt(5);
		if(rand.nextBoolean()) a *= -1;

		int b = -a * (x1 + x2);
		int c = a * x1 * x2;

		String eq = Auxiliar.getNumber(a, "x^2", true);
		eq += Auxiliar.getNumber(b, "x", false);
		eq += Auxiliar.getNumber(c, "", false);
		eq += "=0";

		Racional soma = new Racional(-b, a);
		soma.fatoracao(2);

		String res = "\\(" + ParCor.formula("x_1+x_2=\\dfrac{-b}{a}") + "\\)" + "\\(\\\\\\)";
		res += "Sabendo que \\(x_1=" + x1 + "\\): \\(\\\\\\)";
		res += "\\(" + x1 + "+x_2=" + soma.toStringLatex() + "\\)" + "\\(\\\\\\)";
		res += "\\(x_2=" + soma.toStringLatex() + "-" + x1 + "=\\mathbf{" + x2 + "}\\)";

		addParagrafo("Encontre \\(x_2\\), sabendo que \\(x_1=" + x1 + "\\)");
		addParagrafo("\\(" + eq + "\\)");
		gerarAlternativas("" + x2);
		setResolucao(res);
	}
}
