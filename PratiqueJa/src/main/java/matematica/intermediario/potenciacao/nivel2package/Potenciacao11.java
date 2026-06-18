package matematica.intermediario.potenciacao.nivel2package;

import matematica.GeradorExercicio;

public class Potenciacao11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] opcs = {2, 3, 4, 5};
		int a, b, p;
		long resultado;
		do
		{
			a = opcs[rand.nextInt(opcs.length)];
			b = opcs[rand.nextInt(opcs.length)];
			p = 2 + rand.nextInt(3); // 2..4
			resultado = (long) Math.pow((double) (a * b), p);
		}
		while (a == b || resultado > 50000);

		long an = (long) Math.pow(a, p);
		long bn = (long) Math.pow(b, p);

		addParagrafo("Calcule usando \\((a \\cdot b)^n = a^n \\cdot b^n\\):");
		addParagrafo("\\((" + a + " \\cdot " + b + ")^{" + p + "}\\)");
		gerarAlternativas("" + resultado);
		setResolucao(
			"\\((" + a + " \\cdot " + b + ")^{" + p + "}" +
			" = " + a + "^{" + p + "} \\cdot " + b + "^{" + p + "} =\\)" +
			"\\(\\\\\\)" +
			"\\(" + an + " \\cdot " + bn + " = \\mathbf{" + resultado +"}\\)");
	}
}
