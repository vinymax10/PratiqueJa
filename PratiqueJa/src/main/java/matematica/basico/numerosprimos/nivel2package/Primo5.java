package matematica.basico.numerosprimos.nivel2package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Dado N = p^a × q^b na forma fatorada, calcule N
public class Primo5 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int p, a, q, b, n;
		do
		{
			p = LISTA_PRIMOS[rand.nextInt(3)];     // 2, 3 ou 5
			a = 1 + rand.nextInt(3);               // 1-3
			q = LISTA_PRIMOS[3 + rand.nextInt(4)]; // 7, 11, 13 ou 17
			b = 1 + rand.nextInt(2);               // 1-2
			int tmpP = 1; for(int i = 0; i < a; i++) tmpP *= p;
			int tmpQ = 1; for(int i = 0; i < b; i++) tmpQ *= q;
			n = tmpP * tmpQ;
		}
		while(n > 250 || (a == 1 && b == 1)); // garante pelo menos um expoente > 1

		int pa = 1; for(int i = 0; i < a; i++) pa *= p;
		int qb = 1; for(int i = 0; i < b; i++) qb *= q;

		String pLatex = p + (a > 1 ? "^{" + a + "}" : "");
		String qLatex = q + (b > 1 ? "^{" + b + "}" : "");
		String nLatex = pLatex + " \\times " + qLatex;

		addParagrafo("Calcule o valor de \\(N\\), sendo \\(N = " + nLatex + "\\):");
		gerarAlternativasInteiras(n);

		setResolucao(
			"\\(\\begin{aligned}" +
			"& N = " + nLatex + "\\\\" +
			"& N = " + pa + " \\times " + qb + "\\\\" +
			"& N = \\mathbf{" + n + "}" +
			"\\end{aligned}\\)"
		);
	}
}
