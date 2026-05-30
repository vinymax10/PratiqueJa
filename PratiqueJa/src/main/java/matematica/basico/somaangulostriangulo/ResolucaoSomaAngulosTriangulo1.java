package matematica.basico.somaangulostriangulo;


public class ResolucaoSomaAngulosTriangulo1
{

	public static String resolucaoSuplemento13(int a, int x, int c)
	{
		String resolucaoLatex = "180° = x + " + a + "° + 180° - " + c + " = x+ " + (a + 180 - c) + "° \\\\";
		resolucaoLatex += "x= 180° - " + (a + 180 - c) + "°=" + x + "°\\\\";
		return resolucaoLatex;
	}

	public static String resolucaoSuplemento(int a, int b, int x)
	{
		String resolucaoLatex = "180° = " + a + "° + " + b + "° + 180° - x = " + (a + b + 180) + "° - x\\\\";
		resolucaoLatex += "x= - 180° + " + (a + b + 180) + "°=" + x + "°\\\\";
		return resolucaoLatex;
	}

	public static String resolucao(int a, int b, int x)
	{
		String resolucaoLatex = "180° = " + a + "° + " + b + "° + x = " + (a + b) + "° + x\\\\";
		resolucaoLatex += "x= 180° - " + (a + b) + "°=" + x + "°\\\\";
		return resolucaoLatex;
	}

	public static String resolucaoReto(int a, int x)
	{
		String resolucaoLatex = "180° = " + a + "° + 90° + x = " + (a + 90) + "° + x\\\\";
		resolucaoLatex += "x= 180° - " + (a + 90) + "°=" + x + "°\\\\";
		return resolucaoLatex;
	}

	public static String resolucaoRetoSuplemento3(int a, int x)
	{
		String resolucaoLatex = "180° = x + 90° + 180° - " + a + "°= x + " + (270 - a) + "°\\\\";
		resolucaoLatex += "x=  180° - " + (270 - a) + "°=" + x + "°\\\\";
		return resolucaoLatex;
	}

	public static String resolucaoRetoSuplemento4(int a, int x)
	{
		String resolucaoLatex = "180° = " + a + "° + 90° + 180° - x = " + (a + 270) + "° - x\\\\";

		resolucaoLatex += "x= - 180° + " + (a + 270) + "°=" + x + "°\\\\";
		return resolucaoLatex;
	}

	public static String resolucaoRetoSuplemento5(int a, int x)
	{
		String resolucaoLatex = "180° = x + 2 (180° - " + a + ") = x + 2 \\cdot" + (180 - a) + "° = x + " + (2 * (180 - a)) + "\\\\";

		resolucaoLatex += "x= 180° - " + (2 * (180 - a)) + "°=" + x + "°\\\\";
		return resolucaoLatex;
	}
}