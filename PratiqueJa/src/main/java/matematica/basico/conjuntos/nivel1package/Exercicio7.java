package matematica.basico.conjuntos.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.Conjunto;

public class Exercicio7 extends GeradorExercicio
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

		Conjunto c = a.menos(b);
		int correto = c.maior();

		addParagrafo("Qual o maior elemento de \\(A - B\\)?");
		addParagrafo("\\(A = " + a + "\\)");
		addParagrafo("\\(B = " + b + "\\)");
		gerarAlternativasInteiras(correto);

		addResolucao("\\(A - B = " + c + "\\)");
		addResolucao("Maior elemento de \\(A - B\\) é \\(" + correto + "\\)");
	}
}
