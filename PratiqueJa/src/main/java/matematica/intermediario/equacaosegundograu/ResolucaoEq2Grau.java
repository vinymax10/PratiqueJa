package matematica.intermediario.equacaosegundograu;

import matematica.Auxiliar;
import matematica.ParCor;
import matematica.Racional;
import matematica.expressao.MyExpression;

public class ResolucaoEq2Grau
{

	public static String formula()
	{
		return ParCor.formula("x=\\dfrac{-b \\pm \\sqrt{\\Delta}}{2a}, \\quad \\Delta = b^2-4ac")+"\\\\";
	}

	public static String formulaXv()
	{
		return ParCor.formula("X_v=\\dfrac{-b}{2a}")+"\\\\";
	}

	public static String formulaYv()
	{
		return ParCor.formula("Y_v=\\dfrac{-\\Delta}{4a}, \\quad \\Delta = b^2-4ac")+"\\\\";
	}

	private static String bSq(int b)
	{
		return (b < 0 ? "(" + b + ")" : "" + b) + "^2";
	}

	public static String resolucao(int a, int b, int c, int x)
	{
		String resolucaoLatex = "";

		resolucaoLatex += "f(" + x + ")=" + a + " \\cdot " + x + "^2" + Auxiliar.getNumber(b, "", false) + " \\cdot "
		+ x + Auxiliar.getNumber(c, "", false) + "\\\\";
		resolucaoLatex += "f(" + x + ")=" + a + " \\cdot " + (x * x) + Auxiliar.getNumber(b, "", false) + " \\cdot "
		+ x + Auxiliar.getNumber(c, "", false) + "\\\\";
		resolucaoLatex += "f(" + x + ")=" + (a * (x * x)) + Auxiliar.getNumber(b * x, "", false)
		+ Auxiliar.getNumber(c, "", false);
		resolucaoLatex += "=" + (a * (x * x) + (b * x) + c);

		return resolucaoLatex;
	}

	public static String sinal(int num)
	{
		if(num > 0)
			return "+";

		return "";
	}

	public static String resolucaoAX1(int a, int b, int c, int x1)
	{
		String resolucaoLatex = "";

		resolucaoLatex += "f(" + x1 + ")=a \\cdot " + x1 + "^2" + Auxiliar.getNumber(b, "", false) + " \\cdot " + x1
		+ Auxiliar.getNumber(c, "", false) + " = 0" + "\\\\";
		MyExpression expressao = new MyExpression(
		"a*" + (x1 * x1) + Auxiliar.getNumber(b, "", false) + "*" + x1 + Auxiliar.getNumber(c, "", false) + " = 0");
		resolucaoLatex += expressao.resolverLatex();

		return resolucaoLatex;
	}

	public static String resolucaoAXv(int a, int b, int c, Racional xv)
	{
		String resolucaoLatex = formulaXv();
		resolucaoLatex += "b=" + b + ",~c=" + c + "\\\\";

		if(b < 0)
			resolucaoLatex += xv.showDfrac() + "=\\dfrac{-(" + b + ") }{2a}\\\\";
		else
			resolucaoLatex += xv.showDfrac() + "=\\dfrac{" + (-b) + " }{2a}\\\\";

		MyExpression expressao = new MyExpression(xv.toString() + "* 2a=" + -b);
		resolucaoLatex += expressao.resolverLatex();

		return resolucaoLatex;
	}

	public static String resolucaoAYv(int a, int b, int c, Racional yv)
	{
		String resolucaoLatex = formulaYv();
		resolucaoLatex += "b=" + b + ",~c=" + c + "\\\\";

		resolucaoLatex += yv.showDfrac() + "=\\dfrac{-(" + bSq(b) + " -4 \\cdot a \\cdot " + c + ") }{4a}";
		resolucaoLatex += "=\\dfrac{-(" + (b * b) + " " + Auxiliar.getNumber((-4 * c), "a", false) + ") }{4a}\\\\";

		MyExpression expressao = new MyExpression(
		yv.toString() + "*4a=" + -(b * b) + "" + Auxiliar.getNumber(-(-4 * c), "a", false));
		resolucaoLatex += expressao.resolverLatex();

		return resolucaoLatex;
	}

	public static String resolucaoCYv(int a, int b, int c, Racional yv)
	{
		String resolucaoLatex = formulaYv();
		resolucaoLatex += "a=" + a + ",~b=" + b + "\\\\";

		resolucaoLatex += "y_v=\\dfrac{-(b^2-4ac)}{4a}\\\\";
		resolucaoLatex += yv.showDfrac() + "=\\dfrac{-(" + bSq(b) + " -4 \\cdot " + a + " \\cdot c) }{4 \\cdot " + a
		+ "}";
		resolucaoLatex += "=\\dfrac{-(" + (b * b) + " " + Auxiliar.getNumber((-4 * a), "c", false) + ") }{" + (4 * a)
		+ "}\\\\";

		MyExpression expressao = new MyExpression(
		yv.toString() + "*" + (4 * a) + "=" + -(b * b) + "" + Auxiliar.getNumber(-(-4 * a), "c", false));
		resolucaoLatex += expressao.resolverLatex();

		return resolucaoLatex;
	}

	public static String resolucaoBX1(int a, int b, int c, int x1)
	{
		String resolucaoLatex = "";

		resolucaoLatex += "f(" + x1 + ")=" + a + " \\cdot " + x1 + "^2 +b \\cdot " + x1
		+ Auxiliar.getNumber(c, "", false) + " = 0" + "\\\\";
		MyExpression expressao = new MyExpression(
		a + "*" + (x1 * x1) + " +b * " + x1 + Auxiliar.getNumber(c, "", false) + " = 0");
		resolucaoLatex += expressao.resolverLatex();

		return resolucaoLatex;
	}

