package matematica.intermediario.potenciacao.nivel3package;

import java.util.Arrays;

import matematica.GeradorExercicio;

public class Potenciacao8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean multiplicacao = rand.nextBoolean();
		int a, b, p, q;
		do
		{
			a = 2 + rand.nextInt(8); // 2..9
			b = 2 + rand.nextInt(8); // 2..9
			p = 2 + rand.nextInt(5); // 2..6
			q = 2 + rand.nextInt(5); // 2..6
		}
		while (multiplicacao ? (a * b >= 10) : (a % b != 0));

		String enunciado, correta, e1, e2, e3, passoTexto, passoMath1, passoMath2;

		if (multiplicacao)
		{
			int mantissa = a * b;
			int expoente = p + q;
			enunciado =
				"\\((" + a + " \\times 10^{" + p + "}) \\times (" + b + " \\times 10^{" + q + "})\\)";
			correta   = "\\(" + mantissa + " \\times 10^{" + expoente + "}\\)";
			e1        = "\\(" + mantissa + " \\times 10^{" + (expoente + 1) + "}\\)";
			e2        = "\\(" + mantissa + " \\times 10^{" + (expoente - 1) + "}\\)";
			e3        = "\\(" + (mantissa + 1) + " \\times 10^{" + expoente + "}\\)";
			passoTexto = "Agrupar mantissas e potências de 10:";
			passoMath1 = "\\((" + a + " \\times " + b + ") \\times 10^{" + p + "+" + q + "} =\\)";
			passoMath2 = "\\(\\mathbf{" + mantissa + " \\times 10^{" + expoente + "}}\\)";
		}
		else
		{
			int mantissa = a / b;
			int expoente = p - q;
			enunciado =
				"\\(\\dfrac{" + a + " \\times 10^{" + p + "}}{" + b + " \\times 10^{" + q + "}}\\)";
			correta   = "\\(" + mantissa + " \\times 10^{" + expoente + "}\\)";
			e1        = "\\(" + mantissa + " \\times 10^{" + (expoente + 1) + "}\\)";
			e2        = "\\(" + mantissa + " \\times 10^{" + (expoente - 1) + "}\\)";
			e3        = "\\(" + (mantissa + 1) + " \\times 10^{" + expoente + "}\\)";
			passoTexto = "Dividir mantissas e subtrair expoentes:";
			passoMath1 = "\\(\\dfrac{" + a + "}{" + b + "} \\times 10^{" + p + "-" + q + "} =\\)";
			passoMath2 = "\\(\\mathbf{" + mantissa + " \\times 10^{" + expoente + "}}\\)";
		}

		addParagrafo("Calcule e expresse em notação científica:");
		addParagrafo(enunciado);
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao(passoTexto);
		addResolucao(passoMath1);
		addResolucao(passoMath2);
	}
}
