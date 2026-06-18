package matematica.basico.multiplicacaonatural.nivel2package;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.GeradorExercicio;

public class ComparaSemCalcular extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[][] opts = new int[4][2];
		int[] produtos = new int[4];
		Set<Integer> prodUsados = new HashSet<>();

		for (int i = 0; i < 4; i++)
		{
			int prod;
			do
			{
				opts[i][0] = 25 + rand.nextInt(51);
				opts[i][1] = 25 + rand.nextInt(51);
				prod = opts[i][0] * opts[i][1];
			}
			while (!prodUsados.add(prod));
			produtos[i] = prod;
		}

		int maxIdx = 0;
		for (int i = 1; i < 4; i++)
			if (produtos[i] > produtos[maxIdx]) maxIdx = i;

		int a = opts[maxIdx][0], b = opts[maxIdx][1];
		int maiorProduto = produtos[maxIdx];

		String correta = "\\(" + a + " \\times " + b + "\\)";
		List<String> distrais = new ArrayList<>();
		for (int i = 0; i < 4; i++)
			if (i != maxIdx)
				distrais.add("\\(" + opts[i][0] + " \\times " + opts[i][1] + "\\)");

		addParagrafo("Sem calcular exatamente, qual das multiplicações " + listarOpcoes(correta, distrais) + " resulta no maior produto?");
		embaralharEAdicionarAlternativas(correta, distrais);

		String res = "Calculando cada produto: \\(\\\\\\)";
		res += "\\(";
		for (int i = 0; i < 4; i++)
		{
			if (i > 0) res += " \\\\ ";
			res += opts[i][0] + " \\times " + opts[i][1] + " = " + produtos[i];
		}
		res += "\\) \\(\\\\\\)";
		res += "O maior é \\(\\mathbf{" + a + " \\times " + b + " = " + maiorProduto + "}\\)";
		setResolucao(res);
	}
}
