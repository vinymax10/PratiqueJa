package Matematica.Intermediario.SomaAngulosTriangulo;


public class ResolucaoSAT2
{

	public static String complemento180(String x, int a, int b)
	{
		String resolucaoLatex = x + "+" + a + "° +" + b + "° = 180° \\\\";
		resolucaoLatex += x + "= 180° -" + a + "° - " + b + "°=" + (180 - a - b) + "°\\\\";

		return resolucaoLatex;
	}

	public static String complemento180(String x, int a, int b, int c)
	{
		String resolucaoLatex = x + "+" + a + "° +" + b + "° +" + c + "° = 180° \\\\";
		resolucaoLatex += x + "= 180° -" + a + "° - " + b + "° - " + c + "°=" + (180 - a - b - c) + "°\\\\";

		return resolucaoLatex;
	}

	public static String complemento180(String x, int a)
	{
		String resolucaoLatex = x + "+" + a + "° = 180° \\\\";
		resolucaoLatex += x + "= 180° -" + a + "° =" + (180 - a) + "°\\\\";

		return resolucaoLatex;
	}

	public static String complemento90(String x, int a, int b)
	{
		String resolucaoLatex = x + "+" + a + "° +" + b + "° = 90° \\\\";
		resolucaoLatex += x + "= 90° -" + a + "° - " + b + "°=" + (90 - a - b) + "°\\\\";

		return resolucaoLatex;
	}

	public static String complemento90(String x, int a)
	{
		String resolucaoLatex = x + "+" + a + "° = 90° \\\\";
		resolucaoLatex += x + "= 90° -" + a + "° =" + (90 - a) + "°\\\\";

		return resolucaoLatex;
	}

}