package matematica.basico.divisibilidade;

public class ResolucaoDivisibilidade
{
	public static String resolucao2(boolean correta, int number)
	{
		if(correta)
			return "O número \\(" + number + "\\) é par, por isso é divisível por 2.";
		else
			return "O número \\(" + number + "\\) não é par, por isso não é divisível por 2.";
	}

	public static String resolucao10(boolean correta, int number)
	{
		if(correta)
			return "O número \\(" + number + "\\) termina em 0, por isso é divisível por 10.";
		else
			return "O número \\(" + number + "\\) não termina em 0, por isso não é divisível por 10.";
	}

	public static String resolucao5(boolean correta, int number)
	{
		if(correta)
			return "O número \\(" + number + "\\) termina em 0 ou 5, por isso é divisível por 5.";
		else
			return "O número \\(" + number + "\\) não termina em 0 ou 5, por isso não é divisível por 5.";
	}

	public static String resolucao3(boolean correta, int number)
	{
		String numberStr = String.valueOf(number);
		int somaAlgarismos = 0;
		String somaAlgarismosStr = "";
		for(int i = 0; i < numberStr.length(); i++)
		{
			somaAlgarismos += Integer.valueOf("" + numberStr.charAt(i));
			somaAlgarismosStr += "" + numberStr.charAt(i);
			if(i < numberStr.length() - 1)
				somaAlgarismosStr += "+";
		}

		String res = "A soma dos algarismos é \\(" + somaAlgarismosStr + " = " + somaAlgarismos + "\\). ";
		res += "\\(\\\\\\) ";
		if(correta)
			res += "Como \\(" + somaAlgarismos + "\\) é múltiplo de 3, \\(" + numberStr + "\\) é divisível por 3.";
		else
			res += "Como \\(" + somaAlgarismos + "\\) não é múltiplo de 3, \\(" + numberStr + "\\) não é divisível por 3.";
		return res;
	}

	public static String resolucao9(boolean correta, int number)
	{
		String numberStr = String.valueOf(number);
		int somaAlgarismos = 0;
		String somaAlgarismosStr = "";
		for(int i = 0; i < numberStr.length(); i++)
		{
			somaAlgarismos += Integer.valueOf("" + numberStr.charAt(i));
			somaAlgarismosStr += "" + numberStr.charAt(i);
			if(i < numberStr.length() - 1)
				somaAlgarismosStr += "+";
		}

		String res = "A soma dos algarismos é \\(" + somaAlgarismosStr + " = " + somaAlgarismos + "\\). ";
		res += "\\(\\\\\\) ";
		if(correta)
			res += "Como \\(" + somaAlgarismos + "\\) é múltiplo de 9, \\(" + numberStr + "\\) é divisível por 9.";
		else
			res += "Como \\(" + somaAlgarismos + "\\) não é múltiplo de 9, \\(" + numberStr + "\\) não é divisível por 9.";
		return res;
	}

	public static String resolucao6(boolean correta, int number)
	{
		String numberStr = String.valueOf(number);
		int somaAlgarismos = 0;
		for(int i = 0; i < numberStr.length(); i++)
			somaAlgarismos += Integer.valueOf("" + numberStr.charAt(i));

		boolean divisivel2 = (number % 2) == 0;

		if(correta)
		{
			String res = "O número \\(" + number + "\\) é divisível por 2 e a soma dos seus algarismos é \\("
					+ somaAlgarismos + "\\), que é múltiplo de 3. ";
			res += "\\(\\\\\\) ";
			res += "Portanto, \\(" + numberStr + "\\) é divisível por 6.";
			return res;
		}
		else
		{
			if(!divisivel2)
				return "O número \\(" + number + "\\) não é par, por isso não é divisível por 6.";
			else
			{
				String res = "O número \\(" + number + "\\) é divisível por 2, mas a soma dos seus algarismos é \\("
						+ somaAlgarismos + "\\), que não é múltiplo de 3. ";
				res += "\\(\\\\\\) ";
				res += "Portanto, \\(" + numberStr + "\\) não é divisível por 6.";
				return res;
			}
		}
	}

