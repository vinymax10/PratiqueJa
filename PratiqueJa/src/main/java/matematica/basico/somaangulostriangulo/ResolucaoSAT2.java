package matematica.basico.somaangulostriangulo;


public class ResolucaoSAT2
{

	public static String[] complemento180(String x, int a, int b)
	{
		return new String[]
		{
			x + "+" + a + "° +" + b + "° = 180°",
			x + "= 180° -" + a + "° - " + b + "°=\\mathbf{" + (180 - a - b) + "°}"
		};
	}

	public static String[] complemento180(String x, int a, int b, int c)
	{
		return new String[]
		{
			x + "+" + a + "° +" + b + "° +" + c + "° = 180°",
			x + "= 180° -" + a + "° - " + b + "° - " + c + "°=\\mathbf{" + (180 - a - b - c) + "°}"
		};
	}

	public static String[] complemento180(String x, int a)
	{
		return new String[]
		{
			x + "+" + a + "° = 180°",
			x + "= 180° -" + a + "° =\\mathbf{" + (180 - a) + "°}"
		};
	}

	public static String[] complemento90(String x, int a, int b)
	{
		return new String[]
		{
			x + "+" + a + "° +" + b + "° = 90°",
			x + "= 90° -" + a + "° - " + b + "°=\\mathbf{" + (90 - a - b) + "°}"
		};
	}

	public static String[] complemento90(String x, int a)
	{
		return new String[]
		{
			x + "+" + a + "° = 90°",
			x + "= 90° -" + a + "° =\\mathbf{" + (90 - a) + "°}"
		};
	}

	/** Envolve em \mathbf{} o valor após o último "=" do último passo de uma
	 *  resolução vinda de {@code MyExpression.resolverLatex()} (passos separados
	 *  por "\\"), destacando o resultado final sem alterar os passos anteriores. */
	public static String boldLastResult(String resolucaoLatex)
	{
		int lastSeparador = resolucaoLatex.lastIndexOf("\\\\");
		String ultimoPasso = (lastSeparador >= 0) ? resolucaoLatex.substring(lastSeparador + 2).trim() : resolucaoLatex.trim();
		int lastIgual = ultimoPasso.lastIndexOf('=');
		if(lastIgual >= 0)
		{
			String boldado = ultimoPasso.substring(0, lastIgual + 1) + "\\mathbf{" + ultimoPasso.substring(lastIgual + 1).trim() + "}";
			return (lastSeparador >= 0) ? resolucaoLatex.substring(0, lastSeparador + 2) + boldado : boldado;
		}
		return resolucaoLatex;
	}
}
