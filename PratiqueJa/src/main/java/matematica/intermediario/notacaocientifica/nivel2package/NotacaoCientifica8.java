package matematica.intermediario.notacaocientifica.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Potência de NC: (a × 10^n)² = a² × 10^{2n}, sem ajuste de mantissa
public class NotacaoCientifica8 extends GeradorExercicio
{
	// bases cujo quadrado é dígito único (< 10): 1²=1, 2²=4, 3²=9
	private static final int[] BASES = {1, 2, 3};

	@Override
	protected void construir()
	{
		int a   = BASES[rand.nextInt(BASES.length)];
		int aSq = a * a;
		int n   = 2 + rand.nextInt(4); // 2..5

		String correta = "\\(" + aSq + " \\times 10^{" + (2 * n) + "}\\)";
		String e1     = "\\(" + aSq + " \\times 10^{" + n + "}\\)";       // não dobrou o expoente
		String e2     = "\\(" + (2 * a) + " \\times 10^{" + (2 * n) + "}\\)"; // multiplicou por 2 em vez de elevar ao quadrado
		String e3     = "\\(" + aSq + " \\times 10^{" + (2 * n + 1) + "}\\)"; // expoente a mais

		addParagrafo("Calcule, deixando o resultado em notação científica:");
		addParagrafo("\\((" + a + " \\times 10^{" + n + "})^2\\)");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Elevar ao quadrado a mantissa e o fator de potência de 10:");
		addResolucao("\\(" + a + "^2 = " + aSq + "\\)");
		addResolucao("\\((10^{" + n + "})^2 = 10^{" + n + " \\times 2} = 10^{" + (2 * n) + "}\\)");
		addResolucao("\\(\\mathbf{" + aSq + " \\times 10^{" + (2 * n) + "}}\\)");
	}
}