	public static String resolucao4(boolean correta, int number)
	{
		String numberStr = String.valueOf(number);
		boolean maior2Digitos = numberStr.length() >= 2;
		int valor = 0;
		if(maior2Digitos)
			valor = Integer.valueOf("" + numberStr.charAt(numberStr.length()-2) + numberStr.charAt(numberStr.length()-1));

		if(correta)
		{
			if(maior2Digitos)
			{
				String res = "Os dois últimos algarismos de \\(" + number + "\\) formam \\(" + valor + "\\), que é múltiplo de 4. ";
				res += "\\(\\\\\\) ";
				res += "Portanto, \\(" + numberStr + "\\) é divisível por 4.";
				return res;
			}
			else
				return "O número \\(" + number + "\\) é múltiplo de 4.";
		}
		else
		{
			if(maior2Digitos)
			{
				String res = "Os dois últimos algarismos de \\(" + number + "\\) formam \\(" + valor + "\\), que não é múltiplo de 4. ";
				res += "\\(\\\\\\) ";
				res += "Portanto, \\(" + numberStr + "\\) não é divisível por 4.";
				return res;
			}
			else
				return "O número \\(" + number + "\\) não é múltiplo de 4.";
		}
	}

	public static String resolucao8(boolean correta, int number)
	{
		String numberStr = String.valueOf(number);
		boolean maior3Digitos = numberStr.length() >= 3;
		int valor = 0;
		if(maior3Digitos)
			valor = Integer.valueOf("" +
				numberStr.charAt(numberStr.length()-3) +
				numberStr.charAt(numberStr.length()-2) +
				numberStr.charAt(numberStr.length()-1));

		if(correta)
		{
			if(maior3Digitos)
			{
				String res = "Os três últimos algarismos de \\(" + number + "\\) formam \\(" + valor + "\\), que é múltiplo de 8. ";
				res += "\\(\\\\\\) ";
				res += "Portanto, \\(" + numberStr + "\\) é divisível por 8.";
				return res;
			}
			else
				return "O número \\(" + number + "\\) é múltiplo de 8.";
		}
		else
		{
			if(maior3Digitos)
			{
				String res = "Os três últimos algarismos de \\(" + number + "\\) formam \\(" + valor + "\\), que não é múltiplo de 8. ";
				res += "\\(\\\\\\) ";
				res += "Portanto, \\(" + numberStr + "\\) não é divisível por 8.";
				return res;
			}
			else
				return "O número \\(" + number + "\\) não é múltiplo de 8.";
		}
	}

	public static String resolucao7(boolean correta, int number)
	{
		String res = "";
		String resultadoStr = String.valueOf(number);
		int ultimoDigito;
		int sobraDigito;
		int resultado = number;
		do
		{
			resultadoStr = String.valueOf(resultado);
			ultimoDigito = Integer.valueOf("" + resultadoStr.charAt(resultadoStr.length()-1));
			sobraDigito = Integer.valueOf("" + resultadoStr.substring(0, resultadoStr.length()-1));

			res += "O último dígito de \\(" + resultado + "\\) é \\(" + ultimoDigito + "\\). ";
			res += "\\(\\\\\\) ";
			resultado = sobraDigito - (ultimoDigito * 2);
			res += "\\(" + ultimoDigito + " \\cdot 2 = " + (ultimoDigito * 2)
				+ " \\rightarrow " + sobraDigito + " - " + (ultimoDigito * 2) + " = " + resultado + "\\) ";
			res += "\\(\\\\\\) ";
		}
		while(String.valueOf(resultado).length() > 2);

		if(correta)
		{
			res += "O número \\(" + resultado + "\\) é múltiplo de 7. ";
			res += "\\(\\\\\\) ";
			res += "Portanto, \\(" + number + "\\) é divisível por 7.";
		}
		else
		{
			res += "O número \\(" + resultado + "\\) não é múltiplo de 7. ";
			res += "\\(\\\\\\) ";
			res += "Portanto, \\(" + number + "\\) não é divisível por 7.";
		}
		return res;
	}
}
