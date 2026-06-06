package matematica.basico.divisaonatural;

import matematica.GeradorExercicio;
import matematica.basico.resolucaonatural.ResolucaoNatural;

public class DivisaoNaturalNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 100 + rand.nextInt(900);
		int b = 10 + rand.nextInt(90);

		addParagrafo("Calcule a seguinte divisão:");
		addParagrafo("\\(" + ResolucaoNatural.divisao(a * b, b, false) + "\\)");
		gerarAlternativasInteiras(a);
		setResolucao("\\(" + ResolucaoNatural.divisao(a * b, b, true) + "\\)");
	}
}
