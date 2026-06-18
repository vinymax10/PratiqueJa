package matematica.basico.numerosprimos.nivel1package;

import matematica.basico.numerosprimos.AgrupadorPrimo;

// Conceito: 2 é o único primo par — um par maior que 2 é primo?
public class Primo12 extends AgrupadorPrimo
{
	@Override
	protected void construir()
	{
		int n = 2 * (2 + rand.nextInt(30)); // par, 4..62

		addParagrafo("O número \\(" + n + "\\) é primo?");
		gerarAlternativasBoolean(false);

		setResolucao(
			"Todo número par maior que \\(2\\) é divisível por \\(2\\), além de \\(1\\) e dele mesmo. \\(\\\\\\) " +
			"\\(" + n + " \\div 2 = " + (n / 2) + "\\), então tem o divisor \\(2\\). \\(\\\\\\) " +
			"Logo, não é primo: \\(\\mathbf{Não}\\). (O \\(2\\) é o único número primo par.)"
		);
	}
}
