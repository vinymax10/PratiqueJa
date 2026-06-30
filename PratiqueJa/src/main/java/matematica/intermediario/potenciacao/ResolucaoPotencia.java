package matematica.intermediario.potenciacao;

import matematica.Racional;


public class ResolucaoPotencia
{

	public static String strFatores(int a, int p)
	{
		String resolucaoLatex = "";
		if(p > 1)
		{
			for(int i = 0; i < p - 1; i++)
				resolucaoLatex += "" + a + "\\cdot";

			resolucaoLatex += "" + a;
		}
		return resolucaoLatex;
	}
	
	public static String resolucao(int a, int p)
	{
		String resolucaoLatex = "" + a + "^{" + p + "}" + "=";
		if(p>1)
			resolucaoLatex+=strFatores(a,p)+"=";
		
		resolucaoLatex += "\\mathbf{" + Integer.valueOf((int) Math.pow(a, p)) + "}";
		return resolucaoLatex;
	}
	
	public static String resolucaoNegativo(int a, int p)
	{
		String resolucaoLatex = "" + a + "^{" + p + "}" + "=";

		if(p > 1)
		{
			resolucaoLatex += "-(";
			for(int i = 0; i < p - 1; i++)
				resolucaoLatex += ""+ Math.abs(a) + "\\cdot";

			resolucaoLatex += "" + Math.abs(a) + ")=";
		}
		resolucaoLatex += "\\mathbf{" + Integer.valueOf((int) -Math.pow(Math.abs(a), p)) + "}";

		return resolucaoLatex;
	}

	public static String resolucaoFracao(int a, int b, int p)
	{
		String resolucaoLatex = "\\left(\\dfrac{" + a + "}" + "{" + b + "}\\right)^{" + p + "}" + "=";

		if(p > 1)
		{
			resolucaoLatex += "\\dfrac{";
			for(int i = 0; i < p - 1; i++)
				resolucaoLatex += "" + a + "\\cdot";

			resolucaoLatex += "" + a + "}{";

			for(int i = 0; i < p - 1; i++)
				resolucaoLatex += "" + b + "\\cdot";

			resolucaoLatex += "" + b + "}=";
		}

		int numerador = (int) Math.pow(a, p);
		int denominador = (int) Math.pow(b, p);
		Racional racional = new Racional(numerador, denominador);
		String showFracAntes = racional.showDfrac();
		racional.fatoracao(2);
		if(racional.isSimplificou())
			resolucaoLatex += showFracAntes + "=\\mathbf{" + racional.showDfrac() + "}";
		else
			resolucaoLatex += "\\mathbf{" + showFracAntes + "}";

		return resolucaoLatex;
	}

	public static String resolucaoPotenciaNegativa(int a, int p)
	{
		String resolucaoLatex = "" + a + "^{-" + p + "}=";

		resolucaoLatex += resolucaoFracao(1, a, p);

		return resolucaoLatex;
	}
	
	public static String resolucaoPotenciaNegativaBaseNegativa(int a, int p)
	{
		String resolucaoLatex = "" + a + "^{-" + p + "}=";

		resolucaoLatex += resolucaoFracaoBaseNegativa(1, Math.abs(a), p);

		return resolucaoLatex;
	}

	public static String resolucaoFracaoBaseNegativa(int a, int b, int p)
	{
		String resolucaoLatex = "-\\left(\\dfrac{" + a + "}" + "{" + b + "}\\right)^{" + p + "}" + "=";

		if(p > 1)
		{
			resolucaoLatex += "-\\left(\\dfrac{";
			for(int i = 0; i < p - 1; i++)
				resolucaoLatex += "" + a + "\\cdot";

			resolucaoLatex += "" + a + "}{";

			for(int i = 0; i < p - 1; i++)
				resolucaoLatex += "" + b + "\\cdot";

			resolucaoLatex += "" + b + "}\\right)=";
		}

		int numerador = (int) Math.pow(a, p);
		int denominador = (int) Math.pow(b, p);
		Racional racional = new Racional(numerador, denominador);
		resolucaoLatex += "-"+racional.showDfrac();
		racional.fatoracao(2);
		if(racional.isSimplificou())
			resolucaoLatex += "=-" + racional.showDfrac();

		return resolucaoLatex;
	}

	public static String resolucaoPotenciaNegativa(int a, int b, int p)
	{
		String resolucaoLatex = "\\left(\\dfrac{" + a + "}" + "{" + b + "}\\right)^{-" + p + "}=";

		resolucaoLatex += resolucaoFracao(b, a, p);

		return resolucaoLatex;
	}
}