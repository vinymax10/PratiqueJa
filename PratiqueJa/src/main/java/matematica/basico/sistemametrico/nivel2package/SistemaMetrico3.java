package matematica.basico.sistemametrico.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Área: conversão entre m², cm², km² (fator 100 por passo)
public class SistemaMetrico3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);

		if (tipo == 0)
		{
			// m² → cm²: × 10 000
			int m2  = 1 + rand.nextInt(9);
			int cm2 = m2 * 10000;

			addParagrafo("Converta para centímetros quadrados \\((\\text{cm}^2)\\):");
			addParagrafo("\\(" + m2 + "\\,\\text{m}^2\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + cm2 + "\\,\\text{cm}^2\\)",
				Arrays.asList(
					"\\(" + (m2 * 100) + "\\,\\text{cm}^2\\)",    // confundiu com comprimento
					"\\(" + (m2 * 1000) + "\\,\\text{cm}^2\\)",
					"\\(" + (m2 * 1000000) + "\\,\\text{cm}^2\\)" // fator de volume
				)
			);
			addResolucao("Para área, cada passo vale \\(\\times 100\\) (não \\(\\times 10\\)):");
			addResolucao("\\(1\\,\\text{m}^2 = 10{.}000\\,\\text{cm}^2\\)");
			addResolucao("\\(" + m2 + " \\times 10{.}000 = \\mathbf{" + cm2 + "}\\,\\text{cm}^2\\)");
		}
		else if (tipo == 1)
		{
			// cm² → m²: ÷ 10 000 (múltiplos de 5000)
			int intPart = 1 + rand.nextInt(9);
			boolean meio = rand.nextBoolean();
			int cm2 = intPart * 10000 + (meio ? 5000 : 0);
			String m2Str = intPart + (meio ? "{,}5" : "");

			addParagrafo("Converta para metros quadrados \\((\\text{m}^2)\\):");
			addParagrafo("\\(" + cm2 + "\\,\\text{cm}^2\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + m2Str + "\\,\\text{m}^2\\)",
				Arrays.asList(
					"\\(" + intPart + (meio ? "{,}05" : "{,}5") + "\\,\\text{m}^2\\)",
					"\\(" + (intPart * 10) + "\\,\\text{m}^2\\)",
					"\\(" + (intPart * 100) + "\\,\\text{m}^2\\)"
				)
			);
			addResolucao("\\(1\\,\\text{m}^2 = 10{.}000\\,\\text{cm}^2\\), logo dividir por 10 000:");
			addResolucao("\\(" + cm2 + " \\div 10{.}000 = \\mathbf{" + m2Str + "}\\,\\text{m}^2\\)");
		}
		else
		{
			// km² → m²: × 1 000 000
			int km2 = 1 + rand.nextInt(5);
			int m2  = km2 * 1000000;

			addParagrafo("Converta para metros quadrados \\((\\text{m}^2)\\):");
			addParagrafo("\\(" + km2 + "\\,\\text{km}^2\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + m2 + "\\,\\text{m}^2\\)",
				Arrays.asList(
					"\\(" + (km2 * 1000) + "\\,\\text{m}^2\\)",    // confundiu km² com km
					"\\(" + (km2 * 10000) + "\\,\\text{m}^2\\)",
					"\\(" + (km2 * 100000) + "\\,\\text{m}^2\\)"
				)
			);
			addResolucao("\\(1\\,\\text{km}^2 = 1{.}000{.}000\\,\\text{m}^2\\), pois km para m é \\(\\times 1000\\), logo \\(\\text{km}^2\\) para \\(\\text{m}^2\\) é \\(\\times 1000^2 = 1{.}000{.}000\\):");
			addResolucao("\\(" + km2 + " \\times 1{.}000{.}000 = \\mathbf{" + m2 + "}\\,\\text{m}^2\\)");
		}
	}
}
