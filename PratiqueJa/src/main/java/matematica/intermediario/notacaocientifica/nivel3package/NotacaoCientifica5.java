package matematica.intermediario.notacaocientifica.nivel3package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Adição com ajuste de mantissa em contexto: soma de dois números com mesmo expoente dá a+b ≥ 10
public class NotacaoCientifica5 extends GeradorExercicio
{
	private static final String[] CONTEXTOS = {
		"Duas galáxias têm, respectivamente, \\(%d \\times 10^{%d}\\) e \\(%d \\times 10^{%d}\\) estrelas. " +
			"Juntas, quantas estrelas possuem?",
		"Um país exportou \\(%d \\times 10^{%d}\\) kg de produto A e \\(%d \\times 10^{%d}\\) kg de produto B. " +
			"Qual o total exportado?",
		"Dois reservatórios armazenam \\(%d \\times 10^{%d}\\) L e \\(%d \\times 10^{%d}\\) L. " +
			"Qual a capacidade total?"
	};

	@Override
	protected void construir()
	{
		int a, b;
		do
		{
			a = 4 + rand.nextInt(6); // 4..9
			b = 4 + rand.nextInt(6); // 4..9
		}
		while (a + b < 10 || a + b > 18); // garante a+b ≥ 10 e mantissa ajustada ≤ 1,8

		int m = 8 + rand.nextInt(6); // 8..13

		// ajuste: a+b = 1X → mantissa = 1,X
		int soma        = a + b;
		int resultDec   = soma - 10; // primeiro decimal após 1
		String mantissa = "1{,}" + resultDec;
		int expResult   = m + 1;     // +1 do ajuste

		String correta = "\\(" + mantissa + " \\times 10^{" + expResult + "}\\)";
		String e1     = "\\(" + soma + " \\times 10^{" + m + "}\\)";          // sem ajuste
		String e2     = "\\(" + mantissa + " \\times 10^{" + m + "}\\)";      // expoente não incrementado
		String e3     = "\\(" + mantissa + " \\times 10^{" + (expResult + 1) + "}\\)"; // expoente a mais

		String ctx = CONTEXTOS[rand.nextInt(CONTEXTOS.length)];
		addParagrafo(String.format(ctx, a, m, b, m));
		embaralharEAdicionarAlternativas(correta, Arrays.asList(e1, e2, e3));
		addResolucao("Somar as mantissas (mesmo expoente):");
		addResolucao("\\(" + a + " + " + b + " = " + soma + "\\)");
		addResolucao("\\(" + soma + " \\times 10^{" + m + "}\\)");
		addResolucao("Reajustar: \\(" + soma + " \\geq 10\\), escrever como \\(" + mantissa + " \\times 10^1\\):");
		addResolucao("\\(" + mantissa + " \\times 10^1 \\times 10^{" + m + "} = \\mathbf{" + mantissa + " \\times 10^{" + expResult + "}}\\)");
	}
}
