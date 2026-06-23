package matematica.basico.numerosprimos.nivel2package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Dado N = p^a × q^b na forma fatorada, calcule N — segunda variação
public class Primo9 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p, a, q, b, n, pa, qb;
		do
		{
			p = LISTA_PRIMOS[rand.nextInt(3)];     // 2,3,5
			a = 1 + rand.nextInt(3);
			q = LISTA_PRIMOS[3 + rand.nextInt(3)]; // 7,11,13
			b = 1 + rand.nextInt(2);
			pa = 1; for(int i = 0; i < a; i++) pa *= p;
			qb = 1; for(int i = 0; i < b; i++) qb *= q;
			n = pa * qb;
		}
		while(n > 400 || (a == 1 && b == 1));

		String pLatex = p + (a > 1 ? "^{" + a + "}" : "");
		String qLatex = q + (b > 1 ? "^{" + b + "}" : "");

		addParagrafo("Calcule o valor de \\(N\\), sendo \\(N = " + pLatex + " \\times " + qLatex + "\\):");
		gerarAlternativasInteiras(n);

		addResolucao(
			"\\(\\begin{aligned}" +
			"& N = " + pLatex + " \\times " + qLatex + "\\\\" +
			"& N = " + pa + " \\times " + qb + " = \\mathbf{" + n + "}" +
			"\\end{aligned}\\)"
		);
	}
}
