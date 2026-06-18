package matematica.basico.subtracaonatural.nivel2package;

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
		if (a < b) { int t = a; a = b; b = t; }

		addParagrafo("Calcule a seguinte subtração:");
		addParagrafo("\\(" + ResolucaoNatural.subtracao(a, b, false) + "\\)");

		gerarAlternativasInteiras(a - b);

		setResolucao("\\(" + ResolucaoNatural.subtracao(a, b, true) + "\\)");
	}
}
