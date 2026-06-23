package matematica.basico.subtracaonatural.nivel3package;

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
			a = 100 + rand.nextInt(900);
			b = 100 + rand.nextInt(900);
		}
		else
		{
			a = 100 + rand.nextInt(900);
			b = 10 + rand.nextInt(90);
		}
		if (a < b) { int t = a; a = b; b = t; }

		addParagrafo("Calcule a seguinte subtração:");
		addParagrafo("\\(" + ResolucaoNatural.subtracao(a, b, false) + "\\)");

		gerarAlternativasInteiras(a - b);

		addResolucao("\\(" + ResolucaoNatural.subtracao(a, b, true) + "\\)");
	}
}
