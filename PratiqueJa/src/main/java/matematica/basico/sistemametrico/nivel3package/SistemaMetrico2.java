package matematica.basico.sistemametrico.nivel3package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Tempo: conversão entre horas, minutos e segundos
public class SistemaMetrico2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(4);

		if (tipo == 0)
		{
			// h → min: × 60
			int h   = 1 + rand.nextInt(12);
			int min = h * 60;

			addParagrafo("Converta para minutos:");
			addParagrafo("\\(" + h + "\\,\\text{h}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + min + "\\,\\text{min}\\)",
				Arrays.asList(
					"\\(" + (h * 24) + "\\,\\text{min}\\)",
					"\\(" + (h * 100) + "\\,\\text{min}\\)",
					"\\(" + (h * 60 + 60) + "\\,\\text{min}\\)"
				)
			);
			addResolucao("\\(1\\,\\text{h} = 60\\,\\text{min}\\), logo multiplicar por 60:");
			addResolucao("\\(" + h + " \\times 60 = \\mathbf{" + min + "}\\,\\text{min}\\)");
		}
		else if (tipo == 1)
		{
			// min → s: × 60
			int min = 1 + rand.nextInt(30);
			int s   = min * 60;

			addParagrafo("Converta para segundos:");
			addParagrafo("\\(" + min + "\\,\\text{min}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + s + "\\,\\text{s}\\)",
				Arrays.asList(
					"\\(" + (min * 100) + "\\,\\text{s}\\)",
					"\\(" + (min * 10) + "\\,\\text{s}\\)",
					"\\(" + (s + 60) + "\\,\\text{s}\\)"
				)
			);
			addResolucao("\\(1\\,\\text{min} = 60\\,\\text{s}\\), logo multiplicar por 60:");
			addResolucao("\\(" + min + " \\times 60 = \\mathbf{" + s + "}\\,\\text{s}\\)");
		}
		else if (tipo == 2)
		{
			// h → s: × 3600
			int h = 1 + rand.nextInt(6);
			int s = h * 3600;

			addParagrafo("Converta para segundos:");
			addParagrafo("\\(" + h + "\\,\\text{h}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + s + "\\,\\text{s}\\)",
				Arrays.asList(
					"\\(" + (h * 60) + "\\,\\text{s}\\)",    // só ×60
					"\\(" + (h * 600) + "\\,\\text{s}\\)",
					"\\(" + (h * 360) + "\\,\\text{s}\\)"
				)
			);
			addResolucao("\\(1\\,\\text{h} = 60\\,\\text{min}\\) e \\(1\\,\\text{min} = 60\\,\\text{s}\\), logo \\(1\\,\\text{h} = 3600\\,\\text{s}\\):");
			addResolucao("\\(" + h + " \\times 3600 = \\mathbf{" + s + "}\\,\\text{s}\\)");
		}
		else
		{
			// min → h: múltiplos de 30  (X h Y min)
			int h   = 1 + rand.nextInt(5);
			int min = h * 60;

			addParagrafo("Converta para horas:");
			addParagrafo("\\(" + min + "\\,\\text{min}\\)");
			embaralharEAdicionarAlternativas(
				"\\(" + h + "\\,\\text{h}\\)",
				Arrays.asList(
					"\\(" + (h * 10) + "\\,\\text{h}\\)",
					"\\(0{,}" + h + "\\,\\text{h}\\)",
					"\\(" + (h + 1) + "\\,\\text{h}\\)"
				)
			);
			addResolucao("\\(1\\,\\text{h} = 60\\,\\text{min}\\), logo dividir por 60:");
			addResolucao("\\(" + min + " \\div 60 = \\mathbf{" + h + "}\\,\\text{h}\\)");
		}
	}
}
