package matematica.basico.numerosprimos.nivel1package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Quantos divisores tem o número N?
public class Primo2 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n = 2 + rand.nextInt(29); // 2 a 30

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
