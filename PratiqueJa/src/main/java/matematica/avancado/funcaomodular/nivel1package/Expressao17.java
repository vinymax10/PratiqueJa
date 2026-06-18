package matematica.avancado.funcaomodular.nivel1package;

import matematica.GeradorExercicio;

public class Expressao17 extends GeradorExercicio
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

		String res;
		if (inner < 0)
		{
			res  = "\\(" + a + " - " + b + " = " + inner + "\\) (negativo) \\(\\\\\\)";
			res += "Como \\(" + inner + " < 0\\), pela definição: \\(|" + inner + "| = -(" + inner + ")\\) \\(\\\\\\)";
			res += "\\(|" + a + " - " + b + "| = \\mathbf{" + result + "}\\)";
		}
		else
		{
			res  = "\\(" + a + " - " + b + " = " + inner + "\\) (não negativo) \\(\\\\\\)";
			res += "\\(|" + inner + "| = \\mathbf{" + result + "}\\)";
		}

		gerarAlternativas("" + result);
		setResolucao(res);
	}
}
