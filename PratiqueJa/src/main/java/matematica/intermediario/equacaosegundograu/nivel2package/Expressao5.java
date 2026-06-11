package matematica.intermediario.equacaosegundograu.nivel2package;

import matematica.GeradorExercicio;
import matematica.ParCor;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int x1 = 1 + rand.nextInt(9);
		int x2 = 1 + rand.nextInt(9);
		if(rand.nextBoolean()) x2 *= -1;

		int soma = x1 + x2;
		int produto = x1 * x2;
		int b = -soma;
		int c = produto;

		String res = "\\(" + ParCor.formula("b=-a(x_1+x_2), \\quad a=1") + "\\)" + "\\(\\\\\\)";
		res += "\\(x_1+x_2=" + soma + "\\)" + "\\(\\\\\\)";
		res += "\\(b=-1\\cdot " + soma + "=\\mathbf{" + b + "}\\)";

		String enunciado = "";
		if(soma >= 0)
			enunciado = "x_1+x_2=" + soma;
		else
			enunciado = "x_1+x_2=\\left(" + soma + "\\right)";

		addParagrafo("Dada \\(x^2+bx+c=0\\), com \\(" + enunciado + "\\) e \\(x_1\\cdot x_2=" + produto + "\\), encontre \\(b\\)");
		gerarAlternativas("" + b);
		setResolucao(res);
	}
}
