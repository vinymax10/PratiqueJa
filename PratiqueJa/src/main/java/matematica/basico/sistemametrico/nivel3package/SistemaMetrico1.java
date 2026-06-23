package matematica.basico.sistemametrico.nivel3package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Volume: m³↔cm³ (fator 1 000 000) e equivalência dm³ = 1 L
public class SistemaMetrico1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);

		if (tipo == 0)
		{
			// m³ → cm³: × 1 000 000
			int m3  = 1 + rand.nextInt(5);
			int cm3 = m3 * 1000000;

			addParagrafo("Converta para centímetros cúbicos \\((\\text{cm}^3)\\):");
			addParagrafo("\\(" + m3 + "\\,\\text{m}^3\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + cm3 + "\\,\\text{cm}^3\\)",
				Arrays.asList(
					"\\(" + (m3 * 1000) + "\\,\\text{cm}^3\\)",     // confundiu com comprimento
					"\\(" + (m3 * 10000) + "\\,\\text{cm}^3\\)",    // fator de área
					"\\(" + (m3 * 100000) + "\\,\\text{cm}^3\\)"
				)
			);
			addResolucao("Para volume, cada passo vale \\(\\times 1000\\,(= 10^3)\\):");
			addResolucao("\\(1\\,\\text{m}^3 = 1{.}000{.}000\\,\\text{cm}^3\\)");
			addResolucao("\\(" + m3 + " \\times 1{.}000{.}000 = \\mathbf{" + cm3 + "}\\,\\text{cm}^3\\)");
		}
		else if (tipo == 1)
		{
			// dm³ = L: conversão direta
			int dm3 = 1 + rand.nextInt(20);

			addParagrafo("Quantos litros equivalem a \\(" + dm3 + "\\,\\text{dm}^3\\)?");
			addParagrafo("Use a equivalência: \\(1\\,\\text{dm}^3 = 1\\,\\text{L}\\).");
			embaralharEAdicionarAlternativas(
				"\\(" + dm3 + "\\,\\text{L}\\)",
				Arrays.asList(
					"\\(" + (dm3 * 10) + "\\,\\text{L}\\)",
					"\\(" + (dm3 * 1000) + "\\,\\text{L}\\)",
					"\\(" + (dm3 / 10 == 0 ? dm3 + 1 : dm3 / 10) + "\\,\\text{L}\\)"
				)
			);
			addResolucao("Equivalência fundamental: \\(1\\,\\text{dm}^3 = 1\\,\\text{L}\\):");
			addResolucao("\\(" + dm3 + "\\,\\text{dm}^3 = \\mathbf{" + dm3 + "}\\,\\text{L}\\)");
		}
		else
		{
			// L → dm³ → cm³: 1 L = 1 dm³ = 1000 cm³
			int l   = 1 + rand.nextInt(10);
			int cm3 = l * 1000;

			addParagrafo("Converta para centímetros cúbicos \\((\\text{cm}^3)\\):");
			addParagrafo("\\(" + l + "\\,\\text{L}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + cm3 + "\\,\\text{cm}^3\\)",
				Arrays.asList(
					"\\(" + (l * 100) + "\\,\\text{cm}^3\\)",
					"\\(" + (l * 10) + "\\,\\text{cm}^3\\)",
					"\\(" + (l * 1000000) + "\\,\\text{cm}^3\\)"
				)
			);
			addResolucao("\\(1\\,\\text{L} = 1\\,\\text{dm}^3 = 1000\\,\\text{cm}^3\\):");
			addResolucao("\\(" + l + " \\times 1000 = \\mathbf{" + cm3 + "}\\,\\text{cm}^3\\)");
		}
	}
}
