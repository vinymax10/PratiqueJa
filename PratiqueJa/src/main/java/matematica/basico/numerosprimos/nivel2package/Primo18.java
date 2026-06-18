package matematica.basico.numerosprimos.nivel2package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Dado N = p^a × q^b na forma fatorada, calcule N — terceira variação
public class Primo18 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p, a, q, b, n, pa, qb;
		do
		{
			p = LISTA_PRIMOS[rand.nextInt(2)];     // 2,3
			a = 2 + rand.nextInt(3);               // 2-4
			q = LISTA_PRIMOS[2 + rand.nextInt(3)]; // 5,7,11
			b = 1 + rand.nextInt(2);               // 1-2
			pa = 1; for(int i = 0; i < a; i++) pa *= p;
			qb = 1; for(int i = 0; i < b; i++) qb *= q;
			n = pa * qb;
		}
		while(n > 600);

		String pLatex = p + "^{" + a + "}";
		String qLatex = q + (b > 1 ? "^{" + b + "}" : "");

		addParagrafo("Calcule o valor de \\(N\\), sendo \\(N = " + pLatex + " \\times " + qLatex + "\\):");
		gerarAlternativasInteiras(n);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& N = " + pLatex + " \\times " + qLatex + "\\\\" +
			"& N = " + pa + " \\times " + qb + " = \\mathbf{" + n + "}" +
			"\\end{aligned}\\)"
		);
	}
}
