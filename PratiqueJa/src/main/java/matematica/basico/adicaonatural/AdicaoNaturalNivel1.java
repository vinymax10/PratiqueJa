package matematica.basico.adicaonatural;

import matematica.GeradorExercicio;

/**
 * Gera uma adição de naturais (nível 1) no formato flexível de Exercicio:
 * enunciado em parágrafos + alternativas de múltipla escolha. Constrói por
 * composição (não é entidade nem herda Exercicio).
 */
public class AdicaoNaturalNivel1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int a = 1 + rand.nextInt(10);
		int b = 1 + rand.nextInt(10);
		int correto = a + b;

		// Enunciado em parágrafos (LaTeX inline em \( \), renderizado pelo MathJax).
		addParagrafo("Calcule o valor da seguinte adição:");
		addParagrafo("\\(" + a + " + " + b + " = \\,?\\)");

		// Alternativas: resposta correta + distratores numéricos plausíveis.
		gerarAlternativasInteiras(correto);

		// Resolução (expressão crua, como no padrão anterior).
		setResolucao("\\(" +a + " + " + b + " = " + correto+ "\\)");
	}
}
