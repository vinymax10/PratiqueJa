package matematica.basico.somaangulostriangulo;


public class ResolucaoSAT2
{

	public static String[] complemento180(String x, int a, int b)
	{
		return new String[]
		{
			x + "+" + a + "° +" + b + "° = 180°",
			x + "= 180° -" + a + "° - " + b + "°=" + (180 - a - b) + "°"
		};
	}

	public static String[] complemento180(String x, int a, int b, int c)
	{
		return new String[]
		{
			x + "+" + a + "° +" + b + "° +" + c + "° = 180°",
			x + "= 180° -" + a + "° - " + b + "° - " + c + "°=" + (180 - a - b - c) + "°"
		};
	}

	public static String[] complemento180(String x, int a)
	{
		return new String[]
		{
			x + "+" + a + "° = 180°",
			x + "= 180° -" + a + "° =" + (180 - a) + "°"
		};
	}

	public static String[] complemento90(String x, int a, int b)
	{
		return new String[]
		{
			x + "+" + a + "° +" + b + "° = 90°",
			x + "= 90° -" + a + "° - " + b + "°=" + (90 - a - b) + "°"
		};
	}

	public static String[] complemento90(String x, int a)
	{
		return new String[]
		{
			x + "+" + a + "° = 90°",
			x + "= 90° -" + a + "° =" + (90 - a) + "°"
		};
	}

}
