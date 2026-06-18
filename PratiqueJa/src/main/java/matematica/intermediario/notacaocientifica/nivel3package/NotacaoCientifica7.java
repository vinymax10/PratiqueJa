package matematica.intermediario.notacaocientifica.nivel3package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Contexto físico: distância = velocidade × tempo, com ajuste de mantissa
public class NotacaoCientifica7 extends GeradorExercicio
{
	// pares (v, t) com v*t múltiplo de 10 e v*t/10 dígito único
	private static final int[][] PARES = {
		{2, 5}, {5, 2}, {4, 5}, {5, 4}, {6, 5}, {5, 6}, {8, 5}, {5, 8}
	};

	private static final String[] CONTEXTOS = {
		"Uma partícula se move a \\(%d \\times 10^{%d}\\) m/s. Em \\(%d \\times 10^{%d}\\) segundos, qual é a distância percorrida?",
		"Um sinal de rádio viaja a \\(%d \\times 10^{%d}\\) m/s. Em \\(%d \\times 10^{%d}\\) segundos, qual é a distância que percorre?",
		"Um foguete se desloca a \\(%d \\times 10^{%d}\\) m/s. Em \\(%d \\times 10^{%d}\\) segundos, qual é a distância percorrida?"
	};

	@Override
	protected void construir()
	{
		int[] par = PARES[rand.nextInt(PARES.length)];
		int v = par[0], t = par[1];
		int prod = v * t;      // 10, 20, 30 ou 40
		int r    = prod / 10;  // mantissa ajustada: 1..4

		int m = 3 + rand.nextInt(4); // 3..6
		int n = 2 + rand.nextInt(3); // 2..4
		int expResult = m + n + 1;   // +1 pelo reajuste

		String contexto = CONTEXTOS[rand.nextInt(CONTEXTOS.length)];
		addParagrafo(String.format(contexto, v, m, t, n));

		String correta = "\\(" + r + " \\times 10^{" + expResult + "}\\) m";
		String e1     = "\\(" + r + " \\times 10^{" + (expResult - 1) + "}\\) m"; // esqueceu +1 do ajuste
		String e2     = "\\(" + r + " \\times 10^{" + (expResult + 1) + "}\\) m";
		String e3     = "\\(" + prod + " \\times 10^{" + (m + n) + "}\\) m";      // sem ajuste de mantissa

		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"\\(d = v \\times t\\):" +
			"\\(\\\\\\)" +
			"\\(" + v + " \\times " + t + " = " + prod + "\\)" +
			"\\(\\\\\\)" +
			"\\(" + prod + " \\times 10^{" + m + " + " + n + "} = " + prod + " \\times 10^{" + (m + n) + "}\\)" +
			"\\(\\\\\\)" +
			"Reajustar: \\(" + prod + " \\geq 10\\), escrever como \\(" + r + " \\times 10^1\\):" +
			"\\(\\\\\\)" +
			"\\(" + r + " \\times 10^1 \\times 10^{" + (m + n) + "} = \\mathbf{" + r + " \\times 10^{" + expResult + "}}\\) m"
		);
	}
}
