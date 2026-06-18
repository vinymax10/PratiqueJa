package matematica.intermediario.funcaoafim.nivel1package;

import matematica.GeradorExercicio;

public class Expressao14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		if(rand.nextBoolean()) a *= -1;
		int x0 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(8));
		int b = -a * x0;

		String funcao = afimStr(a, b);
		addParagrafo("Calcule o zero da função \\(f(x) = " + funcao + "\\)");

		int menosB = -b; // = a * x0

		String res = "O zero é o valor \\(x_0\\) tal que \\(f(x_0) = 0\\): \\(\\\\\\)";
		res += "\\(" + funcao + " = 0\\) \\(\\\\\\)";

		if(a == 1)
		{
			res += "\\(x = \\mathbf{" + x0 + "}\\)";
		}
		else if(a == -1)
		{
			res += "\\(-x = " + menosB + "\\\\";
			res += "x = \\mathbf{" + x0 + "}\\)";
		}
		else
		{
			res += "\\(" + a + "x = " + menosB + "\\) \\(\\\\\\)";
			int num = menosB, den = a;
			if(den < 0) { num = -num; den = -den; }
			if(num < 0)
				res += "\\(x = -\\dfrac{" + (-num) + "}{" + den + "} = \\mathbf{" + x0 + "}\\)";
			else
				res += "\\(x = \\dfrac{" + num + "}{" + den + "} = \\mathbf{" + x0 + "}\\)";
		}

		gerarAlternativas("" + x0);
		setResolucao(res);
	}

	private static String afimStr(int a, int b)
	{
		String sa = a == 1 ? "" : a == -1 ? "-" : "" + a;
		String sb = b > 0 ? " + " + b : b < 0 ? " - " + Math.abs(b) : "";
		return sa + "x" + sb;
	}
}
