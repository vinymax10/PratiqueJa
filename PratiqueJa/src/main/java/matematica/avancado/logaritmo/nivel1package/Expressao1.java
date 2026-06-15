package matematica.avancado.logaritmo.nivel1package;

import matematica.GeradorExercicio;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int c = 2 + rand.nextInt(9);
		int maxBase = (int) Math.min(Math.log(1000) / Math.log(c), 10);
		int x = 1 + rand.nextInt(maxBase + 1);
		int a = (int) Math.pow(c, x);

		addParagrafo("Calcule o valor do logaritmo:");
		addParagrafo("\\(\\log_{" + c + "} " + a + " = \\,?\\)");

		String res = "Pela definição \\(\\log_b a = x \\iff b^x = a\\): \\(\\\\\\)";
		res += "\\(\\log_{" + c + "} " + a + " = x \\Rightarrow " + c + "^x = " + a + "\\\\";
		res += "" + c + "^x = " + c + "^{" + x + "}\\\\";
		res += "x = \\mathbf{" + x + "}\\)";

		gerarAlternativas("" + x);
		setResolucao(res);
	}
}
