package matematica.basico.numerosprimos.nivel3package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos divisores tem N³? onde N = p^a × q^b (fórmula τ com expoentes triplicados)
public class Primo18 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p, q, a, b, n;
		do
		{
			p = LISTA_PRIMOS[rand.nextInt(2)];     // 2 ou 3
			q = LISTA_PRIMOS[2 + rand.nextInt(3)]; // 5,7,11
			a = 1 + rand.nextInt(2);               // 1-2
			b = 1 + rand.nextInt(2);               // 1-2
			int pa = 1; for(int i = 0; i < a; i++) pa *= p;
			int qb = 1; for(int i = 0; i < b; i++) qb *= q;
			n = pa * qb;
		}
		while(n > 200);

		int resultado = (3 * a + 1) * (3 * b + 1);
		String nLatex = p + "^{" + a + "} \\times " + q + "^{" + b + "}";
		String n3Latex = p + "^{" + (3 * a) + "} \\times " + q + "^{" + (3 * b) + "}";

		addParagrafo("Dado \\(N = " + nLatex + " = " + n + "\\), quantos divisores tem \\(N^3\\)?");
		gerarAlternativasInteiras(resultado);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& N^3 = " + n3Latex + "\\\\" +
			"& \\tau(N^3) = (" + (3 * a) + "{+}1)(" + (3 * b) + "{+}1)\\\\" +
			"& = " + (3 * a + 1) + " \\times " + (3 * b + 1) + " = \\mathbf{" + resultado + "}" +
			"\\end{aligned}\\)"
		);
	}
}
