package matematica.basico.sistemametrico.nivel1package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Massa: conversão de unidade maior para menor (t→kg, kg→g)
public class SistemaMetrico3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		boolean tonelada = rand.nextBoolean();

		if (tonelada)
		{
			// t → kg: × 1000
			int t  = 1 + rand.nextInt(9);
			int kg = t * 1000;

			addParagrafo("Converta para quilogramas \\((\\text{kg})\\):");
			addParagrafo("\\(" + t + "\\,\\text{t}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + kg + "\\,\\text{kg}\\)",
				Arrays.asList(
					"\\(" + (t * 100) + "\\,\\text{kg}\\)",
					"\\(" + (t * 10) + "\\,\\text{kg}\\)",
					"\\(" + (t * 10000) + "\\,\\text{kg}\\)"
				)
			);
			setResolucao(
				"1 t = 1000 kg → multiplicar por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + t + " \\times 1000 = \\mathbf{" + kg + "}\\,\\text{kg}\\)"
			);
		}
		else
		{
			// kg → g: × 1000
			int kg = 1 + rand.nextInt(9);
			int g  = kg * 1000;

			addParagrafo("Converta para gramas \\((\\text{g})\\):");
			addParagrafo("\\(" + kg + "\\,\\text{kg}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + g + "\\,\\text{g}\\)",
				Arrays.asList(
					"\\(" + (kg * 100) + "\\,\\text{g}\\)",
					"\\(" + (kg * 10) + "\\,\\text{g}\\)",
					"\\(" + (g * 10) + "\\,\\text{g}\\)"
				)
			);
			setResolucao(
				"1 kg = 1000 g → multiplicar por 1000:" +
				"\\(\\\\\\)" +
				"\\(" + kg + " \\times 1000 = \\mathbf{" + g + "}\\,\\text{g}\\)"
			);
		}
	}
}
