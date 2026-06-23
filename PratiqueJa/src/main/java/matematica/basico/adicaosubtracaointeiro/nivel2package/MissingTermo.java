package matematica.basico.adicaosubtracaointeiro.nivel2package;

import matematica.Auxiliar;
import matematica.GeradorExercicio;

public class MissingTermo extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int b = 10 + rand.nextInt(90); if (rand.nextBoolean()) b = -b;
		int missing = 10 + rand.nextInt(90); if (rand.nextBoolean()) missing = -missing;
		int c = missing + b;

		boolean missingFirst = rand.nextBoolean();

		if (missingFirst)
			addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(\\square " + Auxiliar.getNumber(b, "", false) + " = " + c + "\\)?");
		else
			addParagrafo("Qual é o valor de \\(\\square\\) na equação \\(" + Auxiliar.getNumber(b, "", true) + " + \\square = " + c + "\\)?");

		gerarAlternativasInteirasComNegativos(missing);

		addResolucao("Isolamos o termo desconhecido usando a operação inversa:");
		addResolucao("\\(\\square = " + c + Auxiliar.getNumber(-b, "", false) + " = \\mathbf{" + missing + "}\\)");
	}
}
