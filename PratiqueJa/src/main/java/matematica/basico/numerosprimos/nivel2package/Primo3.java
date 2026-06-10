package matematica.basico.numerosprimos.nivel2package;

import java.util.Map;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Maior fator primo de N: N = produto de exatamente 3 primos distintos dos 8 primeiros
public class Primo3 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n;
		Map<Integer, Integer> f;
		do
		{
			boolean[] usado = new boolean[8]; // primeiros 8 primos: 2,3,5,7,11,13,17,19
			n = 1;
			for(int i = 0; i < 3; i++)
			{
				int idx;
				do { idx = rand.nextInt(8); } while(usado[idx]);
				usado[idx] = true;
				n *= LISTA_PRIMOS[idx];
			}
			f = fatorar(n);
		}
		while(n > 500);

		int maiorFator = 0;
		for(int p : f.keySet())
			if(p > maiorFator) maiorFator = p;

		addParagrafo("Qual é o maior fator primo de \\(" + n + "\\)?");
		gerarAlternativasInteiras(maiorFator);

		setResolucao(
			resolucaoFatoracao(n) +
			" \\(\\\\\\) Maior fator primo: \\(\\mathbf{" + maiorFator + "}\\)"
		);
	}
}
