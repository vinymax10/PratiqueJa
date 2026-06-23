package matematica.basico.numerosprimos.nivel1package;

import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Qual é o maior fator primo de N? (N composto qualquer de 10 a 99)
public class Primo15 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		do { n = 10 + rand.nextInt(90); } while(ehPrimo(n)); // 10..99 composto

		Map<Integer, Integer> f = fatorar(n);
		int maiorFator = 0;
		for(int p : f.keySet())
			if(p > maiorFator) maiorFator = p;

		addParagrafo("Qual é o maior fator primo de \\(" + n + "\\)?");
		gerarAlternativasInteiras(maiorFator);

		for(String passo : resolucaoFatoracao(n))
			addResolucao(passo);
		addResolucao("Maior fator primo: \\(\\mathbf{" + maiorFator + "}\\)");
	}
}
