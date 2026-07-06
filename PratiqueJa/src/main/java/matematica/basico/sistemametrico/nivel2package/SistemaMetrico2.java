package matematica.basico.sistemametrico.nivel2package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Comprimento: conversão de 2 passos (km→cm, m→mm) ou com decimal (km→m decimal)
public class SistemaMetrico2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);

		if (tipo == 0)
		{
			// km → cm: × 100 000
			int km = 1 + rand.nextInt(5);
			int cm = km * 100000;

			addParagrafo("Converta para centímetros \\((\\text{cm})\\):");
			addParagrafo("\\(" + km + "\\,\\text{km}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + cm + "\\,\\text{cm}\\)",
				Arrays.asList(
					"\\(" + (km * 1000) + "\\,\\text{cm}\\)",   // só ×1000 (para metros)
					"\\(" + (km * 10000) + "\\,\\text{cm}\\)",  // ×10000
					"\\(" + (km * 1000000) + "\\,\\text{cm}\\)" // ×1000000 (para mm)
				)
			);
			addResolucao("km para m (\\(\\times 1000\\)) e m para cm (\\(\\times 100\\)):");
			addResolucao("\\(" + km + "\\,\\text{km} = " + (km * 1000) + "\\,\\text{m} = \\mathbf{" + cm + "}\\,\\text{cm}\\)");
		}
		else if (tipo == 1)
		{
			// m → mm: × 1000
			int m  = 1 + rand.nextInt(20);
			int mm = m * 1000;

			addParagrafo("Converta para milímetros \\((\\text{mm})\\):");
			addParagrafo("\\(" + m + "\\,\\text{m}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + mm + "\\,\\text{mm}\\)",
				Arrays.asList(
					"\\(" + (m * 100) + "\\,\\text{mm}\\)",
					"\\(" + (m * 10) + "\\,\\text{mm}\\)",
					"\\(" + (mm * 10) + "\\,\\text{mm}\\)"
				)
			);
			addResolucao("m para cm (\\(\\times 100\\)) e cm para mm (\\(\\times 10\\)), ou diretamente m para mm (\\(\\times 1000\\)):");
			addResolucao("\\(" + m + " \\times 1000 = \\mathbf{" + mm + "}\\,\\text{mm}\\)");
		}
		else
		{
			// X,5 km → X500 m  (decimal km para metros)
			int intPart = 1 + rand.nextInt(9);
			int m = intPart * 1000 + 500;

			addParagrafo("Converta para metros \\((\\text{m})\\):");
			addParagrafo("\\(" + intPart + "{,}5\\,\\text{km}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + m + "\\,\\text{m}\\)",
				Arrays.asList(
					"\\(" + (intPart * 1000) + "\\,\\text{m}\\)",
					"\\(" + (intPart * 100 + 50) + "\\,\\text{m}\\)",
					"\\(" + (intPart * 1000 + 50) + "\\,\\text{m}\\)"
				)
			);
			addResolucao("\\(1\\,\\text{km} = 1000\\,\\text{m}\\)");
			addResolucao("\\(0{,}5\\,\\text{km} = 500\\,\\text{m}\\), logo somar:");
			addResolucao("\\(" + intPart + "{,}5 \\times 1000 = \\mathbf{" + m + "}\\,\\text{m}\\)");
		}
	}
}
