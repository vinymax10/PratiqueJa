package matematica.basico.subtracaonatural;

import matematica.GeradorExercicio;

/**
 * Subtração de naturais (nível 1). Constrói por composição (não é entidade nem herda Exercicio).
 */
public class SubtracaoNaturalNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);
		if(a < b)
		{
			int c = a;
			a = b;
			b = c;
		}
		int correto = a - b;

		addParagrafo("Calcule o valor da seguinte subtração:");
		addParagrafo("\\(" + a + " - " + b + " = \\,?\\)");
		gerarAlternativasInteiras(correto);
		setResolucao("\\(" + a + " - " + b + " = " + correto + "\\)");
	}
}
