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

}
