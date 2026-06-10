package matematica.intermediario.notacaocientifica.nivel3package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Crescimento exponencial: colônia dobra k vezes → N × 2^k, com ajuste de mantissa
public class NotacaoCientifica3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		// a=5, k∈{2,3,4}: 5×2^k ∈ {20,40,80} → mantissa ajustada {2,4,8}
		int k = 2 + rand.nextInt(3);      // 2..4
		int pot = (int) Math.pow(2, k);   // 4, 8 ou 16
		int produto = 5 * pot;            // 20, 40 ou 80
		int r = produto / 10;             // 2, 4 ou 8

		int m = 3 + rand.nextInt(4);      // 3..6
		int expResult = m + 1;            // +1 do reajuste (produto ≥ 10)

		String correta = "\\(" + r + " \\times 10^{" + expResult + "}\\)";
		String e1     = "\\(" + r + " \\times 10^{" + m + "}\\)";           // esqueceu o +1
		String e2     = "\\(" + r + " \\times 10^{" + (expResult + 1) + "}\\)";
		String e3     = "\\(" + produto + " \\times 10^{" + m + "}\\)";     // sem ajuste

		addParagrafo("Uma colônia de bactérias tem \\(5 \\times 10^{" + m + "}\\) bactérias.");
		addParagrafo("A população dobra a cada hora. Após \\(" + k + "\\) horas, quantas bactérias há?");
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		setResolucao(
			"Calcular o fator de crescimento:" +
			"\\(\\\\\\)" +
			"\\(2^{" + k + "} = " + pot + "\\)" +
			"\\(\\\\\\)" +
			"Multiplicar pelo número inicial:" +
			"\\(\\\\\\)" +
			"\\(5 \\times " + pot + " = " + produto + "\\)" +
			"\\(\\\\\\)" +
			"\\(" + produto + " \\times 10^{" + m + "}\\)" +
			"\\(\\\\\\)" +
			"Reajustar: \\(" + produto + " \\geq 10\\), escrever como \\(" + r + " \\times 10^1\\):" +
			"\\(\\\\\\)" +
			"\\(" + r + " \\times 10^1 \\times 10^{" + m + "} = \\mathbf{" + r + " \\times 10^{" + expResult + "}}\\)"
		);
	}
}
