package matematica.basico.conjuntos.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.conjuntos.Conjunto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercicio15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int sizeA = 4 + rand.nextInt(4); // 4..7 elementos
		Conjunto a = new Conjunto(sizeA, 50);

		int qtdDentro = 2 + rand.nextInt(2); // 2 ou 3
		List<Integer> candidatos = new ArrayList<>();

		// pegar qtdDentro elementos distintos de A
		int tentativas = 0;
		while (candidatos.size() < qtdDentro && tentativas++ < 100)
		{
			int e = a.getAle();
			if (!candidatos.contains(e))
				candidatos.add(e);
		}
		qtdDentro = candidatos.size();

		// completar com elementos fora de A
		while (candidatos.size() < 5)
		{
			int c = rand.nextInt(100);
			if (!a.contem(c) && !candidatos.contains(c))
				candidatos.add(c);
		}
		Collections.shuffle(candidatos, rand);

		String listaCandidatos = buildLista(candidatos);
		int correto = qtdDentro;

		addParagrafo("Quantos dos elementos do conjunto \\(C\\) pertencem ao conjunto \\(A\\)?");
		addParagrafo("\\(A = " + a + "\\)");
		addParagrafo("\\(C = " + listaCandidatos + "\\)");
		gerarAlternativasInteiras(correto);

		StringBuilder res = new StringBuilder();
		res.append("Verificamos cada elemento de \\(C\\): \\(\\\\\\)");
		for (int e : candidatos)
		{
			if (a.contem(e))
				res.append("\\(" + e + " \\in A\\) \\(\\\\\\)");
			else
				res.append("\\(" + e + " \\notin A\\) \\(\\\\\\)");
		}
		res.append(correto + " elemento" + (correto != 1 ? "s pertencem" : " pertence") + " a \\(A\\).");
		setResolucao(res.toString());
	}

	private String buildLista(List<Integer> elementos)
	{
		StringBuilder sb = new StringBuilder("\\{");
		for (int i = 0; i < elementos.size(); i++)
		{
			sb.append(elementos.get(i));
			if (i < elementos.size() - 1)
				sb.append(",\\,");
		}
		sb.append("\\}");
		return sb.toString();
	}
}
