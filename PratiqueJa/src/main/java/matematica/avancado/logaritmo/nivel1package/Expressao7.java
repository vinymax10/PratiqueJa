package matematica.avancado.logaritmo.nivel1package;

import matematica.GeradorExercicio;

public class Expressao7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// log_b(b^n) = n — a potência é mostrada explicitamente
		int[] bases = {2, 3, 4, 5, 10};
		int b = bases[rand.nextInt(bases.length)];
		int n = 2 + rand.nextInt(5); // 2..6

		addParagrafo("Aplique a propriedade \\(\\log_b b^n = n\\):");
		addParagrafo("\\(\\log_{" + b + "} " + b + "^{" + n + "} = \\,?\\)");

		String res = "A potência e o logaritmo de mesma base se cancelam: \\(\\\\\\)";
		res += "\\(\\log_{" + b + "} " + b + "^{" + n + "} = " + n + "\\), pois \\(" + b + "^{" + n + "} = " + b + "^{" + n + "}\\) \\(\\\\\\)";
		res += "\\(\\log_{" + b + "} " + b + "^{" + n + "} = \\mathbf{" + n + "}\\)";

		gerarAlternativas("" + n);
		setResolucao(res);
	}
}
