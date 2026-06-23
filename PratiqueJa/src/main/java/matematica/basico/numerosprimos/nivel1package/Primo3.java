package matematica.basico.numerosprimos.nivel1package;

import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Qual é o maior fator primo de N? (N = produto de 2-3 primos distintos)
public class Primo3 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int numFatores = 2 + rand.nextInt(2); // 2 ou 3 fatores distintos
		boolean[] usado = new boolean[6];     // primeiros 6 primos: 2,3,5,7,11,13
		int n = 1;

		for(int i = 0; i < numFatores; i++)
		{
			int idx;
			do { idx = rand.nextInt(6); } while(usado[idx]);
			usado[idx] = true;
			n *= LISTA_PRIMOS[idx];
		}

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
