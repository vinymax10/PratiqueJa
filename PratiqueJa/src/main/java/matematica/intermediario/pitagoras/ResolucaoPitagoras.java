package matematica.intermediario.pitagoras;

import matematica.ParCor;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.NoPitagoras;

public class ResolucaoPitagoras
{

	public static String[] resolucaoXBC(Dados dados)
	{
		NoPitagoras a=dados.hipotenusa;
		NoPitagoras b=dados.base;
		NoPitagoras c=dados.altura;

		return new String[]
		{
			formula(),
			"b=" + b.show() + ",\\quad c=" + c.show(),
			"a^2 =" + b.showEleQuad() + "+" + c.showEleQuad(),
			"a^2 =" + b.showQuad() + "+" + c.showQuad() + " = " + (b.quad() + c.quad()),
			"a = \\sqrt{" + (b.quad() + c.quad()) + "}" + " = \\mathbf{" + a.show() + "}"
		};
	}

	public static String[] resolucaoAXC(Dados dados)
	{
		NoPitagoras a=dados.hipotenusa;
		NoPitagoras b=dados.base;
		NoPitagoras c=dados.altura;

		return new String[]
		{
			formula(),
			"a=" + a.show() + ",\\quad c=" + c.show(),
			"b^2+" + c.showEleQuad() + " = "+a.showEleQuad(),
			"b^2 =" + a.showQuad() + "-" + c.showQuad() + " = " + (a.quad() - c.quad()),
			"b = \\sqrt{" + (a.quad() - c.quad()) + "}" + " = \\mathbf{" + b.show() + "}"
		};
	}

	public static String[] resolucaoABX(Dados dados)
	{
		NoPitagoras a=dados.hipotenusa;
		NoPitagoras b=dados.base;
		NoPitagoras c=dados.altura;

		return new String[]
		{
			formula(),
			"a=" + a.show() + ",\\quad b=" + b.show(),
			b.showEleQuad() + " + c^2 =" + a.showEleQuad(),
			"c^2 =" + a.showQuad() + "-" + b.showQuad() + " = " + (a.quad() - b.quad()),
			"c = \\sqrt{" + (a.quad() - b.quad()) + "}" + " = \\mathbf{" + c.show() + "}"
		};
	}
	
	public static String formula()
	{
		return ParCor.formula("a^2=b^2 + c^2");
	}
	
	public static String formulaDistancia()
	{
		return ParCor.formula("d= \\sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}");
	}

	public static String formulaMetricaAltura()
	{
		return ParCor.formula("h^2 = m \\cdot n");
	}

}