package matematica.avancado.funcaologaritmica.nivel2package;

import matematica.GeradorExercicio;
import matematica.Racional;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// f(1/a^n) = log_a(a^(-n)) = -n  →  resultado negativo
		int a = rand.nextBoolean() ? 2 : 3;
		int n = 1 + rand.nextInt(3); // 1, 2, 3
		int arg = (int) Math.pow(a, n); // a^n → argumento exibido como 1/arg
		int resultado = -n;

		String argStr = "\\dfrac{1}{" + arg + "}";
		addParagrafo("Dada \\(f(x) = \\log_{" + a + "}(x)\\), calcule "
			+ "\\(f\\!\\left(\\dfrac{1}{" + arg + "}\\right)\\).");

		String res = "Reescrever \\(" + argStr + "\\) como potência de \\(" + a + "\\): \\(\\\\\\)";
		res += "\\(\\dfrac{1}{" + arg + "} = \\dfrac{1}{" + a + "^{" + n + "}} = " + a + "^{-" + n + "}\\\\";
		res += "f\\!\\left(" + argStr + "\\right) = \\log_{" + a + "}(" + a + "^{-" + n + "}) = \\mathbf{-" + n + "}\\)";

		gerarAlternativas(new Racional(resultado, 1));
		setResolucao(res);
	}
}
