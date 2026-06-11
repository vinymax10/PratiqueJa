package matematica.avancado.polinomios;

import matematica.Auxiliar;

public class Polinomio
{
	public static String termo(int coef, int exp, boolean first)
	{
		if (coef == 0) return "";
		String lit = exp == 0 ? "" : exp == 1 ? "x" : "x^" + exp;
		return Auxiliar.getNumber(coef, lit, first);
	}

	// coefs[0] = leading coefficient (highest degree), descending order
	public static String formatar(int... coefs)
	{
		StringBuilder sb = new StringBuilder();
		int grau = coefs.length - 1;
		for (int i = 0; i <= grau; i++)
		{
			if (coefs[i] == 0) continue;
			sb.append(termo(coefs[i], grau - i, sb.length() == 0));
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}
}
