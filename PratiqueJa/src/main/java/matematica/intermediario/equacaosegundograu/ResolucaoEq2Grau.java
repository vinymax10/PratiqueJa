package matematica.intermediario.equacaosegundograu;

import java.util.ArrayList;
import java.util.List;

import matematica.Auxiliar;
import matematica.ParCor;
import matematica.Racional;
import matematica.expressao.MyExpression;

public class ResolucaoEq2Grau
{

	public static String formula()
	{
		return ParCor.formula("x=\\dfrac{-b \\pm \\sqrt{\\Delta}}{2a}, \\quad \\Delta = b^2-4ac");
	}

	public static String formulaXv()
	{
		return ParCor.formula("X_v=\\dfrac{-b}{2a}");
	}

	public static String formulaYv()
	{
		return ParCor.formula("Y_v=\\dfrac{-\\Delta}{4a}, \\quad \\Delta = b^2-4ac");
	}

	private static String bSq(int b)
	{
		return (b < 0 ? "(" + b + ")" : "" + b) + "^2";
	}

	public static String resolucao(int a, int b, int c, int x)
	{
		String resolucaoLatex = "";

		// Sem termo linear (b=0) não deve aparecer "\cdot x" solto.
		String termoB = (b != 0) ? Auxiliar.getNumber(b, "", false) + " \\cdot " + x : "";
		resolucaoLatex += "f(" + x + ")=" + a + " \\cdot " + x + "^2" + termoB
		+ Auxiliar.getNumber(c, "", false) + "\\\\";
		resolucaoLatex += "f(" + x + ")=" + a + " \\cdot " + (x * x) + termoB
		+ Auxiliar.getNumber(c, "", false) + "\\\\";
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

	public static String[] resolucaoAX1(int a, int b, int c, int x1)
	{
		List<String> passos = new ArrayList<>();

		// Sem termo linear (b=0), omitir o "\cdot x" também na expressão resolvida,
		// senão a equação fica a*x^2*x (errada) e a raiz calculada sai incorreta.
		String termoBDisp = (b != 0) ? Auxiliar.getNumber(b, "", false) + " \\cdot " + x1 : "";
		String termoBExpr = (b != 0) ? Auxiliar.getNumber(b, "", false) + "*" + x1 : "";
		passos.add("\\(f(" + x1 + ")=a \\cdot " + x1 + "^2" + termoBDisp
		+ Auxiliar.getNumber(c, "", false) + " = 0\\)");
		MyExpression expressao = new MyExpression(
		"a*" + (x1 * x1) + termoBExpr + Auxiliar.getNumber(c, "", false) + " = 0");
		passos.add("\\(" + expressao.resolverLatex() + "\\)");

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoAXv(int a, int b, int c, Racional xv)
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(" + formulaXv() + "\\)");
		passos.add("\\(b=" + b + ",~c=" + c + "\\)");

		if(b < 0)
			passos.add("\\(" + xv.showDfrac() + "=\\dfrac{-(" + b + ") }{2a}\\)");
		else
			passos.add("\\(" + xv.showDfrac() + "=\\dfrac{" + (-b) + " }{2a}\\)");

		MyExpression expressao = new MyExpression(xv.toString() + "* 2a=" + -b);
		passos.add("\\(" + expressao.resolverLatex() + "\\)");

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoAYv(int a, int b, int c, Racional yv)
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(" + formulaYv() + "\\)");
		passos.add("\\(b=" + b + ",~c=" + c + "\\)");

		passos.add("\\(" + yv.showDfrac() + "=\\dfrac{-(" + bSq(b) + " -4 \\cdot a \\cdot " + c + ") }{4a}"
		+ "=\\dfrac{-(" + (b * b) + " " + Auxiliar.getNumber((-4 * c), "a", false) + ") }{4a}\\)");

		MyExpression expressao = new MyExpression(
		yv.toString() + "*4a=" + -(b * b) + "" + Auxiliar.getNumber(-(-4 * c), "a", false));
		passos.add("\\(" + expressao.resolverLatex() + "\\)");

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoCYv(int a, int b, int c, Racional yv)
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(" + formulaYv() + "\\)");
		passos.add("\\(a=" + a + ",~b=" + b + "\\)");

		passos.add("\\(y_v=\\dfrac{-(b^2-4ac)}{4a}\\)");
		passos.add("\\(" + yv.showDfrac() + "=\\dfrac{-(" + bSq(b) + " -4 \\cdot " + a + " \\cdot c) }{4 \\cdot " + a
		+ "}" + "=\\dfrac{-(" + (b * b) + " " + Auxiliar.getNumber((-4 * a), "c", false) + ") }{" + (4 * a)
		+ "}\\)");

		MyExpression expressao = new MyExpression(
		yv.toString() + "*" + (4 * a) + "=" + -(b * b) + "" + Auxiliar.getNumber(-(-4 * a), "c", false));
		passos.add("\\(" + expressao.resolverLatex() + "\\)");

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoBX1(int a, int b, int c, int x1)
	{
		List<String> passos = new ArrayList<>();

		passos.add("\\(f(" + x1 + ")=" + a + " \\cdot " + x1 + "^2 +b \\cdot " + x1
		+ Auxiliar.getNumber(c, "", false) + " = 0\\)");
		MyExpression expressao = new MyExpression(
		a + "*" + (x1 * x1) + " +b * " + x1 + Auxiliar.getNumber(c, "", false) + " = 0");
		passos.add("\\(" + expressao.resolverLatex() + "\\)");

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoBXv(int a, int b, int c, Racional xv)
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(" + formulaXv() + "\\)");
		passos.add("\\(a=" + a + ",~c=" + c + "\\)");

		passos.add("\\(" + xv.showFrac() + "=\\dfrac{-b }{2 \\cdot " + a + "}"
		+ "=\\dfrac{-b }{" + (2 * a) + "}\\)");
		MyExpression expressao = new MyExpression(xv.toString() + "*" + (2 * a) + "=-b");
		passos.add("\\(" + expressao.resolverLatex() + "\\)");

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoC(int a, int b, int c, int x1)
	{
		List<String> passos = new ArrayList<>();

		// Sem termo linear (b=0), omitir o "\cdot x" na exibição e na expressão.
		String termoBDisp = (b != 0) ? Auxiliar.getNumber(b, "", false) + "\\cdot " + x1 : "";
		String termoBExpr = (b != 0) ? Auxiliar.getNumber(b, "", false) + "*" + x1 : "";
		passos.add("\\(f(" + x1 + ")=" + a + " \\cdot " + x1 + "^2" + termoBDisp
		+ "+c = 0\\)");
		MyExpression expressao = new MyExpression(
		a + "*" + (x1 * x1) + termoBExpr + "+c = 0");
		passos.add("\\(" + expressao.resolverLatex() + "\\)");

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoX1(int a, int b, int c)
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(" + formula() + "\\)");
		passos.add("\\(a=" + a + ",~b=" + b + ",~c=" + c + "\\)");
		int delta = (int) (b * b) + (-4 * a * c);
		int raiz = (int) Math.sqrt(delta);

		passos.add("\\(\\Delta=" + bSq(b) + "-4 \\cdot " + a + " \\cdot " + c + "\\)");
		passos.add("\\( \\Delta= " + (b * b)
		+ Auxiliar.getNumber((-4 * a * c), "", false) + "=" + ((b * b) + (-4 * a * c)) + "\\)");

		String passoFracao;
		if(b < 0)
			passoFracao = "\\(x_1=\\dfrac{-(" + b + ") + \\sqrt{" + delta + "}}{2 \\cdot " + a + "}";
		else
			passoFracao = "\\(x_1=\\dfrac{" + (-b) + " + \\sqrt{" + delta + "}}{2 \\cdot " + a + "}";

		passoFracao += "=\\dfrac{" + (-b) + " + " + raiz + "}{" + (2 * a) + "}\\)";
		passos.add(passoFracao);

		Racional resultado = new Racional((-b) + raiz, (2 * a));
		String passoResultado = "\\(x_1=" + resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			passoResultado += "=" + resultado.showDfrac();
		passoResultado += "\\)";
		passos.add(passoResultado);

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoX2(int a, int b, int c)
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(" + formula() + "\\)");
		passos.add("\\(a=" + a + ",~b=" + b + ",~c=" + c + "\\)");
		int delta = (int) (b * b) + (-4 * a * c);
		int raiz = (int) Math.sqrt(delta);

		passos.add("\\(\\Delta=" + bSq(b) + "-4 \\cdot " + a + " \\cdot " + c + "\\)");
		passos.add("\\( \\Delta= " + (b * b)
		+ Auxiliar.getNumber((-4 * a * c), "", false) + "=" + ((b * b) + (-4 * a * c)) + "\\)");

		String passoFracao;
		if(b < 0)
			passoFracao = "\\(x_2=\\dfrac{-(" + (b) + ") - \\sqrt{" + delta + "}}{2 \\cdot " + a + "}";
		else
			passoFracao = "\\(x_2=\\dfrac{" + (-b) + " - \\sqrt{" + delta + "}}{2 \\cdot " + a + "}";

		passoFracao += "=\\dfrac{" + (-b) + " - " + raiz + "}{" + (2 * a) + "}\\)";
		passos.add(passoFracao);

		Racional resultado = new Racional((-b) - raiz, (2 * a));
		String passoResultado = "\\(x_2=" + resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			passoResultado += "=" + resultado.showDfrac();
		passoResultado += "\\)";
		passos.add(passoResultado);

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoXVertice(int a, int b, int c)
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(" + formulaXv() + "\\)");
		passos.add("\\(a=" + a + ",~b=" + b + ",~c=" + c + "\\)");

		String passoXv = "\\(X_v";
		if(b < 0)
			passoXv += "=\\dfrac{-(" + b + ")}{2 \\cdot " + a + "}";
		else
			passoXv += "=\\dfrac{" + (-b) + "}{2 \\cdot " + a + "}";

		Racional resultado = new Racional((-b), (2 * a));
		passoXv += "=" + resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			passoXv += "=" + resultado.showDfrac();
		passoXv += "\\)";
		passos.add(passoXv);

		return passos.toArray(new String[0]);
	}

	public static String[] resolucaoYVertice(int a, int b, int c)
	{
		List<String> passos = new ArrayList<>();
		passos.add("\\(" + formulaYv() + "\\)");
		passos.add("\\(a=" + a + ",~b=" + b + ",~c=" + c + "\\)");
		int delta = (int) (b * b) + (-4 * a * c);
		passos.add("\\(\\Delta=" + bSq(b) + "-4 \\cdot " + a + " \\cdot " + c + "\\)");
		passos.add("\\( \\Delta= " + (b * b)
		+ Auxiliar.getNumber((-4 * a * c), "", false) + "=" + ((b * b) + (-4 * a * c)) + "\\)");

		String passoYv = "\\(Y_v=\\dfrac{" + (-delta) + "}{4 \\cdot " + a + "}";

		Racional resultado = new Racional((-delta), (4 * a));
		passoYv += "=" + resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			passoYv += "=" + resultado.showDfrac();
		passoYv += "\\)";
		passos.add(passoYv);

		return passos.toArray(new String[0]);
	}
}
