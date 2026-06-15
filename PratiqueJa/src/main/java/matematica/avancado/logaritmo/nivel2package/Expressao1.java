package matematica.avancado.logaritmo.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int c = 2 + rand.nextInt(9);
		int b = 2 + rand.nextInt(10);
		int maxBase = (int) Math.min(Math.log(1000) / Math.log(c), 10);
		int x = 1 + rand.nextInt(maxBase + 1);

		if (rand.nextBoolean())
			construirSoma(c, b, x);
		else
			construirSubtracao(c, b, x);
	}

	private void construirSoma(int c, int b, int x)
	{
		// log_c(c^x / b) + log_c(b) = x
		Racional a = new Racional((int) Math.pow(c, x), b);
		a.fatoracao(2);
		String argA = a.denominador == 1 ? "" + a.numerador : "\\dfrac{" + a.numerador + "}{" + a.denominador + "}";
		String enunciado = "\\(\\log_{" + c + "}\\left(" + argA + "\\right) + \\log_{" + c + "} " + b + "\\)";

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo(enunciado);

		Racional produto = a.mult(new Racional(b));
		produto.fatoracao(2);

		String res = "Propriedade do produto: \\(\\log_b M + \\log_b N = \\log_b(M \\cdot N)\\\\";
		res += "\\log_{" + c + "}\\left(" + argA + " \\cdot " + b + "\\right) = \\log_{" + c + "} " + (long) produto.numerador + "\\\\";
		res += "\\log_{" + c + "} " + c + "^{" + x + "} = \\mathbf{" + x + "}\\)";

		gerarAlternativas("" + x);
		setResolucao(res);
	}

	private void construirSubtracao(int c, int b, int x)
	{
		// log_c(c^x * b) - log_c(b) = x
		long arg = (long) Math.pow(c, x) * b;
		String enunciado = "\\(\\log_{" + c + "} " + arg + " - \\log_{" + c + "} " + b + "\\)";

		addParagrafo("Calcule o valor da expressão:");
		addParagrafo(enunciado);

		long quociente = arg / b; // = c^x
		String res = "Propriedade do quociente: \\(\\log_b M - \\log_b N = \\log_b\\!\\left(\\dfrac{M}{N}\\right)\\\\";
		res += "\\log_{" + c + "}\\!\\left(\\dfrac{" + arg + "}{" + b + "}\\right) = \\log_{" + c + "} " + quociente + "\\\\";
		res += "\\log_{" + c + "} " + c + "^{" + x + "} = \\mathbf{" + x + "}\\)";

		gerarAlternativas("" + x);
		setResolucao(res);
	}
}
