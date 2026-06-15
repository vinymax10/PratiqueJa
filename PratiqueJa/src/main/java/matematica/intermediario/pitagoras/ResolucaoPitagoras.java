package matematica.intermediario.pitagoras;

import matematica.ParCor;
import matematica.intermediario.pitagoras.dados.Dados;
import matematica.intermediario.pitagoras.dados.NoPitagoras;

public class ResolucaoPitagoras
{

	public static String resolucaoXBC(Dados dados)
	{
		NoPitagoras a=dados.hipotenusa;
		NoPitagoras b=dados.base;
		NoPitagoras c=dados.altura;
		
		String resolucaoLatex = formula()+"\\\\";
		resolucaoLatex += "b=" + b.show() + ",\\quad c=" + c.show() + "\\\\";
		resolucaoLatex += "a^2 =" + b.showEleQuad() + "+" + c.showEleQuad() + "\\\\";
		resolucaoLatex += "a^2 =" + b.showQuad() + "+" + c.showQuad() + " = " + (b.quad() + c.quad()) + " \\\\";
		resolucaoLatex += "a = \\sqrt{" + (b.quad() + c.quad()) + "}" + " = " + a.show();
		
		return resolucaoLatex;
	}
	
	public static String resolucaoAXC(Dados dados)
	{
		NoPitagoras a=dados.hipotenusa;
		NoPitagoras b=dados.base;
		NoPitagoras c=dados.altura;
		
		String resolucaoLatex = formula()+"\\\\";
		resolucaoLatex += "a=" + a.show() + ",\\quad c=" + c.show() + "\\\\";
		resolucaoLatex +=  "b^2+" + c.showEleQuad() + " = "+a.showEleQuad()+" \\\\";
		resolucaoLatex += "b^2 =" + a.showQuad() + "-" + c.showQuad() + " = " + (a.quad() - c.quad()) + " \\\\";
		resolucaoLatex += "b = \\sqrt{" + (a.quad() - c.quad()) + "}" + " = " + b.show();
		
		return resolucaoLatex;
	}
	
	public static String resolucaoABX(Dados dados)
	{
		NoPitagoras a=dados.hipotenusa;
		NoPitagoras b=dados.base;
		NoPitagoras c=dados.altura;
		
		String resolucaoLatex = formula()+"\\\\";
		resolucaoLatex += "a=" + a.show() + ",\\quad b=" + b.show() + "\\\\";
		resolucaoLatex += b.showEleQuad() + " + c^2 =" + a.showEleQuad()+"\\\\";
		resolucaoLatex += "c^2 =" + a.showQuad() + "-" + b.showQuad() + " = " + (a.quad() - b.quad()) + " \\\\";
		resolucaoLatex += "c = \\sqrt{" + (a.quad() - b.quad()) + "}" + " = " + c.show();
		
		return resolucaoLatex;
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