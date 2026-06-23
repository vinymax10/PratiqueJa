package matematica.avancado.funcaoquadratica.nivel3package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(x) = a(x - x1)(x - x2) → expandir e encontrar c = a·x1·x2
		int x1 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5));
		int x2 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5));
		while(x1 == x2) x2 = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(5));

		int a = (1 + rand.nextInt(3)) * (rand.nextBoolean() ? 1 : -1);
		int b = -a * (x1 + x2);
		int c = a * x1 * x2;

		// Exibir forma fatorada e forma padrão com c oculto
		String x1Disp = x1 < 0 ? "(x + " + (-x1) + ")" : "(x - " + x1 + ")";
		String x2Disp = x2 < 0 ? "(x + " + (-x2) + ")" : "(x - " + x2 + ")";
		String aPref  = a == 1 ? "" : a == -1 ? "-" : "" + a;
		String fatorada = aPref + x1Disp + x2Disp;

		String funcaoPadrao = Auxiliar.getNumber(a, "x^2", true)
			+ Auxiliar.getNumber(b, "x", false) + "+c";

		addParagrafo("A função \\(f(x) = " + fatorada + "\\) está na forma fatorada. "
			+ "Determine o coeficiente \\(c\\) na forma padrão \\(" + funcaoPadrao + "\\).");

		int prod = x1 * x2;
		String x1s = "" + x1;
		String x2s = "" + x2;

		gerarAlternativas("" + c);
		addResolucao("Expandindo \\(f(x) = " + fatorada + "\\):");
		addResolucao("\\(f(x) = " + aPref + "[x^2 - (" + x1 + " + " + x2 + ")x + (" + x1 + ") \\cdot (" + x2 + ")]\\)");
		addResolucao("\\(f(x) = " + aPref + "[x^2" + Auxiliar.getNumber(-(x1 + x2), "x", false)
			+ Auxiliar.getNumber(prod, "", false) + "]\\)");
		addResolucao("Portanto \\(c = a \\cdot x_1 \\cdot x_2 = " + a + " \\cdot (" + x1s + ") \\cdot (" + x2s + ") = "
			+ (a * prod) + "\\).");
		addResolucao("\\(c = \\mathbf{" + c + "}\\)");
	}
}
