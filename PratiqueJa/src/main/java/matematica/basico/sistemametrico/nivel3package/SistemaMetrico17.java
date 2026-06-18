package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Tempo composto: converter "X h Y min" para minutos
public class SistemaMetrico17 extends MetricoBase
{
	@Override
	protected void construir()
	{
		int h = 1 + rand.nextInt(5);          // 1..5
		int min = 5 * (1 + rand.nextInt(11)); // 5..55 (múltiplo de 5)
		int total = h * 60 + min;

		addParagrafo("Quantos minutos há em \\(" + h + "\\,\\text{h}\\;" + min + "\\,\\text{min}\\)?");
		gerarAlternativasInteiras(total);

		setResolucao(
			"\\(1\\,\\text{h} = 60\\,\\text{min}\\). Convertendo as horas e somando: \\(\\\\\\)" +
			"\\(" + h + " \\times 60 + " + min + " = " + (h * 60) + " + " + min + " = \\mathbf{" + total + "}\\,\\text{min}\\)"
		);
	}
}
