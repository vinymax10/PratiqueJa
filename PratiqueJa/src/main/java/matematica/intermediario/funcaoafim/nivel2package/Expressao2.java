package matematica.intermediario.funcaoafim.nivel2package;

import matematica.GeradorExercicio;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(5);
		if(rand.nextBoolean()) a *= -1;
		int v = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(6));
		int b = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(10));
		int w = a * v + b;

		String nomeA = a == 1 ? "" : a == -1 ? "-" : "" + a;
		addParagrafo("A função afim \\(f(x) = " + nomeA + "x + b\\) passa pelo ponto "
			+ "\\((" + v + ",\\ " + w + ")\\). Calcule o coeficiente linear \\(b\\).");

		int av = a * v;
		String vDisp = v < 0 ? "\\left(" + v + "\\right)" : "" + v;

		gerarAlternativas("" + b);

		addResolucao("Se \\((" + v + ",\\ " + w + ")\\) pertence ao gráfico, então "
			+ "\\(f(" + v + ") = " + w + "\\):");

		if(a == 1)
			addResolucao("\\(" + vDisp + " + b = " + w + "\\)");
		else if(a == -1)
			addResolucao("\\(-" + vDisp + " + b = " + w + "\\)");
		else
			addResolucao("\\(" + a + " \\cdot " + vDisp + " + b = " + w + "\\)");

		addResolucao("\\(" + av + " + b = " + w + "\\)");

		if(av >= 0)
			addResolucao("\\(b = " + w + " - " + av + " = \\mathbf{" + b + "}\\)");
		else
			addResolucao("\\(b = " + w + " + " + Math.abs(av) + " = \\mathbf{" + b + "}\\)");
	}
}
