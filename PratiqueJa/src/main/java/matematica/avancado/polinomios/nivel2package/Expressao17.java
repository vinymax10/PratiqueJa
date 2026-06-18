package matematica.avancado.polinomios.nivel2package;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Factor out common factor: k·(ax² + bx + 1)
public class Expressao17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int k = 2 + rand.nextInt(3);
		int a = 1 + rand.nextInt(4);
		int b = 1 + rand.nextInt(4);

		int coef2 = k * a;
		int coef1 = k * b;
		int coef0 = k;

		String poly = Polinomio.termo(coef2, 2, true)
				+ Polinomio.termo(coef1, 1, false)
				+ Auxiliar.getNumber(coef0, "", false);

		String inner = Polinomio.termo(a, 2, true) + Polinomio.termo(b, 1, false)
				+ Auxiliar.getNumber(1, "", false);
		String correct = k + "(" + inner + ")";

		// d1: wrong factor (k+1)
		String d1 = (k + 1) + "(" + inner + ")";
		// d2: missing constant inside
		String d2 = k + "(" + Polinomio.termo(a, 2, true) + Polinomio.termo(b, 1, false) + ")";
		// d3: wrong inner coefficient
		String innerWrong = Polinomio.termo(a + 1, 2, true) + Polinomio.termo(b, 1, false)
				+ Auxiliar.getNumber(1, "", false);
		String d3 = k + "(" + innerWrong + ")";

		List<String> distratores = new ArrayList<>();
		distratores.add("\\(" + d1 + "\\)");
		distratores.add("\\(" + d2 + "\\)");
		distratores.add("\\(" + d3 + "\\)");

		String res = "O MDC dos coeficientes " + coef2 + ", " + coef1 + " e " + coef0
				+ " é \\(" + k + "\\).\\(\\\\\\)";
		res += "Colocando \\(" + k + "\\) em evidência:\\(\\\\\\)";
		res += "\\(" + poly + " = " + k + "\\left(" + inner + "\\right)\\)\\(\\\\\\)";
		res += "\\(\\mathbf{" + correct + "}\\)";

		addParagrafo("Fatore o polinômio colocando o fator comum em evidência.");
		addParagrafo("\\(" + poly + "\\)");
		embaralharEAdicionarAlternativas("\\(" + correct + "\\)", distratores);
		setResolucao(res);
	}
}
