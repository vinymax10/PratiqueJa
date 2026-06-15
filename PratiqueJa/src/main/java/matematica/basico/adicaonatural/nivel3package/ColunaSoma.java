package matematica.basico.adicaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.resolucaonatural.ResolucaoNatural;

public class ColunaSoma extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		if (rand.nextBoolean())
		{
			a = 100 + rand.nextInt(900);
			b = 100 + rand.nextInt(900);
		}
		else
		{
			a = 100 + rand.nextInt(900);
			b = 10 + rand.nextInt(90);
		}

		addParagrafo("Calcule a seguinte adição:");
		addParagrafo("\\(" + ResolucaoNatural.soma(a, b, false) + "\\)");

		gerarAlternativasInteiras(a + b);

		setResolucao("\\(" + ResolucaoNatural.soma(a, b, true) + "\\)");
	}
}
