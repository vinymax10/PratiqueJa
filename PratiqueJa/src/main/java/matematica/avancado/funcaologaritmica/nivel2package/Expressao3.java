package matematica.avancado.funcaologaritmica.nivel2package;

import matematica.GeradorExercicio;

public class Expressao3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Equação: log_a(x) = n → x = a^n
		int[] bases = {2, 3, 4, 5};
		int a = bases[rand.nextInt(bases.length)];
		int n = 1 + rand.nextInt(3); // 1, 2, 3
		int x = (int) Math.pow(a, n);

		addParagrafo("Resolva a equação: \\(\\log_{" + a + "}(x) = " + n + "\\)");

		String res = "Converter para forma exponencial: \\(\\\\\\)";
		res += "\\(\\log_{" + a + "}(x) = " + n + " \\Leftrightarrow x = " + a + "^{" + n + "}\\\\";
		res += "x = \\mathbf{" + x + "}\\)";

		gerarAlternativas("" + x);
		setResolucao(res);
	}
}
