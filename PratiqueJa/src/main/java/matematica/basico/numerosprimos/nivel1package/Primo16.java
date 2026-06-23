package matematica.basico.numerosprimos.nivel1package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// O número N é primo? (Sim/Não, faixa 30..90)
public class Primo16 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n = 30 + rand.nextInt(61); // 30..90
		boolean ePrimo = ehPrimo(n);

		addParagrafo("O número \\(" + n + "\\) é primo?");
		gerarAlternativasBoolean(ePrimo);

		if(ePrimo)
		{
			addResolucao("Nenhum primo até \\(\\sqrt{" + n + "}\\) divide \\(" + n + "\\).");
			addResolucao("Logo, é primo: \\(\\mathbf{Sim}\\).");
		}
		else
		{
			int p = fatorar(n).keySet().iterator().next();
			addResolucao("\\(" + n + " \\div " + p + " = " + (n / p) + "\\) (exato).");
			addResolucao("Como é divisível por \\(" + p + "\\), não é primo: \\(\\mathbf{Não}\\).");
		}
	}
}
