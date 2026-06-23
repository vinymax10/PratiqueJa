package matematica.basico.numerosprimos.nivel1package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Qual é o menor número primo maior que N?
public class Primo6 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n = 6 + rand.nextInt(60); // 6..65
		int proximo = n + 1;
		while(!ehPrimo(proximo)) proximo++;

		addParagrafo("Qual é o menor número primo maior que \\(" + n + "\\)?");
		gerarAlternativasInteiras(proximo);

		StringBuilder descartados = new StringBuilder();
		for(int k = n + 1; k < proximo; k++)
		{
			if(descartados.length() > 0) descartados.append(",\\, ");
			descartados.append(k);
		}
		addResolucao("Testamos os números após \\(" + n + "\\) até achar um primo.");
		if(descartados.length() > 0)
			addResolucao("\\(" + descartados + "\\) são compostos.");
		addResolucao("O primeiro primo é \\(\\mathbf{" + proximo + "}\\).");
	}
}
