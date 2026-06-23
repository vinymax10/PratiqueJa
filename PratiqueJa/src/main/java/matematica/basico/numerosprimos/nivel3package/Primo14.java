package matematica.basico.numerosprimos.nivel3package;

import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Maior fator primo de N = produto de 3 a 4 primos distintos
public class Primo14 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		Map<Integer, Integer> f;
		do
		{
			int qtd = 3 + rand.nextInt(2); // 3 ou 4 fatores
			boolean[] usado = new boolean[8]; // 2,3,5,7,11,13,17,19
			n = 1;
			for(int i = 0; i < qtd; i++)
			{
				int idx;
				do { idx = rand.nextInt(8); } while(usado[idx]);
				usado[idx] = true;
				n *= LISTA_PRIMOS[idx];
			}
			f = fatorar(n);
		}
		while(n > 1500);

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
