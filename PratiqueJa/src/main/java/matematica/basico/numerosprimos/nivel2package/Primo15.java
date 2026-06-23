package matematica.basico.numerosprimos.nivel2package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos números primos existem até N? (faixa 40..70)
public class Primo15 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n = 40 + rand.nextInt(31); // 40..70

		int contagem = 0;
		StringBuilder lista = new StringBuilder();
		for(int k = 2; k <= n; k++)
		{
			if(ehPrimo(k))
			{
				contagem++;
				if(lista.length() > 0) lista.append(", ");
				lista.append(k);
			}
		}

		addParagrafo("Quantos números primos existem de \\(2\\) até \\(" + n + "\\)?");
		gerarAlternativasInteiras(contagem);

		addResolucao("Primos até \\(" + n + "\\): \\(" + lista + "\\).");
		addResolucao("Total: \\(\\mathbf{" + contagem + "}\\).");
	}
}
