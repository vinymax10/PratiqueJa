package matematica.intermediario.funcaoafim.nivel1package;

import matematica.GeradorExercicio;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		if(rand.nextBoolean()) a *= -1;
		int xResult = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(8));
		int b = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(10));
		int k = a * xResult + b;

		String funcao = afimStr(a, b);
		addParagrafo("Sendo \\(f(x) = " + funcao + "\\), determine \\(x\\) tal que \\(f(x) = " + k + "\\)");

		int kmb = k - b; // = a * xResult

		String res = "Igualamos \\(f(x) = " + k + "\\): \\(\\\\\\)";
		res += "\\(" + funcao + " = " + k + "\\) \\(\\\\\\)";

		if(a == 1)
		{
			if(b > 0)
				res += "\\(x = " + k + " - " + b + " = \\mathbf{" + xResult + "}\\)";
			else
				res += "\\(x = " + k + " + " + Math.abs(b) + " = \\mathbf{" + xResult + "}\\)";
		}
		else if(a == -1)
		{
			if(b > 0)
				res += "\\(-x = " + k + " - " + b + " = " + kmb + "\\) \\(\\\\\\)";
			else
				res += "\\(-x = " + k + " + " + Math.abs(b) + " = " + kmb + "\\\\";
			res += "x = \\mathbf{" + xResult + "}\\)";
		}
		else
		{
			if(b > 0)
				res += "\\(" + a + "x = " + k + " - " + b + " = " + kmb + "\\) \\(\\\\\\)";
			else
				res += "\\(" + a + "x = " + k + " + " + Math.abs(b) + " = " + kmb + "\\) \\(\\\\\\)";
			int num = kmb, den = a;
			if(den < 0) { num = -num; den = -den; }
			if(num < 0)
				res += "\\(x = -\\dfrac{" + (-num) + "}{" + den + "} = \\mathbf{" + xResult + "}\\)";
			else
				res += "\\(x = \\dfrac{" + num + "}{" + den + "} = \\mathbf{" + xResult + "}\\)";
		}

		gerarAlternativas("" + xResult);
		setResolucao(res);
	}

	private static String afimStr(int a, int b)
	{
		String sa = a == 1 ? "" : a == -1 ? "-" : "" + a;
		String sb = b > 0 ? " + " + b : b < 0 ? " - " + Math.abs(b) : "";
		return sa + "x" + sb;
	}
}
