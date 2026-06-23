package matematica.basico.multiplicacaonatural.nivel1package;

import matematica.GeradorExercicio;

public class Sequencia extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int n = 2 + rand.nextInt(9);
		int len = 4 + rand.nextInt(3);
		int missingPos = 1 + rand.nextInt(len - 2);
		int missingValue = (missingPos + 1) * n;
		int prev = missingPos * n;

		StringBuilder seq = new StringBuilder("\\(");
		for (int i = 0; i < len; i++)
		{
			if (i > 0) seq.append(", ");
			if (i == missingPos) seq.append("\\square");
			else seq.append((i + 1) * n);
		}
		seq.append("\\)");

		addParagrafo("Qual é o número que completa a sequência de multiplicação?");
		addParagrafo(seq.toString());

		gerarAlternativasInteiras(missingValue);

		addResolucao("A sequência é a tabuada do \\(" + n
			+ "\\): cada termo aumenta \\(" + n + "\\) em relação ao anterior.");
		addResolucao("\\(" + prev + " + " + n + " = \\mathbf{" + missingValue + "}\\)");
	}
}
