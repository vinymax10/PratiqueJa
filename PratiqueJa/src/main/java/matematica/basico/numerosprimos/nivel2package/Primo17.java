package matematica.basico.numerosprimos.nivel2package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Número de divisores de N = p^a × q^b com expoentes maiores
public class Primo17 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p = LISTA_PRIMOS[rand.nextInt(2)];      // 2 ou 3
		int a = 2 + rand.nextInt(4);                // 2-5
		int q = LISTA_PRIMOS[2 + rand.nextInt(3)];  // 5,7,11
		int b = 2 + rand.nextInt(2);                // 2-3

		int resultado = (a + 1) * (b + 1);
		String nLatex = p + "^{" + a + "} \\times " + q + "^{" + b + "}";

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
