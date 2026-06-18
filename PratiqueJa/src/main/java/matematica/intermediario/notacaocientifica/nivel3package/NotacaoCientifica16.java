package matematica.intermediario.notacaocientifica.nivel3package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Contexto astronômico: tempo = distância / velocidade (velocidade da luz = 3×10^8 m/s)
public class NotacaoCientifica16 extends GeradorExercicio
{
	// {descrição do objeto, expoente da distância em metros}
	private static final Object[][] ASTROS = {
		{"A Lua está a uma distância de",       "3", 8},
		{"Marte está a uma distância de",       "6", 10},
		{"Júpiter está a uma distância de",     "6", 11},
		{"a estrela Proxima Centauri está a",   "3", 16},
		{"a nebulosa de Órion está a",          "9", 18}
	};

	@Override
	protected void construir()
	{
		Object[] astro = ASTROS[rand.nextInt(ASTROS.length)];
		String desc = (String) astro[0];
		String d    = (String) astro[1]; // mantissa da distância (divisível por 3)
		int m       = (int) astro[2];    // expoente da distância

		int dInt = Integer.parseInt(d);
		int ratio = dInt / 3;  // d / 3 = mantissa do tempo
		int expT  = m - 8;     // expoente do tempo = m - expoente da velocidade da luz

		String correta = "\\(" + ratio + " \\times 10^{" + expT + "}\\) s";
		String e1     = "\\(" + ratio + " \\times 10^{" + (expT + 1) + "}\\) s";
		String e2     = "\\(" + ratio + " \\times 10^{" + (expT - 1) + "}\\) s";
		String e3     = "\\(" + (dInt * 3) + " \\times 10^{" + expT + "}\\) s"; // multiplicou em vez de dividir

		addParagrafo("A velocidade da luz é \\(3 \\times 10^8\\) m/s.");
		addParagrafo(desc + " \\(" + d + " \\times 10^{" + m + "}\\) m da Terra.");
		addParagrafo("Em quantos segundos a luz percorre essa distância?");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"\\(t = \\dfrac{d}{v}\\):" +
			"\\(\\\\\\)" +
			"\\(t = \\dfrac{" + d + " \\times 10^{" + m + "}}{3 \\times 10^8} =\\)" +
			"\\(\\\\\\)" +
			"\\(\\dfrac{" + d + "}{3} \\times 10^{" + m + " - 8} =\\)" +
			"\\(\\\\\\)" +
			"\\(\\mathbf{" + ratio + " \\times 10^{" + expT + "}}\\) s"
		);
	}
}
