package matematica.basico.numerosprimos.nivel3package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos divisores tem N²? onde N = p^a × q^b (terceira variação)
public class Primo16 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p, q, a, b, n;
		do
		{
			p = LISTA_PRIMOS[rand.nextInt(2)];     // 2 ou 3
			q = LISTA_PRIMOS[2 + rand.nextInt(4)]; // 5,7,11,13
			a = 2 + rand.nextInt(2);               // 2-3
			b = 1 + rand.nextInt(2);               // 1-2
			int pa = 1; for(int i = 0; i < a; i++) pa *= p;
			int qb = 1; for(int i = 0; i < b; i++) qb *= q;
			n = pa * qb;
		}
		while(n > 500);

		int resultado = (2 * a + 1) * (2 * b + 1);
		String nLatex = p + "^{" + a + "} \\times " + q + "^{" + b + "}";
		String n2Latex = p + "^{" + (2 * a) + "} \\times " + q + "^{" + (2 * b) + "}";

		addParagrafo("Dado \\(N = " + nLatex + " = " + n + "\\), quantos divisores tem \\(N^2\\)?");
		gerarAlternativasInteiras(resultado);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& N^2 = " + n2Latex + "\\\\" +
			"& \\tau(N^2) = (" + (2 * a) + "{+}1)(" + (2 * b) + "{+}1)\\\\" +
			"& = " + (2 * a + 1) + " \\times " + (2 * b + 1) + " = \\mathbf{" + resultado + "}" +
			"\\end{aligned}\\)"
		);
	}
}
