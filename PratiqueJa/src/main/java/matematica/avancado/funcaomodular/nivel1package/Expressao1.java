package matematica.avancado.funcaomodular.nivel1package;

import matematica.GeradorExercicio;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Avaliação direta de |a - b|, onde o interior pode ser negativo ou positivo
		boolean interior_negativo = rand.nextBoolean();
		int a, b, inner, result;

		if (interior_negativo)
		{
			b      = 5 + rand.nextInt(10);  // b: 5..14
			a      = 1 + rand.nextInt(4);   // a: 1..4 (a < b)
			inner  = a - b;                 // negativo
			result = b - a;
		}
		else
		{
			a      = 5 + rand.nextInt(10);  // a: 5..14
			b      = 1 + rand.nextInt(4);   // b: 1..4 (b < a)
			inner  = a - b;                 // positivo
			result = inner;
		}

		addParagrafo("Calcule \\(|" + a + " - " + b + "|\\).");

		gerarAlternativas("" + result);

		if (inner < 0)
		{
			addResolucao("\\(" + a + " - " + b + " = " + inner + "\\) (negativo)");
			addResolucao("Como \\(" + inner + " < 0\\), pela definição: \\(|" + inner + "| = -(" + inner + ")\\)");
			addResolucao("\\(|" + a + " - " + b + "| = \\mathbf{" + result + "}\\)");
		}
		else
		{
			addResolucao("\\(" + a + " - " + b + " = " + inner + "\\) (não negativo)");
			addResolucao("\\(|" + inner + "| = \\mathbf{" + result + "}\\)");
		}
	}
}
