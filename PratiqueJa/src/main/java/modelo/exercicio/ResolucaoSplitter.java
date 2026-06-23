package modelo.exercicio;

import java.util.ArrayList;
import java.util.List;

/**
 * Converte uma resolução em formato de String (legado: linhas separadas por "\\",
 * com matemática inline "\(...\)") em uma lista de linhas robusta, pronta para virar
 * {@link ParagrafoResolucao}.
 *
 * <p>O problema crônico era "\\" (quebra de linha) DENTRO de "\(...\)" — inválido:
 * o modo matemático quebra no 1º "\\" e o resto vira texto. Aqui isso é resolvido em
 * duas etapas:
 * <ol>
 *   <li>{@link #normalizar(String)}: move cada "\\" que está dentro de "\(...\)" (e
 *       fora de matriz/array) para o modo texto, fechando/reabrindo o math
 *       ("\(a\\b\)" → "\(a\)\\\(b\)"). O "\\" interno de bmatrix/array é preservado.</li>
 *   <li>{@link #split(String)}: quebra nas barras "\\" de nível superior (fora de
 *       "\(...\)"), gerando uma linha por passo.</li>
 * </ol>
 */
public final class ResolucaoSplitter
{
	private ResolucaoSplitter() {}

	/** Fatia a resolução (String legada) em linhas; cada linha vira um parágrafo. */
	public static List<String> split(String resolucao)
	{
		List<String> linhas = new ArrayList<>();
		if(resolucao == null || resolucao.isBlank())
			return linhas;

		String normalizado = normalizar(resolucao);

		int mathDepth = 0, inicio = 0, i = 0, n = normalizado.length();
		while(i < n)
		{
			if(normalizado.startsWith("\\(", i))
			{
				mathDepth++; i += 2;
			}
			else if(normalizado.startsWith("\\)", i))
			{
				if(mathDepth > 0) mathDepth--;
				i += 2;
			}
			else if(mathDepth == 0 && normalizado.startsWith("\\\\", i))
			{
				adicionarLinha(linhas, normalizado.substring(inicio, i));
				i += 2; inicio = i;
			}
			else
			{
				i++;
			}
		}
		adicionarLinha(linhas, normalizado.substring(inicio));
		return linhas;
	}

	private static void adicionarLinha(List<String> linhas, String linha)
	{
		String t = linha.trim();
		// remove segmentos de math vazios que sobram (ex.: de "\(\\\)")
		t = t.replaceAll("\\\\\\(\\s*\\\\\\)", "").trim();
		if(!t.isEmpty())
			linhas.add(t);
	}

	/**
	 * Move todo "\\" que está dentro de "\(...\)" (e fora de matriz/array) para o modo
	 * texto, fechando e reabrindo o math. Preserva o "\\" interno de bmatrix/array.
	 */
	public static String normalizar(String resolucao)
	{
		StringBuilder out = new StringBuilder();
		boolean inMath = false;
		int matrizDepth = 0;
		int i = 0, n = resolucao.length();
		while(i < n)
		{
			if(!inMath && resolucao.startsWith("\\(", i))
			{
				inMath = true; out.append("\\("); i += 2;
			}
			else if(inMath && matrizDepth == 0 && resolucao.startsWith("\\)", i))
			{
				inMath = false; out.append("\\)"); i += 2;
			}
			else if(inMath && resolucao.startsWith("\\begin{", i))
			{
				matrizDepth++; out.append("\\begin{"); i += 7;
			}
			else if(inMath && matrizDepth > 0 && resolucao.startsWith("\\end{", i))
			{
				matrizDepth--; out.append("\\end{"); i += 5;
			}
			else if(inMath && matrizDepth == 0 && resolucao.startsWith("\\\\", i))
			{
				out.append("\\)\\\\\\("); i += 2; // fecha math, quebra em texto, reabre math
			}
			else
			{
				out.append(resolucao.charAt(i)); i++;
			}
		}
		return out.toString();
	}
}
