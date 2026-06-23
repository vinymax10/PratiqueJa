package matematica.basico.conjuntos.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.Conjunto;

public class Exercicio13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int sizeA = 3 + rand.nextInt(6); // 3..8 elementos
		Conjunto a = new Conjunto(sizeA, 30);
		int correto = a.size();

		addParagrafo("Qual é a cardinalidade do conjunto \\(A\\)?");
		addParagrafo("\\(A = " + a + "\\)");
		gerarAlternativasInteiras(correto);

		addResolucao("A cardinalidade \\(|A|\\) é o número de elementos distintos do conjunto.");
		addResolucao("Contando os elementos de \\(A = " + a + "\\):");
		addResolucao("\\(|A| = " + correto + "\\)");
	}
}
