package matematica.basico.numerosprimos.nivel1package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos números primos existem até N? (crivo de Eratóstenes)
public class Primo8 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n = 12 + rand.nextInt(38); // 12..49

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

		setResolucao("Primos até \\(" + n + "\\): \\(" + lista + "\\). \\(\\\\\\) Total: \\(\\mathbf{" + contagem + "}\\).");
	}
}
