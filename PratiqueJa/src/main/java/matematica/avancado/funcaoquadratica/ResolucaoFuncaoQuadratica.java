package matematica.avancado.funcaoquadratica;

import matematica.Auxiliar;
import matematica.ParCor;
import matematica.Racional;
import matematica.expressao.MyExpression;

public class ResolucaoFuncaoQuadratica
{
	public static String formulaXv()
	{
		return "\\(" + ParCor.formula("X_v=\\dfrac{-b}{2a}") + "\\)" + "\\(\\\\\\)";
	}

	public static String formulaYv()
	{
		return "\\(" + ParCor.formula("Y_v=\\dfrac{-\\Delta}{4a}, \\quad \\Delta = b^2-4ac") + "\\)" + "\\(\\\\\\)";
	}

	public static String resolucaoFx(int a, int b, int c, int x)
	{
		String s1 = "f(" + x + ")=" + a + "\\cdot " + x + "^2"
				+ Auxiliar.getNumber(b, "", false) + "\\cdot " + x
				+ Auxiliar.getNumber(c, "", false);

		String s2 = "f(" + x + ")=" + a + "\\cdot " + (x * x)
				+ Auxiliar.getNumber(b, "", false) + "\\cdot " + x
				+ Auxiliar.getNumber(c, "", false);

		int r1 = a * x * x;
		int r2 = b * x;
		String s3 = "f(" + x + ")=" + r1
				+ Auxiliar.getNumber(r2, "", false)
				+ Auxiliar.getNumber(c, "", false)
				+ "=\\mathbf{" + (r1 + r2 + c) + "}";

		return "Substituindo \\(x=" + x + "\\) na função: \\(\\\\\\)" +
			   "\\(" + s1 + " = \\\\ " +
			   "" + s2 + " = \\\\ " +
			   "" + s3 + "\\)";
	}

	public static String resolucaoXv(int a, int b, int c)
	{
		Racional resultado = new Racional(-b, 2 * a);
		resultado.fatoracao(2);

		String sub;
		if(b < 0)
			sub = "X_v=\\dfrac{-(" + b + ")}{2\\cdot " + a + "}=\\dfrac{" + (-b) + "}{" + (2 * a) + "}";
		else
			sub = "X_v=\\dfrac{" + (-b) + "}{2\\cdot " + a + "}=\\dfrac{" + (-b) + "}{" + (2 * a) + "}";

		String res = formulaXv();
		res += "\\(a=" + a + ", \\quad b=" + b + ", \\quad c=" + c + "\\\\" ;
		res += "" + sub;
		if(resultado.denominador != 1)
			res += "=" + resultado.showDfrac();
		res += "=\\mathbf{" + resultado.toStringLatex() + "}\\)";
		return res;
	}

	public static String resolucaoYv(int a, int b, int c)
	{
		int delta = b * b - 4 * a * c;
		Racional resultado = new Racional(-delta, 4 * a);
		resultado.fatoracao(2);

		String res = formulaYv();
		res += "\\(a=" + a + ", \\quad b=" + b + ", \\quad c=" + c + "\\\\" ;
		res += "\\Delta=" + b + "^2-4\\cdot " + a + "\\cdot " + c + " = \\\\ ";
		res += "\\Delta=" + (b * b) + Auxiliar.getNumber(-4 * a * c, "", false) + "=" + delta + "\\\\" ;
		res += "Y_v=\\dfrac{-(" + delta + ")}{4\\cdot " + a + "}=\\dfrac{" + (-delta) + "}{" + (4 * a) + "}";
		if(resultado.denominador != 1)
			res += "=" + resultado.showDfrac();
		res += "=\\mathbf{" + resultado.toStringLatex() + "}\\)";
		return res;
	}

	public static String resolucaoAcharA(int a, int b, int c, int x, int fx)
	{
		String sub = "f(" + x + ")=a\\cdot " + x + "^2"
				+ Auxiliar.getNumber(b, "", false) + "\\cdot " + x
				+ Auxiliar.getNumber(c, "", false) + "=" + fx;
		MyExpression expressao = new MyExpression("a*" + (x * x)
				+ Auxiliar.getNumber(b, "", false) + "*" + x
				+ Auxiliar.getNumber(c, "", false) + "=" + fx);

		return "Substituindo \\(f(" + x + ")=" + fx + "\\): \\(\\\\\\)" +
			   "\\(" + sub + "\\\\"  +
			   "" + expressao.resolverLatex() + "\\\\" +
			   "a=\\mathbf{" + a + "}\\)";
	}

