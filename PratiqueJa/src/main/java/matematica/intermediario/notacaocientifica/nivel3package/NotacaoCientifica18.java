package matematica.intermediario.notacaocientifica.nivel3package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Operação encadeada: (a×10^m × b×10^n) ÷ (c×10^p) em contexto de Física/Ciências
public class NotacaoCientifica18 extends GeradorExercicio
{
	// {a, b, c, r=a*b/c}: r é inteiro < 10
	private static final int[][] TRIPLES = {
		{4, 3, 2, 6}, {6, 3, 2, 9}, {2, 6, 3, 4},
		{3, 4, 2, 6}, {2, 9, 3, 6}, {4, 2, 1, 8},
		{3, 3, 1, 9}, {6, 2, 3, 4}, {8, 3, 4, 6}
	};

	private static final String[] CONTEXTOS = {
		"A energia cinética de um sistema é dada por \\(E = \\dfrac{m \\times v^2}{2}\\). " +
			"Com \\(m = %d \\times 10^{%d}\\) kg e \\(v^2 = %d \\times 10^{%d}\\) m²/s², " +
			"e \\(2 = %d \\times 10^0\\), qual é a energia?",
		"A densidade é \\(\\rho = \\dfrac{m}{V}\\). " +
			"Com \\(m = %d \\times 10^{%d}\\) kg e \\(V = \\dfrac{1}{%d \\times 10^{%d}}\\)... " +
			"Calcule \\(\\dfrac{%d \\times 10^{%d} \\times %d \\times 10^{%d}}{%d \\times 10^0}\\)."
	};

	@Override
	protected void construir()
	{
		int[] t = TRIPLES[rand.nextInt(TRIPLES.length)];
		int a = t[0], b = t[1], c = t[2], r = t[3];

		int m = 3 + rand.nextInt(4); // 3..6
		int n = 2 + rand.nextInt(4); // 2..5
		int p = 1 + rand.nextInt(3); // 1..3
		int expResult = m + n - p;

		String correta = "\\(" + r + " \\times 10^{" + expResult + "}\\)";
		String e1     = "\\(" + r + " \\times 10^{" + (expResult + 1) + "}\\)";
		String e2     = "\\(" + r + " \\times 10^{" + (expResult - 1) + "}\\)";
		String e3     = "\\(" + (a * b) + " \\times 10^{" + (m + n) + "}\\)"; // esqueceu de dividir

		addParagrafo("Calcule, em notação científica:");
		addParagrafo("\\(\\dfrac{" + a + " \\times 10^{" + m + "} \\;\\times\\; " + b + " \\times 10^{" + n + "}}{" + c + " \\times 10^{" + p + "}}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Separar a operação com as mantissas e com as potências:" +
			"\\(\\\\\\)" +
			"\\(\\dfrac{" + a + " \\times " + b + "}{" + c + "} \\times \\dfrac{10^{" + m + "} \\times 10^{" + n + "}}{10^{" + p + "}} =\\)" +
			"\\(\\\\\\)" +
			"\\(" + r + " \\times 10^{" + m + " + " + n + " - " + p + "} = \\mathbf{" + r + " \\times 10^{" + expResult + "}} \\)"
		);
	}
}
