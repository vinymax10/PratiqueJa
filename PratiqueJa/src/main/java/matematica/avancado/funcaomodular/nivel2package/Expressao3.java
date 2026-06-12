package matematica.avancado.funcaomodular.nivel2package;

import matematica.GeradorExercicio;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// |ax + b| = 0  →  solução única x = -b/a
		// Construção reversa: escolher x0 e derivar b = -a*x0
		int a  = 1 + rand.nextInt(3);   // 1, 2 ou 3
		int x0 = rand.nextInt(9) - 4;   // -4..4
		int b  = -a * x0;

		String aStr = a == 1 ? "" : "" + a;
		String bStr = b == 0 ? "" : (b > 0 ? " + " + b : " - " + (-b));
		String inner = aStr + "x" + bStr;

		addParagrafo("Determine a solução de \\(|" + inner + "| = 0\\).");

		String res = "\\(|f(x)| = 0 \\Leftrightarrow f(x) = 0\\): \\(\\\\\\)";
		res += "\\(" + inner + " = 0\\)";
		if (a > 1 || b != 0)
		{
			res += " \\(\\\\\\)";
			if (a > 1 && b != 0)
				res += "\\(" + a + "x = " + (a * x0) + " \\Rightarrow x = \\mathbf{" + x0 + "}\\)";
			else if (b != 0)
				res += "\\(x = \\mathbf{" + x0 + "}\\)";
			else
				res += "\\(x = \\mathbf{" + x0 + "}\\)";
		}
		else
		{
			res += " \\(\\Rightarrow x = \\mathbf{" + x0 + "}\\)";
		}

		gerarAlternativas("" + x0);
		setResolucao(res);
	}
}
