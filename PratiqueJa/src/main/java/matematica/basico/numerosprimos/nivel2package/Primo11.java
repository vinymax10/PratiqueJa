package matematica.basico.numerosprimos.nivel2package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Menor fator primo de N (composto de 40 a 150)
public class Primo11 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		do { n = 40 + rand.nextInt(111); } while(ehPrimo(n));

		int menorFator = fatorar(n).keySet().iterator().next();

		addParagrafo("Qual é o menor fator primo de \\(" + n + "\\)?");
		gerarAlternativasInteiras(menorFator);

		for(String passo : resolucaoFatoracao(n))
			addResolucao(passo);
		addResolucao("Menor fator primo: \\(\\mathbf{" + menorFator + "}\\)");
	}
}
