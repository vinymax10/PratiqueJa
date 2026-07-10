package matematica.basico.numerosdecimais.nivel3package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import matematica.basico.numerosdecimais.AgrupadorDecimal;

// Comparação: identificar o maior (ou menor) entre 4 decimais
public class Decimal5 extends AgrupadorDecimal
{
	@Override
	protected void construir()
	{
		int base = 2 + rand.nextInt(6);

		// 4 valores distintos em centésimos com mesma parte inteira
		Set<Integer> usadosSet = new HashSet<>();
		int[] vals = new int[4];
		for(int i = 0; i < 4; i++)
		{
			int v;
			do
			{
				v = base * 100 + 1 + rand.nextInt(99);
			}
			while(!usadosSet.add(v));
			vals[i] = v;
		}

		int[] sorted = vals.clone();
		Arrays.sort(sorted);

		boolean pedirMaior = rand.nextBoolean();
		int correto = pedirMaior ? sorted[3] : sorted[0];

		String enun = pedirMaior
			? "Qual é o maior número entre os quatro abaixo?"
			: "Qual é o menor número entre os quatro abaixo?";

		StringBuilder lista = new StringBuilder("\\(");
		for(int i = 0; i < 4; i++)
		{
			if(i > 0) lista.append(" \\quad ");
			lista.append(fmtH(vals[i]));
		}
		lista.append("\\)");

		addParagrafo(enun);
		addParagrafo(lista.toString());

		// alternativas: os 4 próprios valores
		String corretaStr = "\\(" + fmtH(correto) + "\\)";
		List<String> distratores = new ArrayList<>();
		for(int v : vals)
			if(v != correto)
				distratores.add("\\(" + fmtH(v) + "\\)");

		embaralharEAdicionarAlternativas(corretaStr, distratores);

		// resolução: mostrar ordenação crescente
		addResolucao("Ordenando crescente:");
		addResolucao("\\(" + fmtH(sorted[0]) + " < " + fmtH(sorted[1]) +
			" < " + fmtH(sorted[2]) + " < " + fmtH(sorted[3]) + "\\)");
		addResolucao((pedirMaior ? "Maior" : "Menor") + ": \\(\\mathbf{" + fmtH(correto) + "}\\)");
	}
}
