package matematica.avancado.funcaomodular.nivel1package;

import matematica.GeradorExercicio;

public class Expressao15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Calcular f(val) = |val - h| + k
		int h   = rand.nextInt(7) - 3;  // -3..3
		int k   = rand.nextInt(4);       // 0..3
		int val = rand.nextInt(11) - 5;  // -5..5

		int inner  = val - h;
		int result = Math.abs(inner) + k;

		String modStr  = h == 0 ? "|x|" : (h > 0 ? "|x - " + h + "|" : "|x + " + (-h) + "|");
		String funcStr = k == 0 ? modStr : modStr + " + " + k;

		addParagrafo("Dada \\(f(x) = " + funcStr + "\\), calcule \\(f(" + val + ")\\).");

		String absInner = "" + Math.abs(inner);
		String res;
		if (k == 0)
		{
			res = "\\(f(" + val + ") = |" + inner + "|";
			if (inner < 0) res += " = -(" + inner + ")";
			res += " = \\mathbf{" + result + "}\\)";
		}
		else
		{
			res = "\\(f(" + val + ") = |" + inner + "| + " + k + " = " + absInner + " + " + k + " = \\mathbf{" + result + "}\\)";
		}

		gerarAlternativas("" + result);
		setResolucao(res);
	}
}
