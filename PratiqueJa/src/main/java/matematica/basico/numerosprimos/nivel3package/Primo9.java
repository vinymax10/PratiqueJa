package matematica.basico.numerosprimos.nivel3package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos divisores tem N = 2^a × 3^b × 7^c? (três fatores, segunda variação)
public class Primo9 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p = 2, q = 3, r = 7;
		int a = 1 + rand.nextInt(3); // 1-3
		int b = 1 + rand.nextInt(2); // 1-2
		int c = 1 + rand.nextInt(2); // 1-2

		int pa = 1; for(int i = 0; i < a; i++) pa *= p;
		int qb = 1; for(int i = 0; i < b; i++) qb *= q;
		int rc = 1; for(int i = 0; i < c; i++) rc *= r;
		int n = pa * qb * rc;

		int resultado = (a + 1) * (b + 1) * (c + 1);
		String nLatex = p + "^{" + a + "} \\times " + q + "^{" + b + "} \\times " + r + "^{" + c + "}";

		addParagrafo("Dado que \\(N = " + nLatex + " = " + n + "\\), quantos divisores tem \\(N\\)?");
		gerarAlternativasInteiras(resultado);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& \\tau(N) = (" + a + "+1)(" + b + "+1)(" + c + "+1)\\\\" +
			"& = " + (a + 1) + " \\times " + (b + 1) + " \\times " + (c + 1) + " = \\mathbf{" + resultado + "}" +
			"\\end{aligned}\\)"
		);
	}
}
