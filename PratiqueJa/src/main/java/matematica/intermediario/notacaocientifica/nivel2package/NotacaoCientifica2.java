package matematica.intermediario.notacaocientifica.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Divisão em NC: (a×10^m)÷(b×10^n) = (a/b)×10^{m-n}, com a/b inteiro
public class NotacaoCientifica2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b, ratio;
		do
		{
			ratio = 2 + rand.nextInt(3); // 2..4
			b     = 2 + rand.nextInt(2); // 2..3
			a     = ratio * b;
		}
		while (a > 9);

		int m = 4 + rand.nextInt(4); // 4..7
		int n = 1 + rand.nextInt(m - 3); // 1..m-3, garante m-n ≥ 3
		int expResult = m - n;

		String correta = "\\(" + ratio + " \\times 10^{" + expResult + "}\\)";
		String e1     = "\\(" + ratio + " \\times 10^{" + (expResult + 1) + "}\\)";
		String e2     = "\\(" + ratio + " \\times 10^{" + (expResult - 1) + "}\\)";
		String e3     = "\\(" + (ratio + 1) + " \\times 10^{" + expResult + "}\\)";

		addParagrafo("Calcule, deixando o resultado em notação científica:");
		addParagrafo("\\(\\dfrac{" + a + " \\times 10^{" + m + "}}{" + b + " \\times 10^{" + n + "}}\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Dividir as mantissas e subtrair os expoentes:" +
			"\\(\\\\\\)" +
			"\\(\\dfrac{" + a + "}{" + b + "} = " + ratio + "\\)" +
			"\\(\\\\\\)" +
			"\\(\\dfrac{10^{" + m + "}}{10^{" + n + "}} = 10^{" + m + " - " + n + "} = 10^{" + expResult + "}\\)" +
			"\\(\\\\\\)" +
			"\\(= \\mathbf{" + ratio + " \\times 10^{" + expResult + "}}\\)"
		);
	}
}
