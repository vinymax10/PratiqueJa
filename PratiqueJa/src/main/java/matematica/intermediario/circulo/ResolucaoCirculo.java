package matematica.intermediario.circulo;

import matematica.ParCor;

public class ResolucaoCirculo
{
	public static String formulaComprimento()
	{
		return ParCor.formula("C = 2\\pi r");
	}

	public static String formulaComprimentoDiametro()
	{
		return ParCor.formula("C = \\pi d");
	}

	public static String formulaArea()
	{
		return ParCor.formula("A = \\pi r^2");
	}

	public static String formulaArco()
	{
		return ParCor.formula("\\ell = \\dfrac{\\theta}{360} \\cdot 2\\pi r");
	}

	public static String formulaAreaSetor()
	{
		return ParCor.formula("A_s = \\dfrac{\\theta}{360} \\cdot \\pi r^2");
	}

	public static String formulaAreaCoroa()
	{
		return ParCor.formula("A = \\pi \\left(R^2 - r^2\\right)");
	}
}
