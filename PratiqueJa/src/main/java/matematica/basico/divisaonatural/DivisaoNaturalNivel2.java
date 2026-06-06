package matematica.basico.divisaonatural;

import matematica.GeradorExercicio;
import matematica.basico.resolucaonatural.ResolucaoNatural;

public class DivisaoNaturalNivel2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		if(rand.nextBoolean())
		{
			a = 10 + rand.nextInt(90);
			b = 1 + rand.nextInt(9);
		}
		else
		{
			a = 10 + rand.nextInt(90);
			b = 10 + rand.nextInt(90);
		}

		addParagrafo("Calcule a seguinte divisão:");
		addParagrafo("\\(" + ResolucaoNatural.divisao(a * b, b, false) + "\\)");
		gerarAlternativasInteiras(a);
		setResolucao("\\(" + ResolucaoNatural.divisao(a * b, b, true) + "\\)");
	}
}
