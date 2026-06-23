package matematica.basico.numerosprimos.nivel2package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos divisores tem N = p^a × q^b? (fórmula dos divisores)
public class Primo4 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p = LISTA_PRIMOS[rand.nextInt(3)];      // 2, 3 ou 5
		int a = 1 + rand.nextInt(3);                // expoente 1-3
		int q = LISTA_PRIMOS[3 + rand.nextInt(4)];  // 7, 11, 13 ou 17
		int b = 1 + rand.nextInt(2);                // expoente 1-2

		int pa = 1; for(int i = 0; i < a; i++) pa *= p;
		int qb = 1; for(int i = 0; i < b; i++) qb *= q;
		int n = pa * qb;

		int resultado = (a + 1) * (b + 1);

		String nLatex = p + (a > 1 ? "^" + a : "") + " \\times " + q + (b > 1 ? "^" + b : "");

		addParagrafo("Dado que \\(N = " + nLatex + " = " + n + "\\), quantos divisores tem \\(N\\)?");
		gerarAlternativasInteiras(resultado);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& N = " + p + "^{" + a + "} \\times " + q + "^{" + b + "}\\\\" +
			"& \\tau(N) = (" + a + "+1) \\times (" + b + "+1)\\\\" +
			"& = " + (a + 1) + " \\times " + (b + 1) + " = \\mathbf{" + resultado + "}" +
			"\\end{aligned}\\)"
		);
	}
}
