package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.ParCor;
import matematica.Racional;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x1 = 1 + rand.nextInt(9);
		int delta = 1 + rand.nextInt(9);
		int a = 1 + rand.nextInt(9);
		if(rand.nextBoolean()) a *= -1;

		int b = delta - (2 * a * x1);
		int c = (-x1 * delta) + (a * x1 * x1);

		String eq = Auxiliar.getNumber(a, "x^2", true);
		eq += Auxiliar.getNumber(b, "x", false);
		eq += Auxiliar.getNumber(c, "", false);
		eq += "=0";

		Racional resultado = new Racional(-b, a);
		resultado.fatoracao(2);

		addParagrafo("Encontre a soma das raízes");
		addParagrafo("\\(" + eq + "\\)");
		gerarAlternativas(resultado);
		addResolucao("\\(" + ParCor.formula("x_1+x_2=\\dfrac{-b}{a}") + "\\)");
		addResolucao("\\(a=" + a + ", \\quad b=" + b + "\\)");
		if(b < 0)
			addResolucao("\\(x_1+x_2=\\dfrac{-(" + b + ")}{" + a + "}=\\dfrac{" + (-b) + "}{" + a + "}=\\mathbf{" + resultado.toStringLatex() + "}\\)");
		else
			addResolucao("\\(x_1+x_2=\\dfrac{" + (-b) + "}{" + a + "}=\\mathbf{" + resultado.toStringLatex() + "}\\)");
	}
}
