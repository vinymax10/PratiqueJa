package matematica.avancado.estatistica;

import java.util.Arrays;

public class AuxEstatistica
{
	/** Lista para prosa: "4, 6, 7, 8, 10". */
	public static String listaStr(int[] v)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < v.length; i++)
		{
			sb.append(v[i]);
			if(i < v.length - 1)
				sb.append(", ");
		}
		return sb.toString();
	}

	/** Soma para math: "4 + 6 + 7 + 8 + 10". */
	public static String somaStr(int[] v)
	{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < v.length; i++)
		{
			sb.append(v[i]);
			if(i < v.length - 1)
				sb.append(" + ");
		}
		return sb.toString();
	}

	public static int somar(int[] v)
	{
		int s = 0;
		for(int x : v) s += x;
		return s;
	}

	public static int maximo(int[] v)
	{
		int m = v[0];
		for(int x : v) if(x > m) m = x;
		return m;
	}

	public static int minimo(int[] v)
	{
		int m = v[0];
		for(int x : v) if(x < m) m = x;
		return m;
	}

	public static int[] ordenar(int[] v)
	{
		int[] c = Arrays.copyOf(v, v.length);
		Arrays.sort(c);
		return c;
	}
}
