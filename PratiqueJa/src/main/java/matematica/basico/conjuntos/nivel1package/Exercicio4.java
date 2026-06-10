package matematica.basico.conjuntos.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.Conjunto;

public class Exercicio4 extends GeradorExercicio
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
		int correto = c.maior();

		addParagrafo("Qual o maior elemento da união \\(A \\cup B\\)?");
		addParagrafo("\\(A = " + a + "\\)");
		addParagrafo("\\(B = " + b + "\\)");
		gerarAlternativasInteiras(correto);

		setResolucao(
			"\\(A \\cup B = " + c + "\\)" +
			"\\(\\\\\\)" +
			"Maior elemento de \\(A \\cup B\\) é \\(" + correto + "\\)"
		);
	}
}
