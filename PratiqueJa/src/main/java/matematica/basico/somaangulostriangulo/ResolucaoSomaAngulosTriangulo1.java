package matematica.basico.somaangulostriangulo;


public class ResolucaoSomaAngulosTriangulo1
{

	public static String[] resolucaoSuplemento13(int a, int x, int c)
	{
		return new String[]
		{
			"180° = x + " + a + "° + 180° - " + c + " = x+ " + (a + 180 - c) + "°",
			"x= 180° - " + (a + 180 - c) + "°=" + x + "°"
		};
	}

	public static String[] resolucaoSuplemento(int a, int b, int x)
	{
		return new String[]
		{
			"180° = " + a + "° + " + b + "° + 180° - x = " + (a + b + 180) + "° - x",
			"x= - 180° + " + (a + b + 180) + "°=" + x + "°"
		};
	}

	public static String[] resolucao(int a, int b, int x)
	{
		return new String[]
		{
			"180° = " + a + "° + " + b + "° + x = " + (a + b) + "° + x",
			"x= 180° - " + (a + b) + "°=" + x + "°"
		};
	}

	public static String[] resolucaoReto(int a, int x)
	{
		return new String[]
		{
			"180° = " + a + "° + 90° + x = " + (a + 90) + "° + x",
			"x= 180° - " + (a + 90) + "°=" + x + "°"
		};
	}

	public static String[] resolucaoRetoSuplemento3(int a, int x)
	{
		return new String[]
		{
			"180° = x + 90° + 180° - " + a + "°= x + " + (270 - a) + "°",
			"x=  180° - " + (270 - a) + "°=" + x + "°"
		};
	}

	public static String[] resolucaoRetoSuplemento4(int a, int x)
	{
		return new String[]
		{
			"180° = " + a + "° + 90° + 180° - x = " + (a + 270) + "° - x",
			"x= - 180° + " + (a + 270) + "°=" + x + "°"
		};
	}

	public static String[] resolucaoRetoSuplemento5(int a, int x)
	{
		return new String[]
		{
			"180° = x + 2 (180° - " + a + ") = x + 2 \\cdot" + (180 - a) + "° = x + " + (2 * (180 - a)),
			"x= 180° - " + (2 * (180 - a)) + "°=" + x + "°"
		};
	}
}
