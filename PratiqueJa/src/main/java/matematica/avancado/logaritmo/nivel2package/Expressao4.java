package matematica.avancado.logaritmo.nivel2package;

import matematica.GeradorExercicio;

public class Expressao4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// Equação tipo 2: log_b(x) + log_b(k) = n → log_b(kx) = n → x = b^n / k
		// Garantia de resultado inteiro: k = b^kExp, x = b^(n-kExp)
		int b    = rand.nextBoolean() ? 2 : 3;
		int kExp = 1 + rand.nextInt(2);          // k = b¹ ou b²
		int k    = (int) Math.pow(b, kExp);
		int n    = kExp + 2 + rand.nextInt(2);   // n > kExp → x = b^(n-kExp) ≥ b²
		int x    = (int) Math.pow(b, n - kExp);

		addParagrafo("Resolva a equação logarítmica:");
		addParagrafo("\\(\\log_{" + b + "} x + \\log_{" + b + "} " + k + " = " + n + "\\)");

		String res = "Aplicando a propriedade do produto: \\(\\\\\\)";
		res += "\\(\\log_{" + b + "}(x \\cdot " + k + ") = " + n + "\\) \\(\\\\\\)";
		res += "Convertendo para forma exponencial: \\(\\\\\\)";
		res += "\\(" + k + "x = " + b + "^{" + n + "} = " + (int) Math.pow(b, n) + "\\\\";
		res += "x = \\dfrac{" + (int) Math.pow(b, n) + "}{" + k + "} = \\mathbf{" + x + "}\\)";

		gerarAlternativas("" + x);
		setResolucao(res);
	}
}
