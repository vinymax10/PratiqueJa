package matematica.avancado.estatistica.nivel2package;

import java.util.ArrayList;
import java.util.List;
import matematica.GeradorExercicio;

// Frequência relativa: frᵢ = (fᵢ / n) × 100%
// Usa n ∈ {20, 25, 50} para garantir percentuais inteiros
public class Estatistica8 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		int[] nOpcoes = {20, 25, 50};
		int n = nOpcoes[rand.nextInt(nOpcoes.length)];

		int numCat = 4 + rand.nextInt(2);  // 4 ou 5 categorias
		int[] fi = gerarFrequencias(n, numCat);
		int alvo = rand.nextInt(numCat);

		// percentual = fi[alvo]*100/n — inteiro garantido pois n ∈ {20,25,50}
		int percentual = fi[alvo] * 100 / n;

		String[][] cenarios = {
			{"notas obtidas em uma prova (de 1 a " + numCat + ")", "nota"},
			{"número de produtos defeituosos por lote (" + numCat + " lotes)", "lote"},
			{"idades (em décadas) dos participantes de uma pesquisa", "faixa"},
			{"resultados de " + n + " lançamentos de um dado", "face"},
		};
		String[] cen = cenarios[rand.nextInt(cenarios.length)];

		StringBuilder distrib = new StringBuilder();
		for (int i = 0; i < numCat; i++)
		{
			distrib.append(i + 1).append(" → ").append(fi[i]);
			if (i < numCat - 1) distrib.append(", ");
		}

		addParagrafo("Uma pesquisa registrou " + n + " observações de " + cen[0]
				+ ". A distribuição de frequências absolutas é: " + distrib + "."
				+ " Qual é a frequência relativa da " + cen[1] + " " + (alvo + 1)
				+ ", expressa em porcentagem?");

		// Distratores
		String sCorreta = percentual + "%";
		int d1Num = fi[alvo];       // frequência absoluta sem conversão
		int d2Num = 100 - percentual;  // complemento
		if (d2Num == d1Num) d2Num = d2Num > 50 ? d2Num - 5 : d2Num + 5;
		int d3Num = fi[(alvo + 1) % numCat] * 100 / n;  // categoria vizinha
		// garantir que d3 ≠ correta, d1, d2
		if (d3Num == percentual || d3Num == d1Num || d3Num == d2Num)
			d3Num = percentual > 20 ? percentual - 5 : percentual + 5;

		List<String> distratores = new ArrayList<>();
		distratores.add(d1Num + "%");
		distratores.add(d2Num + "%");
		distratores.add(d3Num + "%");
		embaralharEAdicionarAlternativas(sCorreta, distratores);

		String res = "A frequência relativa é a proporção de cada categoria em relação ao total:\\(\\\\\\)";
		res += "\\(fr_i = \\dfrac{f_i}{n} \\times 100\\%\\)\\(\\\\\\)";
		res += "Para a " + cen[1] + " " + (alvo + 1) + ": \\(f_i = " + fi[alvo]
				+ "\\), \\(n = " + n + "\\)\\(\\\\\\)";
		res += "\\(fr = \\dfrac{" + fi[alvo] + "}{" + n + "} \\times 100\\% = \\mathbf{"
				+ percentual + "\\%}\\)";
		setResolucao(res);
	}

	private int[] gerarFrequencias(int n, int numCat)
	{
		int[] fi = new int[numCat];
		int restante = n;
		for (int i = 0; i < numCat - 1; i++)
		{
			int max = restante - (numCat - i - 1);
			fi[i] = 1 + rand.nextInt(max);
			restante -= fi[i];
		}
		fi[numCat - 1] = restante;
		return fi;
	}
}
