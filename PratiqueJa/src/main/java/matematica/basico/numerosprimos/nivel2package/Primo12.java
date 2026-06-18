package matematica.basico.numerosprimos.nivel2package;

import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Maior fator primo de N (composto de 40 a 200)
public class Primo12 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		Map<Integer, Integer> f;
		do { n = 40 + rand.nextInt(161); f = fatorar(n); } while(ehPrimo(n));

		int maiorFator = 0;
		for(int p : f.keySet())
			if(p > maiorFator) maiorFator = p;

		addParagrafo("Qual é o maior fator primo de \\(" + n + "\\)?");
		gerarAlternativasInteiras(maiorFator);

		setResolucao(resolucaoFatoracao(n) + " \\(\\\\\\) Maior fator primo: \\(\\mathbf{" + maiorFator + "}\\)");
	}
}
