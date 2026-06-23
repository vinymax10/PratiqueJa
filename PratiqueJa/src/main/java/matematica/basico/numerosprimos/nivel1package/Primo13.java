package matematica.basico.numerosprimos.nivel1package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos divisores tem o número N? (faixa 18..60)
public class Primo13 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n = 18 + rand.nextInt(43); // 18..60

		int contDiv = 0;
		StringBuilder divStr = new StringBuilder();
		for(int d = 1; d <= n; d++)
		{
			if(n % d == 0)
			{
				contDiv++;
				if(divStr.length() > 0) divStr.append(",\\, ");
				divStr.append(d);
			}
		}

		addParagrafo("Quantos divisores tem o número \\(" + n + "\\)?");
		gerarAlternativasInteiras(contDiv);

		addResolucao("Divisores de \\(" + n + "\\): \\(\\{" + divStr + "\\}\\).");
		addResolucao("Total: \\(\\mathbf{" + contDiv + "}\\) divisores.");
	}
}
