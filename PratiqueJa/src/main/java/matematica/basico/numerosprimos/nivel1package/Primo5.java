package matematica.basico.numerosprimos.nivel1package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// O número N é primo? (Sim/Não)
public class Primo5 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n = 2 + rand.nextInt(58); // 2..59
		boolean ePrimo = ehPrimo(n);

		addParagrafo("O número \\(" + n + "\\) é primo?");
		gerarAlternativasBoolean(ePrimo);

		if(ePrimo)
		{
			addResolucao("\\(" + n + "\\) só é divisível por \\(1\\) e por ele mesmo.");
			addResolucao("Logo, é primo: \\(\\mathbf{Sim}\\).");
		}
		else
		{
			int p = fatorar(n).keySet().iterator().next();
			addResolucao("\\(" + n + " \\div " + p + " = " + (n / p) + "\\) (divisão exata).");
			addResolucao("Como tem o divisor \\(" + p + "\\), não é primo: \\(\\mathbf{Não}\\).");
		}
	}
}
