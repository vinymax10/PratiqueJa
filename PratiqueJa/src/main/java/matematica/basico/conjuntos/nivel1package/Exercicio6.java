package matematica.basico.conjuntos.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.Conjunto;

public class Exercicio6 extends GeradorExercicio
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
		int correto = c.soma();

		addParagrafo("Qual a soma dos elementos da união \\(A \\cup B\\)?");
		addParagrafo("\\(A = " + a + "\\)");
		addParagrafo("\\(B = " + b + "\\)");
		gerarAlternativasInteiras(correto);

		String resolucao = "\\(A \\cup B = " + c + "\\)" +
			"\\(\\\\\\)" +
			"Soma dos elementos de \\(A \\cup B\\):";
		if(c.size() > 1)
			resolucao += "\\(\\\\\\)\\(" + c.somaStr() + " = " + correto + "\\)";
		else
			resolucao += " \\(" + correto + "\\)";
		setResolucao(resolucao);
	}
}
