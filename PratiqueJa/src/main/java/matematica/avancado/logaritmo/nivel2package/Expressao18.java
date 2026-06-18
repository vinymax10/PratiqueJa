package matematica.avancado.logaritmo.nivel2package;

import matematica.GeradorExercicio;

public class Expressao18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Equação tipo 1: log_b(x) = n → x = b^n
		int b = 2 + rand.nextInt(4); // 2, 3, 4, 5
		int n = 2 + rand.nextInt(4); // 2, 3, 4, 5
		int x = (int) Math.pow(b, n);

		addParagrafo("Resolva a equação logarítmica:");
		addParagrafo("\\(\\log_{" + b + "} x = " + n + "\\)");

		String res = "Convertendo para forma exponencial (\\(\\log_b x = n \\Rightarrow x = b^n\\)): \\(\\\\\\)";
		res += "\\(x = " + b + "^{" + n + "} = \\mathbf{" + x + "}\\)";

		gerarAlternativas("" + x);
		setResolucao(res);
	}
}
