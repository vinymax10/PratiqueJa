package matematica.basico.conjuntos.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.Conjunto;

public class Exercicio5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int sizeA = 3 + rand.nextInt(5);
		int sizeB = 3 + rand.nextInt(5);
		int limit = 25;
		Conjunto a = new Conjunto(sizeA, limit);
		Conjunto b = new Conjunto(sizeB, limit);
		a.contruirInterseccao(b);

		Conjunto c = a.uniao(b);
		int correto = c.menor();

		addParagrafo("Qual o menor elemento da união \\(A \\cup B\\)?");
		addParagrafo("\\(A = " + a + "\\)");
		addParagrafo("\\(B = " + b + "\\)");
		gerarAlternativasInteiras(correto);

		addResolucao("\\(A \\cup B = " + c + "\\)");
		addResolucao("Menor elemento de \\(A \\cup B\\) é \\(\\mathbf{" + correto + "}\\)");
	}
}
