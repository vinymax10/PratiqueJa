package matematica.intermediario.poligonosregulares;

import matematica.ParCor;

public class ResolucaoPoligonoRegular
{
	public static String formulaSomaInternos()
	{
		return ParCor.formula("S_i = (n-2)\\times180^\\circ");
	}

	public static String formulaAnguloInterno()
	{
		return ParCor.formula("\\alpha_i = \\dfrac{(n-2)\\times180^\\circ}{n}");
	}

	public static String formulaAnguloExterno()
	{
		return ParCor.formula("\\alpha_e = \\dfrac{360^\\circ}{n}");
	}

	public static String formulaArea()
	{
		return ParCor.formula("A = \\dfrac{P \\cdot a}{2}");
	}

	public static String formulaPerimetro()
	{
		return ParCor.formula("P = n \\cdot l");
	}

	public static String formulaApotemaHexagono()
	{
		return ParCor.formula("a = \\dfrac{l\\sqrt{3}}{2}");
	}

	public static String formulaAreaHexagono()
	{
		return ParCor.formula("A = \\dfrac{3\\sqrt{3}}{2}\\,l^2");
	}
}
