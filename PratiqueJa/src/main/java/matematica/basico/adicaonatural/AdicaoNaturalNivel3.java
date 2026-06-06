package matematica.basico.adicaonatural;

import matematica.GeradorExercicio;
import matematica.basico.resolucaonatural.ResolucaoNatural;

/**
 * Adição de naturais (nível 3): uma parcela de 3 dígitos com outra de 2 ou 3
 * dígitos, apresentada como conta armada (\begin{array}) no enunciado.
 * Constrói por composição (não é entidade nem herda Exercicio).
 */
public class AdicaoNaturalNivel3 extends GeradorExercicio
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

		// Enunciado: conta armada em LaTeX (\( \) para o MathJax).
		addParagrafo("Calcule a seguinte adição:");
		addParagrafo("\\(" + ResolucaoNatural.soma(a, b, false) + "\\)");

		// Alternativas: correta + distratores numéricos plausíveis.
		gerarAlternativasInteiras(a + b);

		// Resolução com o "vai um" passo a passo (expressão crua).
		setResolucao("\\(" +ResolucaoNatural.soma(a, b, true)+ "\\)");
	}
}