	public static String resolucaoBXv(int a, int b, int c, Racional xv)
	{
		String resolucaoLatex = formulaXv();
		resolucaoLatex += "a=" + a + ",~c=" + c + "\\\\";

		resolucaoLatex += xv.showFrac() + "=\\dfrac{-b }{2 \\cdot " + a + "}";
		resolucaoLatex += "=\\dfrac{-b }{" + (2 * a) + "}\\\\";
		MyExpression expressao = new MyExpression(xv.toString() + "*" + (2 * a) + "=-b");
		resolucaoLatex += expressao.resolverLatex();

		return resolucaoLatex;
	}

	public static String resolucaoC(int a, int b, int c, int x1)
	{
		String resolucaoLatex = "";

		resolucaoLatex += "f(" + x1 + ")=" + a + " \\cdot " + x1 + "^2" + Auxiliar.getNumber(b, "", false) + "\\cdot "
		+ x1 + "+c = 0" + "\\\\";
		MyExpression expressao = new MyExpression(
		a + "*" + (x1 * x1) + Auxiliar.getNumber(b, "", false) + "*" + x1 + "+c = 0");
		resolucaoLatex += expressao.resolverLatex();

		return resolucaoLatex;
	}

	public static String resolucaoX1(int a, int b, int c)
	{
		String resolucaoLatex = "";
		resolucaoLatex = formula();
		resolucaoLatex += "a=" + a + ",~b=" + b + ",~c=" + c + "\\\\";
		int delta = (int) (b * b) + (-4 * a * c);
		int raiz = (int) Math.sqrt(delta);

		resolucaoLatex += "\\Delta=" + bSq(b) + "-4 \\cdot " + a + " \\cdot " + c + "\\\\" + " \\Delta= " + (b * b)
		+ Auxiliar.getNumber((-4 * a * c), "", false) + "=" + ((b * b) + (-4 * a * c)) + "\\\\";

		if(b < 0)
			resolucaoLatex += "x_1=\\dfrac{-(" + b + ") + \\sqrt{" + delta + "}}{2 \\cdot " + a + "}";
		else
			resolucaoLatex += "x_1=\\dfrac{" + (-b) + " + \\sqrt{" + delta + "}}{2 \\cdot " + a + "}";

		resolucaoLatex += "=\\dfrac{" + (-b) + " + " + raiz + "}{" + (2 * a) + "}" + "\\\\";

		Racional resultado = new Racional((-b) + raiz, (2 * a));
		resolucaoLatex += "x_1=" + resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucaoLatex += "=" + resultado.showDfrac();

		return resolucaoLatex;
	}

	public static String resolucaoX2(int a, int b, int c)
	{
		String resolucaoLatex = "";
		resolucaoLatex = formula();
		resolucaoLatex += "a=" + a + ",~b=" + b + ",~c=" + c + "\\\\";
		int delta = (int) (b * b) + (-4 * a * c);
		int raiz = (int) Math.sqrt(delta);

		resolucaoLatex += "\\Delta=" + bSq(b) + "-4 \\cdot " + a + " \\cdot " + c + "\\\\" + " \\Delta= " + (b * b)
		+ Auxiliar.getNumber((-4 * a * c), "", false) + "=" + ((b * b) + (-4 * a * c)) + "\\\\";

		if(b < 0)
			resolucaoLatex += "x_2=\\dfrac{-(" + (b) + ") - \\sqrt{" + delta + "}}{2 \\cdot " + a + "}";
		else
			resolucaoLatex += "x_2=\\dfrac{" + (-b) + " - \\sqrt{" + delta + "}}{2 \\cdot " + a + "}";

		resolucaoLatex += "=\\dfrac{" + (-b) + " - " + raiz + "}{" + (2 * a) + "}" + "\\\\";
		;

		Racional resultado = new Racional((-b) - raiz, (2 * a));
		resolucaoLatex += "x_2=" + resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucaoLatex += "=" + resultado.showDfrac();

		return resolucaoLatex;
	}

	public static String resolucaoXVertice(int a, int b, int c)
	{
		String resolucaoLatex = "";
		resolucaoLatex = formulaXv();
		resolucaoLatex += "a=" + a + ",~b=" + b + ",~c=" + c + "\\\\";
		resolucaoLatex += "X_v";
		if(b < 0)
			resolucaoLatex += "=\\dfrac{-(" + b + ")}{2 \\cdot " + a + "}";
		else
			resolucaoLatex += "=\\dfrac{" + (-b) + "}{2 \\cdot " + a + "}";

		Racional resultado = new Racional((-b), (2 * a));
		resolucaoLatex += "=" + resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucaoLatex += "=" + resultado.showDfrac();

		return resolucaoLatex;
	}

	public static String resolucaoYVertice(int a, int b, int c)
	{
		String resolucaoLatex = "";
		resolucaoLatex = formulaYv();
		resolucaoLatex += "a=" + a + ",~b=" + b + ",~c=" + c + "\\\\";
		int delta = (int) (b * b) + (-4 * a * c);
		resolucaoLatex += "\\Delta=" + bSq(b) + "-4 \\cdot " + a + " \\cdot " + c + "\\\\" + " \\Delta= " + (b * b)
		+ Auxiliar.getNumber((-4 * a * c), "", false) + "=" + ((b * b) + (-4 * a * c)) + "\\\\";

		resolucaoLatex += "Y_v=\\dfrac{" + (-delta) + "}{4 \\cdot " + a + "}";

		Racional resultado = new Racional((-delta), (4 * a));
		resolucaoLatex += "=" + resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucaoLatex += "=" + resultado.showDfrac();

		return resolucaoLatex;
	}
}