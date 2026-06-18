package matematica.basico.numerosprimos.nivel2package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Número de divisores de N = p^a × q^b (fórmula τ) — segunda variação
public class Primo8 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p = LISTA_PRIMOS[rand.nextInt(4)];      // 2,3,5,7
		int a = 2 + rand.nextInt(3);                // 2-4
		int q = LISTA_PRIMOS[4 + rand.nextInt(3)];  // 11,13,17
		int b = 1 + rand.nextInt(2);                // 1-2

		int resultado = (a + 1) * (b + 1);
		String nLatex = p + "^{" + a + "} \\times " + q + (b > 1 ? "^{" + b + "}" : "");

		addParagrafo("Quantos divisores tem o número \\(N = " + nLatex + "\\)?");
		gerarAlternativasInteiras(resultado);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& \\tau(N) = (" + a + "+1)(" + b + "+1)\\\\" +
			"& = " + (a + 1) + " \\times " + (b + 1) + " = \\mathbf{" + resultado + "}" +
			"\\end{aligned}\\)"
		);
	}
}
