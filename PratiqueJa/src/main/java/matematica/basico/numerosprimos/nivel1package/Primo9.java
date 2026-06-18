package matematica.basico.numerosprimos.nivel1package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Qual é o menor fator primo de N?
public class Primo9 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		do { n = 4 + rand.nextInt(76); } while(ehPrimo(n)); // composto 4..79

		int menorFator = fatorar(n).keySet().iterator().next(); // LISTA_PRIMOS é crescente

		addParagrafo("Qual é o menor fator primo de \\(" + n + "\\)?");
		gerarAlternativasInteiras(menorFator);

		setResolucao(resolucaoFatoracao(n) + " \\(\\\\\\) Menor fator primo: \\(\\mathbf{" + menorFator + "}\\)");
	}
}
