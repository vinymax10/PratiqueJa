package matematica.basico.numerosprimos.nivel3package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos divisores tem N²? onde N = p^a × q^b (fórmula τ aplicada ao quadrado)
public class Primo1 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p, q, a, b, n;
		do
		{
			p = LISTA_PRIMOS[rand.nextInt(3)];     // 2, 3 ou 5
			q = LISTA_PRIMOS[3 + rand.nextInt(4)]; // 7, 11, 13 ou 17
			a = 1 + rand.nextInt(3);               // 1-3
			b = 1 + rand.nextInt(2);               // 1-2
			int pa = 1; for(int i = 0; i < a; i++) pa *= p;
			int qb = 1; for(int i = 0; i < b; i++) qb *= q;
			n = pa * qb;
		}
		while(n > 200);

		int resultado = (2 * a + 1) * (2 * b + 1);

		String nLatex  = p + "^{" + a + "} \\times " + q + "^{" + b + "}";
		String n2Latex = p + "^{" + (2 * a) + "} \\times " + q + "^{" + (2 * b) + "}";

		addParagrafo("Dado \\(N = " + nLatex + " = " + n + "\\), quantos divisores tem \\(N^2\\)?");
		gerarAlternativasInteiras(resultado);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& N^2 = " + n2Latex + "\\\\" +
			"& \\tau(N^2) = (" + (2 * a) + "{+}1)(" + (2 * b) + "{+}1)\\\\" +
			"& = " + (2 * a + 1) + " \\times " + (2 * b + 1) + " = \\mathbf{" + resultado + "}" +
			"\\end{aligned}\\)"
		);
	}
}
