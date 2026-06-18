package matematica.avancado.polinomios.nivel1package;

import matematica.GeradorExercicio;
import matematica.avancado.polinomios.Polinomio;

// Identify the degree of a polynomial (degree 3 or 4)
public class Expressao13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int degree = 3 + rand.nextInt(2);

		int a = 2 + rand.nextInt(4);
		int b = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));
		int c = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));
		int d = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));

		String poly;
		if (degree == 3)
		{
			poly = Polinomio.formatar(a, b, c, d);
		}
		else
		{
			int e = (rand.nextBoolean() ? 1 : -1) * (1 + rand.nextInt(4));
			poly = Polinomio.formatar(a, b, c, d, e);
		}

		String res = "O maior expoente com coeficiente não nulo é \\(" + degree + "\\). "
				+ "Portanto, o grau de \\(p(x)\\) é \\(\\mathbf{" + degree + "}\\).";

		addParagrafo("Qual o grau do polinômio \\(p(x)\\)?");
		addParagrafo("\\(p(x) = " + poly + "\\)");
		gerarAlternativas("" + degree);
		setResolucao(res);
	}
}
