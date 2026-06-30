package matematica.basico.numerosprimos;

import java.util.LinkedHashMap;
import java.util.Map;

import matematica.GeradorExercicio;

public abstract class AgrupadorPrimo extends GeradorExercicio
{
	protected static final int[] LISTA_PRIMOS = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

	protected boolean ehPrimo(int n)
	{
		if(n < 2) return false;
		for(int p : LISTA_PRIMOS)
		{
			if(p * p > n) break;
			if(n % p == 0) return false;
		}
		return true;
	}

	protected Map<Integer, Integer> fatorar(int n)
	{
		Map<Integer, Integer> fatores = new LinkedHashMap<>();
		for(int p : LISTA_PRIMOS)
		{
			if(p * p > n) break;
			while(n % p == 0)
			{
				fatores.merge(p, 1, Integer::sum);
				n /= p;
			}
		}
		if(n > 1) fatores.merge(n, 1, Integer::sum);
		return fatores;
	}

	protected int mdc(int a, int b)
	{
		return b == 0 ? a : mdc(b, a % b);
	}

	protected int numDivisores(Map<Integer, Integer> fatores)
	{
		int d = 1;
		for(int e : fatores.values()) d *= (e + 1);
		return d;
	}

	protected String fatorLatex(Map<Integer, Integer> fatores)
	{
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<Integer, Integer> e : fatores.entrySet())
		{
			if(sb.length() > 0) sb.append(" \\times ");
			sb.append(e.getKey());
			if(e.getValue() > 1) sb.append("^").append(e.getValue());
		}
		return sb.toString();
	}

	protected String[] resolucaoFatoracao(int original)
	{
		Map<Integer, Integer> fatores = fatorar(original);
		StringBuilder array = new StringBuilder("\\(\\begin{array}[t]{r|l}");
		int n = original;
		for(int p : LISTA_PRIMOS)
		{
			if(p * p > n) break;
			while(n % p == 0)
			{
				array.append(n).append(" & ").append(p).append("\\\\");
				n /= p;
			}
		}
		if(n > 1)
			array.append(n).append(" & ").append(n).append("\\\\");
		array.append("\\hline 1 & \\\\\\end{array}\\)");
		String equacao = "\\(\\mathbf{" + original + " = " + fatorLatex(fatores) + "}\\)";
		return new String[]{array.toString(), equacao};
	}
}
