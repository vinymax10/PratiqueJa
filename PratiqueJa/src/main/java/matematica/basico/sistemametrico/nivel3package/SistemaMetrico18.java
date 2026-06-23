package matematica.basico.sistemametrico.nivel3package;

import matematica.basico.sistemametrico.MetricoBase;

// Tempo composto: converter "X min Y s" para segundos
public class SistemaMetrico18 extends MetricoBase
{
	@Override
	protected void construir()
	{
		int min = 1 + rand.nextInt(9);      // 1..9
		int s = 5 * (1 + rand.nextInt(11)); // 5..55 (múltiplo de 5)
		int total = min * 60 + s;

		addParagrafo("Quantos segundos há em \\(" + min + "\\,\\text{min}\\;" + s + "\\,\\text{s}\\)?");
		gerarAlternativasInteiras(total);

		addResolucao("\\(1\\,\\text{min} = 60\\,\\text{s}\\). Convertendo os minutos e somando:");
		addResolucao("\\(" + min + " \\times 60 + " + s + " = " + (min * 60) + " + " + s + " = \\mathbf{" + total + "}\\,\\text{s}\\)");
	}
}
