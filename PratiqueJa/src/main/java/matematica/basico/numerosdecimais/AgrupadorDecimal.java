package matematica.basico.numerosdecimais;

import java.util.ArrayList;
import java.util.List;

import matematica.GeradorExercicio;

public abstract class AgrupadorDecimal extends GeradorExercicio
{
	/** Formata n décimos como "X{,}Y" para LaTeX */
	protected String fmtT(int decimos)
	{
		return (decimos / 10) + "{,}" + (decimos % 10);
	}

	/** Formata n centésimos como "X{,}YZ" para LaTeX */
	protected String fmtH(int centesimos)
	{
		return (centesimos / 100) + "{,}" + String.format("%02d", centesimos % 100);
	}

	/** Formata n milésimos como "X{,}YZW" para LaTeX */
	protected String fmtM(int milesimos)
	{
		return (milesimos / 1000) + "{,}" + String.format("%03d", milesimos % 1000);
	}

	/**
	 * Resolução em conta vertical: op é "+" ou "-".
	 * Gera \(\begin{array}{r} a \\ op b \\ \hline result \end{array}\)
	 */
	protected String vertical(String op, String a, String b, String result)
	{
		return "\\(\\begin{array}{r}" +
			a + " \\\\" +
			op + "\\;" + b + " \\\\" +
			"\\hline " + result +
			"\\end{array}\\)";
	}

	/** Conta vertical com 3 parcelas (todos com sinal op nas 2ª e 3ª linhas) */
	protected String vertical3(String op, String a, String b, String c, String result)
	{
		return "\\(\\begin{array}{r}" +
			a + " \\\\" +
			op + "\\;" + b + " \\\\" +
			op + "\\;" + c + " \\\\" +
			"\\hline " + result +
			"\\end{array}\\)";
	}

	/** 4 alternativas embaralhadas para resultado em décimos */
	protected void gerarAltT(int correto)
	{
		String correctStr = "\\(" + fmtT(correto) + "\\)";
		List<String> distratores = new ArrayList<>();
		List<Integer> usados = new ArrayList<>();
		usados.add(correto);

		while(distratores.size() < 3)
		{
			int delta = 1 + rand.nextInt(4);
			int cand = rand.nextBoolean() ? correto + delta : correto - delta;
			if(cand > 0 && !usados.contains(cand))
			{
				usados.add(cand);
				distratores.add("\\(" + fmtT(cand) + "\\)");
			}
		}
		embaralharEAdicionarAlternativas(correctStr, distratores);
	}

	/** 4 alternativas embaralhadas para resultado em centésimos */
	protected void gerarAltH(int correto)
	{
		String correctStr = "\\(" + fmtH(correto) + "\\)";
		List<String> distratores = new ArrayList<>();
		List<Integer> usados = new ArrayList<>();
		usados.add(correto);

		while(distratores.size() < 3)
		{
			int delta = 1 + rand.nextInt(6);
			int cand = rand.nextBoolean() ? correto + delta : correto - delta;
			if(cand > 0 && !usados.contains(cand))
			{
				usados.add(cand);
				distratores.add("\\(" + fmtH(cand) + "\\)");
			}
		}
		embaralharEAdicionarAlternativas(correctStr, distratores);
	}
}