	public static String resolucaoAcharB(int a, int b, int c, int x, int fx)
	{
		String sub = "f(" + x + ")=" + a + "\\cdot " + x + "^2+b\\cdot " + x
				+ Auxiliar.getNumber(c, "", false) + "=" + fx;
		MyExpression expressao = new MyExpression(a + "*" + (x * x) + "+b*" + x
				+ Auxiliar.getNumber(c, "", false) + "=" + fx);

		return "Substituindo \\(f(" + x + ")=" + fx + "\\): \\(\\\\\\)" +
			   "\\(" + sub + "\\\\" +
			   "" + expressao.resolverLatex() + "\\\\" +
			   "b=\\mathbf{" + b + "}\\)";
	}

	public static String resolucaoAcharC(int a, int b, int c, int x, int fx)
	{
		String sub = "f(" + x + ")=" + a + "\\cdot " + x + "^2"
				+ Auxiliar.getNumber(b, "", false) + "\\cdot " + x + "+c=" + fx;
		MyExpression expressao = new MyExpression(a + "*" + (x * x)
				+ Auxiliar.getNumber(b, "", false) + "*" + x + "+c=" + fx);

		return "Substituindo \\(f(" + x + ")=" + fx + "\\): \\(\\\\\\)" +
			   "\\(" + sub + "\\\\"  +
			   "" + expressao.resolverLatex() + "\\\\" +
			   "c=\\mathbf{" + c + "}\\)";
	}

	public static String resolucaoAcharB_Xv(int a, int b, int c, Racional xv)
	{
		String res = formulaXv();
		res += "\\(a=" + a + ", \\quad c=" + c + "\\\\" ;
		res += "" + xv.showFrac() + "=\\dfrac{-b}{2\\cdot " + a + "}=\\dfrac{-b}{" + (2 * a) + "}\\\\" ;
		MyExpression expressao = new MyExpression(xv.toString() + "*" + (2 * a) + "=-b");
		res += "" + expressao.resolverLatex() + "\\\\";
		res += "b=\\mathbf{" + b + "}\\)";
		return res;
	}

	public static String resolucaoAcharA_Xv(int a, int b, int c, Racional xv)
	{
		String res = formulaXv();
		res += "\\(b=" + b + ", \\quad c=" + c + "\\\\" ;
		if(b < 0)
			res += "" + xv.showFrac() + "=\\dfrac{-(" + b + ")}{2a}=\\dfrac{" + (-b) + "}{2a}\\\\" ;
		else
			res += "" + xv.showFrac() + "=\\dfrac{" + (-b) + "}{2a}\\\\";
		MyExpression expressao = new MyExpression(xv.toString() + "*2a=" + (-b));
		res += "" + expressao.resolverLatex() + "\\\\" ;
		res += "a=\\mathbf{" + a + "}\\)";
		return res;
	}

	public static String resolucaoAcharA_Yv(int a, int b, int c, Racional yv)
	{
		String res = formulaYv();
		res += "\\(b=" + b + ", \\quad c=" + c + "\\\\";
		res += "" + yv.showDfrac() + "=\\dfrac{-(" + b + "^2-4\\cdot a\\cdot " + c + ")}{4a}\\\\";
		res += "" + yv.showDfrac() + "=\\dfrac{-(" + (b * b) + Auxiliar.getNumber(-4 * c, "a", false) + ")}{4a}\\\\" ;
		MyExpression expressao = new MyExpression(yv.toString() + "*4a=" + (-(b * b)) + Auxiliar.getNumber(4 * c, "a", false));
		res += "" + expressao.resolverLatex() + "\\\\" ;
		res += "a=\\mathbf{" + a + "}\\)";
		return res;
	}

	public static String resolucaoAcharC_Yv(int a, int b, int c, Racional yv)
	{
		String res = formulaYv();
		res += "\\(a=" + a + ", \\quad b=" + b + "\\\\" ;
		res += "" + yv.showDfrac() + "=\\dfrac{-(" + b + "^2-4\\cdot " + a + "\\cdot c)}{4\\cdot " + a + "}\\\\";
		res += "" + yv.showDfrac() + "=\\dfrac{-(" + (b * b) + Auxiliar.getNumber(-4 * a, "c", false) + ")}{" + (4 * a) + "}\\\\" ;
		MyExpression expressao = new MyExpression(yv.toString() + "*" + (4 * a) + "=" + (-(b * b)) + Auxiliar.getNumber(4 * a, "c", false));
		res += "" + expressao.resolverLatex() + "\\\\" ;
		res += "c=\\mathbf{" + c + "}\\)";
		return res;
	}
}
