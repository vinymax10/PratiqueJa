package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.ParCor;
import matematica.Racional;

public class Expressao18 extends GeradorExercicio
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

		Racional resultado = new Racional(c, a);
		resultado.fatoracao(2);

		String res = "\\(" + ParCor.formula("x_1\\cdot x_2=\\dfrac{c}{a}") + "\\)" + "\\(\\\\\\)";
		res += "\\(a=" + a + ", \\quad c=" + c + "\\)" + "\\(\\\\\\)";
		res += "\\(x_1\\cdot x_2=\\dfrac{" + c + "}{" + a + "}=\\mathbf{" + resultado.toStringLatex() + "}\\)";

		addParagrafo("Encontre o produto das raízes");
		addParagrafo("\\(" + eq + "\\)");
		gerarAlternativas(resultado);
		setResolucao(res);
	}
}
