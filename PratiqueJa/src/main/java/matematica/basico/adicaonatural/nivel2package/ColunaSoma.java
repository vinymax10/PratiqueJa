package matematica.basico.adicaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.resolucaonatural.ResolucaoNatural;

public class ColunaSoma extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		if (rand.nextBoolean())
		{
			a = 10 + rand.nextInt(90);
			b = 1 + rand.nextInt(9);
		}
		else
		{
			a = 10 + rand.nextInt(90);
			b = 10 + rand.nextInt(90);
		}

		addParagrafo("Calcule a seguinte adição:");
		addParagrafo("\\(" + ResolucaoNatural.soma(a, b, false) + "\\)");

		gerarAlternativasInteiras(a + b);

		setResolucao("\\(" + ResolucaoNatural.soma(a, b, true) + "\\)");
	}
}
