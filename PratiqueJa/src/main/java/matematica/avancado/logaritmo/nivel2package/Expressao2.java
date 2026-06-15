package matematica.avancado.logaritmo.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// log_{p^m}(p^n) = n/m — propriedade da base potência
		int p = rand.nextBoolean() ? 2 : 3;
		int m = 2 + rand.nextInt(3); // 2, 3, 4
		int n = 2 + rand.nextInt(5); // 2..6
		while (n == m) n = 2 + rand.nextInt(5);

		int leftBase = (int) Math.pow(p, m);
		int rightArg = (int) Math.pow(p, n);
		Racional resultado = new Racional(n, m);
		resultado.fatoracao(2);

		addParagrafo("Calcule o valor do logaritmo:");
		addParagrafo("\\(\\log_{" + leftBase + "} " + rightArg + " = \\,?\\)");

		String res = "Reconhecemos \\(" + leftBase + " = " + p + "^{" + m + "}\\) e \\(" + rightArg + " = " + p + "^{" + n + "}\\). \\(\\\\\\)";
		res += "Pela propriedade \\(\\log_{b^c} a = \\dfrac{1}{c} \\cdot \\log_b a\\): \\(\\\\\\)";
		res += "\\(\\log_{" + p + "^{" + m + "}} " + p + "^{" + n + "} = \\dfrac{1}{" + m + "} \\cdot \\log_{" + p + "} " + p + "^{" + n + "} = \\dfrac{1}{" + m + "} \\cdot " + n + "\\\\";
		res += "= \\dfrac{" + n + "}{" + m + "} = \\mathbf{" + resultado.toStringLatex() + "}\\)";

		gerarAlternativas(resultado);
		setResolucao(res);
	}
}
