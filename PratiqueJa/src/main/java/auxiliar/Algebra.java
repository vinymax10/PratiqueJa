package auxiliar;

import java.util.Random;

import matematica.Racional;

public class Algebra
{
	
	public static int fatorial(int n)
	{
		if(n == 0)
		{
			return 1; // Base da recursão
		}
		else
		{
			return n * fatorial(n - 1); // Recursão
		}
	}
	
	public static int fatorial(int n, int limite)
	{
		if(n <= limite)
		{
			return 1; // Base da recursão
		}
		else
		{
			return n * fatorial(n - 1,limite); // Recursão
		}
	}
	
	public static String parentezes(String str)
	{
		Random rand = new Random();
		if(rand.nextBoolean())
			return "(" + str + ")";
		else
			return str;
	}

	public static String sinalPlusMinus()
	{
		Random rand = new Random();
		if(rand.nextBoolean())
			return "+";
		else
			return "-";
	}

	public static String sinalMultDiv()
	{
		Random rand = new Random();
		if(rand.nextBoolean())
			return "*";
		else
			return "/";
	}

	public static String sinal()
	{
		Random rand = new Random();
		switch(rand.nextInt(4)) {
		case 0:
			return "*";
		case 1:
			return "/";
		case 2:
			return "+";
		case 3:
			return "-";
		default:
			return "*";
		}
	}

	public static String sinalMenosDiv()
	{
		Random rand = new Random();
		switch(rand.nextInt(3)) {
		case 0:
			return "*";
		case 1:
			return "+";
		case 2:
			return "-";
		default:
			return "*";
		}
	}

	public static String sinalMinus()
	{
		Random rand = new Random();
		if(rand.nextBoolean())
			return "-";
		else
			return "";
	}

	public static String fatoracao(int a, int b, int fator)
	{
		while(fator <= Math.abs(a) && fator <= Math.abs(b))
		{
			if(a % fator == 0 && b % fator == 0)
			{
				a /= fator;
				b /= fator;
			}
			else
				fator++;
		}

		if(b < 0)
		{
			a *= -1;
			b *= -1;
		}

		if(a == 0 || b == 1 || b == -1)
			return "" + a;
		else
			return "" + a + "/" + b;
	}

	public static String converter(String operador)
	{
		switch(operador) {
		case "+":
			return "+";
		case "-":
			return "-";
		case "*":
			return "\\times";
		case "/":
			return "\\div";
		case "^":
			return "^";
		}
		return "";
	}

	public static String gerarTextLatex(String exp, int[] posX, Racional[] coeficientes)
	{
		Random rand = new Random();
		String variavel = "" + (char) (97 + rand.nextInt(26));
		String textLatex = exp;
		for(int i = 0; i < coeficientes.length; i++)
			textLatex = textLatex.replace("" + (char) (65 + i), getCoeficiente(i, posX, coeficientes[i], variavel));

		textLatex = textLatex.replace("*", "\\times");

		String vet[] = textLatex.split("/");
		if(vet.length > 1)
			textLatex = "\\dfrac{" + vet[0] + "}{" + vet[1] + "}";

		textLatex += "=";

		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");
		textLatex = "\\begin{align}&" + "\\text{Para}~" + variavel + " = " + coeficientes[posX[0]] + ",\\\\ &" + textLatex + "\\end{align}";

		return textLatex;
	}

	public static String gerarTextLatexEN(String exp, Racional[] coeficientes)
	{
		String textLatex = exp;
		for(int i = 0; i < coeficientes.length; i++)
			textLatex = textLatex.replace("" + (char) (65 + i), coeficientes[i].toString());

		textLatex = textLatex.replace("*", "\\times");

		String vet[] = textLatex.split("/");
		if(vet.length > 1)
			textLatex = "\\dfrac{" + vet[0] + "}{" + vet[1] + "}";

		textLatex += "=";

		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		return textLatex;
	}

	private static String getCoeficiente(int index, int[] posX, Racional coeficiente, String variavel)
	{
		boolean contem = false;
		for(int i = 0; i < posX.length; i++)
		{
			if(index == posX[i])
				contem = true;
		}

		if(contem)
			return variavel;
		else
			return "" + coeficiente;
	}

	public static void main(String[] args)
	{
		System.out.println(Algebra.fatorial(7,3));
	}
}
