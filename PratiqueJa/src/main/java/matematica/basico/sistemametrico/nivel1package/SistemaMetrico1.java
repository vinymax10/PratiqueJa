package matematica.basico.sistemametrico.nivel1package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Comprimento: conversão de unidade maior para menor (km→m, m→cm, cm→mm)
public class SistemaMetrico1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);

		if (tipo == 0)
		{
			// km → m: × 1000
			int km = 1 + rand.nextInt(9);
			int m  = km * 1000;

			addParagrafo("Converta para metros \\((\\text{m})\\):");
			addParagrafo("\\(" + km + "\\,\\text{km}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + m + "\\,\\text{m}\\)",
				Arrays.asList(
					"\\(" + (km * 100) + "\\,\\text{m}\\)",
					"\\(" + (km * 10) + "\\,\\text{m}\\)",
					"\\(" + (km * 10000) + "\\,\\text{m}\\)"
				)
			);
			setResolucao(
				"\\(1\\,\\text{km} = 1000\\,\\text{m}\\), logo multiplicar por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + km + " \\times 1000 = \\mathbf{" + m + "}\\,\\text{m}\\)"
			);
		}
		else if (tipo == 1)
		{
			// m → cm: × 100
			int m  = 1 + rand.nextInt(20);
			int cm = m * 100;

			addParagrafo("Converta para centímetros \\((\\text{cm})\\):");
			addParagrafo("\\(" + m + "\\,\\text{m}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + cm + "\\,\\text{cm}\\)",
				Arrays.asList(
					"\\(" + (m * 10) + "\\,\\text{cm}\\)",
					"\\(" + (m * 1000) + "\\,\\text{cm}\\)",
					"\\(" + (m + 100) + "\\,\\text{cm}\\)"
				)
			);
			setResolucao(
				"\\(1\\,\\text{m} = 100\\,\\text{cm}\\), logo multiplicar por 100:" +
				"\\(\\\\\\)" +
				"\\(" + m + " \\times 100 = \\mathbf{" + cm + "}\\,\\text{cm}\\)"
			);
		}
		else
		{
			// cm → mm: × 10
			int cm = 1 + rand.nextInt(50);
			int mm = cm * 10;

			addParagrafo("Converta para milímetros \\((\\text{mm})\\):");
			addParagrafo("\\(" + cm + "\\,\\text{cm}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + mm + "\\,\\text{mm}\\)",
				Arrays.asList(
					"\\(" + (mm * 10) + "\\,\\text{mm}\\)",
					"\\(" + (mm / 10) + "\\,\\text{mm}\\)",
					"\\(" + (cm + 10) + "\\,\\text{mm}\\)"
				)
			);
			setResolucao(
				"\\(1\\,\\text{cm} = 10\\,\\text{mm}\\), logo multiplicar por 10:" +
				"\\(\\\\\\)" +
				"\\(" + cm + " \\times 10 = \\mathbf{" + mm + "}\\,\\text{mm}\\)"
			);
		}
	}
}
