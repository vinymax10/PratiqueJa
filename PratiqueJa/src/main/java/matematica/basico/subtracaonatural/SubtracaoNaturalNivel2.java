package matematica.basico.subtracaonatural;

import matematica.GeradorExercicio;
import matematica.basico.resolucaonatural.ResolucaoNatural;

/**
 * Subtração de naturais (nível 2): conta armada (\begin{array}) no enunciado.
 */
public class SubtracaoNaturalNivel2 extends GeradorExercicio
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
