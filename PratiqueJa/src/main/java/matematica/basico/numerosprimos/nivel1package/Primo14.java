package matematica.basico.numerosprimos.nivel1package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Qual é a soma de todos os divisores de N?
public class Primo14 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n = 6 + rand.nextInt(40); // 6..45

		int soma = 0;
		StringBuilder divStr = new StringBuilder();
		for(int d = 1; d <= n; d++)
		{
			if(n % d == 0)
			{
				soma += d;
				if(divStr.length() > 0) divStr.append(" + ");
				divStr.append(d);
			}
		}

		addParagrafo("Qual é a soma de todos os divisores de \\(" + n + "\\)?");
		gerarAlternativasInteiras(soma);

		addResolucao("Divisores de \\(" + n + "\\) somados:");
		addResolucao("\\(" + divStr + " = \\mathbf{" + soma + "}\\).");
	}
}
