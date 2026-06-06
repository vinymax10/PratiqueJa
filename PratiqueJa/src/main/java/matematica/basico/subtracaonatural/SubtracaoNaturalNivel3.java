package matematica.basico.subtracaonatural;

import matematica.GeradorExercicio;
import matematica.basico.resolucaonatural.ResolucaoNatural;

/**
 * Subtração de naturais (nível 3): conta armada (\begin{array}) no enunciado.
 */
public class SubtracaoNaturalNivel3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a, b;
		if(rand.nextBoolean())
		{
			a = 100 + rand.nextInt(900);
			b = 100 + rand.nextInt(900);
		}
		else
		{
			a = 100 + rand.nextInt(900);
			b = 10 + rand.nextInt(90);
		}
		if(a < b)
		{
			int c = a;
			a = b;
			b = c;
		}

		addParagrafo("Calcule a seguinte subtração:");
		addParagrafo("\\(" + ResolucaoNatural.subtracao(a, b, false) + "\\)");
		gerarAlternativasInteiras(a - b);
		setResolucao("\\(" + ResolucaoNatural.subtracao(a, b, true) + "\\)");
	}
}
