package matematica.basico.sistemametrico.nivel3package;

import java.util.Arrays;
import matematica.GeradorExercicio;

// Problema misto: combina comprimento + massa + capacidade ou área em contexto real
public class SistemaMetrico4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int tipo = rand.nextInt(3);

		if (tipo == 0)
		{
			// Velocidade: km/h → m/min → m/s (encadeado)
			// v km/h = v*1000/3600 m/s = v/3.6 m/s
			// Para resultado inteiro: v múltiplo de 36 → v = 36, 72, 108...
			int[] velocidades = {36, 72, 108};
			int kmh = velocidades[rand.nextInt(velocidades.length)];
			int ms  = kmh * 1000 / 3600; // 10, 20 ou 30

			addParagrafo("Um carro viaja a \\(" + kmh + "\\,\\text{km/h}\\).");
			addParagrafo("Qual é sua velocidade em metros por segundo \\((\\text{m/s})\\)?");
			embaralharEAdicionarAlternativas(
				"\\(" + ms + "\\,\\text{m/s}\\)",
				Arrays.asList(
					"\\(" + (kmh / 60) + "\\,\\text{m/s}\\)",
					"\\(" + (kmh * 60) + "\\,\\text{m/s}\\)",
					"\\(" + (ms * 10) + "\\,\\text{m/s}\\)"
				)
			);
			setResolucao(
				"Converter km → m (×1000) e h → s (×3600):" +
				"\\(\\\\\\)" +
				"\\(\\dfrac{" + kmh + "\\,\\text{km}}{\\text{h}} = \\dfrac{" + (kmh * 1000) + "\\,\\text{m}}{3600\\,\\text{s}}\\)" +
				"\\(\\\\\\)" +
				"\\(= \\dfrac{" + (kmh * 1000) + "}{3600} = \\mathbf{" + ms + "}\\,\\text{m/s}\\)"
			);
		}
		else if (tipo == 1)
		{
			// Densidade: g/cm³ ↔ kg/m³  (fator 1000)
			// ρ g/cm³ = ρ × 1000 kg/m³
			int[] densidades = {1, 2, 5, 8, 10};
			int gcm3 = densidades[rand.nextInt(densidades.length)];
			int kgm3 = gcm3 * 1000;

			addParagrafo("A densidade de um material é \\(" + gcm3 + "\\,\\text{g/cm}^3\\).");
			addParagrafo("Qual é essa densidade em \\(\\text{kg/m}^3\\)?");
			embaralharEAdicionarAlternativas(
				"\\(" + kgm3 + "\\,\\text{kg/m}^3\\)",
				Arrays.asList(
					"\\(" + (gcm3 * 100) + "\\,\\text{kg/m}^3\\)",
					"\\(" + gcm3 + "\\,\\text{kg/m}^3\\)",           // esqueceu de converter
					"\\(" + (kgm3 * 1000) + "\\,\\text{kg/m}^3\\)"
				)
			);
			setResolucao(
				"Converter g→kg (÷1000) e cm³→m³ (÷1 000 000):" +
				"\\(\\\\\\)" +
				"\\(\\dfrac{\\text{g}}{\\text{cm}^3} \\times \\dfrac{1\\,\\text{kg}}{1000\\,\\text{g}} \\times \\dfrac{1{.}000{.}000\\,\\text{cm}^3}{1\\,\\text{m}^3}\\)" +
				"\\(\\\\\\)" +
				"\\(= \\times 1000\\)" +
				"\\(\\\\\\)" +
				"\\(" + gcm3 + " \\times 1000 = \\mathbf{" + kgm3 + "}\\,\\text{kg/m}^3\\)"
			);
		}
		else
		{
			// Área de terreno em hectares vs m²: 1 ha = 10 000 m²
			int ha = 1 + rand.nextInt(9);
			int m2 = ha * 10000;

			addParagrafo("Um terreno tem \\(" + ha + "\\,\\text{ha}\\) (hectare" + (ha > 1 ? "s" : "") + ").");
			addParagrafo("Quantos metros quadrados \\((\\text{m}^2)\\) correspondem?");
			addParagrafo("Use: \\(1\\,\\text{ha} = 10{.}000\\,\\text{m}^2\\).");
			embaralharEAdicionarAlternativas(
				"\\(" + m2 + "\\,\\text{m}^2\\)",
				Arrays.asList(
					"\\(" + (ha * 1000) + "\\,\\text{m}^2\\)",
					"\\(" + (ha * 100) + "\\,\\text{m}^2\\)",
					"\\(" + (ha * 100000) + "\\,\\text{m}^2\\)"
				)
			);
			setResolucao(
				"1 ha = 1 hm² = 10 000 m² → multiplicar por 10 000:" +
				"\\(\\\\\\)" +
				"\\(" + ha + " \\times 10{.}000 = \\mathbf{" + m2 + "}\\,\\text{m}^2\\)"
			);
		}
	}
}
