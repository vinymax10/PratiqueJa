package matematica.avancado.logaritmo;

import matematica.Racional;


public class ResolucaoLogaritmo
{

	public static String resolucao1(int numero, int base, int resultado)
	{
		String resolucaoLatex = "\\log_{" + base + "}" + numero + "=x\\\\";

		resolucaoLatex += "" + base + "^x =" + numero + "\\\\";
		resolucaoLatex += "" + base + "^x =" + "" + base + "^{" + resultado + "}" + "\\\\";
		resolucaoLatex += "x=" + resultado;

		return resolucaoLatex;
	}

	public static String resolucaoSoma(Racional a, int b, int c, int x)
	{
		String resolucaoLatex = "\\log_{" + c + "}\\left(" + a.showDfrac() + "\\right)+" + "\\log_{" + c + "}" + b + "=\\\\";
		resolucaoLatex += "\\log_{" + c + "}\\left(" + a.showDfrac() + "\\right)\\cdot" + b + "=\\\\";
		Racional resultado = a.mult(new Racional(b));
		if(resultado.denominador != 1)
		{
			resolucaoLatex += "\\log_{" + c + "}\\left(" + resultado.showDfrac() + "\\right)=";
			resultado.fatoracao(2);
		}
		if(resultado.denominador == 1)
			resolucaoLatex += resolucao1((int)resultado.numerador, c, x);

		return resolucaoLatex;
	}

	public static String resolucaoSubtracao(Racional a, int b, int c, int x)
	{
		String resolucaoLatex = "\\log_{" + c + "}" + a.numerador + "-" + "\\log_{" + c + "}" + b + "=\\\\";
		Racional resultado = a.div(new Racional(b));
		resolucaoLatex += "\\log_{" + c + "}\\left(" + resultado.showDfrac() + "\\right)=\\\\";
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
		{
			resolucaoLatex += resolucao1((int)resultado.numerador, c, x);
		}

		return resolucaoLatex;
	}

	public static String resolucao3A1(int b, int c, int d, Racional x)
	{
		int resultado = (int) Math.pow(c, d);
		String resolucaoLatex = b + " \\cdot \\log_{" + c + "}" + resultado + "=\\\\";
		resolucaoLatex += "\\log_{" + c + "}" + resultado + "^{" + b + "}=x\\\\";
		resolucaoLatex += "" + c + "^x =" + resultado + "^{" + b + "}\\\\";
		if(resultado != c)
			resolucaoLatex += "" + c + "^x =" + "" + c + "^{" + x + "}" + "\\\\";

		resolucaoLatex += "x=" + x;

		return resolucaoLatex;
	}

	public static String resolucao3(int a, int b, int c, int d, Racional x)
	{
		int resultado = (int) Math.pow(c, d);
		String resolucaoLatex = b + " \\cdot \\log_{" + c + "}\\sqrt[" + a + "]{" + resultado + "}"+ "=\\\\";
		resolucaoLatex += "\\log_{" + c + "}\\left(\\sqrt[" + a + "]{" + resultado + "}\\right)^{" + b + "}=\\\\";
		Racional racional = new Racional(b, a);
		resolucaoLatex += "\\log_{" + c + "}" + resultado + "^{" + racional.showFrac() + "}=";
		racional.fatoracao(2);
		if(racional.isSimplificou())
			resolucaoLatex += "\\log_{" + c + "}" + resultado + "^{" + racional.showFrac() + "}=x\\\\";
		else
			resolucaoLatex += "x\\\\";

		resolucaoLatex += "" + c + "^x =" + resultado + "^{" + racional.showFrac() + "}\\\\";
		if(resultado != c)
			resolucaoLatex += "" + c + "^x =" + "" + c + "^{" + x.showFrac() + "}" + "\\\\";

		resolucaoLatex += "x=" + x.showFrac();

		return resolucaoLatex;
	}
}