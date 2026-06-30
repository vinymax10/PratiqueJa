package matematica.intermediario.notacaocientifica.nivel3package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Comparação: A é quantas vezes maior que B? — divisão em NC com contexto
public class NotacaoCientifica2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int ratio, b, a;
		do
		{
			ratio = 2 + rand.nextInt(3); // 2..4
			b     = 2 + rand.nextInt(2); // 2..3
			a     = ratio * b;
		}
		while (a > 9);

		int m    = 5 + rand.nextInt(4); // 5..8
		int diff = 2 + rand.nextInt(3); // 2..4
		int n    = m - diff;

		String correta = "\\(" + ratio + " \\times 10^{" + diff + "}\\)";
		String e1     = "\\(" + ratio + " \\times 10^{" + (diff + 1) + "}\\)";
		String e2     = "\\(" + ratio + " \\times 10^{" + (diff - 1) + "}\\)";
		// Quando ratio=2, b=2, a=4: a-b=2==ratio → e3==correta; usar a+b nesses casos
		int e3mant = (a - b == ratio) ? a + b : a - b;
		String e3     = "\\(" + e3mant + " \\times 10^{" + diff + "}\\)"; // subtração/adição em vez de divisão

		addParagrafo("A massa do planeta X é \\(" + a + " \\times 10^{" + m + "}\\) kg.");
		addParagrafo("A massa do planeta Y é \\(" + b + " \\times 10^{" + n + "}\\) kg.");
		addParagrafo("Quantas vezes a massa de X é maior que a de Y?");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Dividir as massas:");
		addResolucao("\\(\\dfrac{" + a + " \\times 10^{" + m + "}}{" + b + " \\times 10^{" + n + "}} =\\)");
		addResolucao("\\(\\dfrac{" + a + "}{" + b + "} \\times 10^{" + m + " - " + n + "} =\\)");
		addResolucao("\\(\\mathbf{" + ratio + " \\times 10^{" + diff + "}}\\)");
	}
}
