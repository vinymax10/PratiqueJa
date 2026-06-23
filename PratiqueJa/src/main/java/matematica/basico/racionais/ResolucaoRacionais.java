package matematica.basico.racionais;

import java.util.ArrayList;
import java.util.List;

import matematica.Racional;
import pdf.util.Convert;


public class ResolucaoRacionais
{
	public static String[] resolucaoCompleta(long a, long b, long c, long d, boolean positivo)
	{
		String resolucaoLatex = "";

		Racional frac1 = new Racional(a, b);
		Racional frac2 = new Racional(c, d);
		frac1.fatoracao(2);
		frac2.fatoracao(2);
		// Pré-simplificação só faz sentido quando os denominadores diferem:
		// com denominadores iguais, basta somar/subtrair diretamente.
		if(b != d && (frac1.isSimplificou() || frac2.isSimplificou()))
		{
			resolucaoLatex += "\\dfrac{" + a + "}{" + b + "}" + sinalPlusMinus(positivo)
			+ "\\dfrac{" + c + "}{" + d + "}=";

			a = frac1.numerador;
			b = frac1.denominador;
			c = frac2.numerador;
			d = frac2.denominador;
		}
		resolucaoLatex += "\\dfrac{" + a + "}{" + b + "}" + sinalPlusMinus(positivo)
		+ "\\dfrac{" + c + "}{" + d + "}=";

		if(b==d)
		{
			// Usa os valores atuais (a, b, c) e não frac1/frac2, que foram
			// mutados por fatoracao() e podem ter denominadores diferentes.
			if(positivo)
				resolucaoLatex +=simplesSomaLatex(new Racional(a, b), new Racional(c, d));
			else
				resolucaoLatex +=simplesMenosLatex(new Racional(a, b), new Racional(c, d));
		}
		else
		{
			resolucaoLatex += "\\dfrac{" + a + "\\cdot" + d + sinalPlusMinus(positivo) + c + "\\cdot " + b + "}{" + b+" \\cdot "+d + "}=";

			resolucaoLatex += "\\dfrac{" + (a*d) + sinalPlusMinus(positivo) + (c*b) + "}{" + (b*d) + "}=";

			Racional frac;
			if(positivo)
				frac = new Racional((a*d)+(c*b),(b*d));
			else
				frac = new Racional((a*d)-(c*b),(b*d));

			resolucaoLatex += frac.showDfrac();

			frac.fatoracao(2);

			if(frac.isSimplificou()&&!frac.isZero())
				resolucaoLatex += "=" + frac.showDfrac();
		}

		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,180);

		return separarPassos(resolucaoLatex);
	}

	public static String[] simplesSoma(Racional a, Racional b)
	{
		return separarPassos(Convert.includeLineBreak(simplesSomaLatex(a, b), 180));
	}

	private static String simplesSomaLatex(Racional a, Racional b)
	{
		String resolucaoLatex = "";
		resolucaoLatex += "\\dfrac{" + a.numerador + "+" + b.numerador + "}{" + a.denominador + "}";

		Racional resultado = new Racional(a.numerador + b.numerador,a.denominador);

		resolucaoLatex += "=" + resultado.showDfrac();

		resultado.fatoracao(2);

		if(resultado.isSimplificou()&&!resultado.isZero())
			resolucaoLatex += "=" + resultado.showDfrac();

		return resolucaoLatex;
	}

	public static String[] simplesMenos(Racional a, Racional b)
	{
		return separarPassos(Convert.includeLineBreak(simplesMenosLatex(a, b), 180));
	}

	private static String simplesMenosLatex(Racional a, Racional b)
	{
		String resolucaoLatex = "";
		resolucaoLatex += "\\dfrac{" + a.numerador + "-" + b.numerador + "}{" + a.denominador + "}";

		Racional resultado = new Racional(a.numerador - b.numerador,a.denominador);
		resolucaoLatex += "=" + resultado.showDfrac();

		resultado.fatoracao(2);

		if(resultado.isSimplificou()&&!resultado.isZero())
			resolucaoLatex += "=" + resultado.showDfrac();

		return resolucaoLatex;
	}

	public static String[] Multiplicacao(long a, long b, long c, long d)
	{
		String resolucaoLatex = "";

		Racional frac1 = new Racional(a, b);
		Racional frac2 = new Racional(c, d);
		frac1.fatoracao(2);
		frac2.fatoracao(2);
		if(frac1.isSimplificou() || frac2.isSimplificou())
		{
			resolucaoLatex += "\\dfrac{" + a + "}{" + b + "} \\cdot \\dfrac{" + c + "}{" + d + "}=";

			a = frac1.numerador;
			b = frac1.denominador;
			c = frac2.numerador;
			d = frac2.denominador;
		}

		resolucaoLatex += "\\dfrac{" + a + "}{" + b + "} \\cdot \\dfrac{" + c + "}{" + d + "}=";

		resolucaoLatex += "\\dfrac{" + a + "\\cdot" + c + "}{" + b + "\\cdot" + d + "}=";
		long resultNumerodor = a * c;
		long resultDenominador = b * d;

		Racional fracSimplificada = new Racional(resultNumerodor, resultDenominador);
		resolucaoLatex += fracSimplificada.showDfrac();

		fracSimplificada.fatoracao(2);

		if(fracSimplificada.isSimplificou())
			resolucaoLatex += "=" + fracSimplificada.showDfrac();

		return separarPassos(Convert.includeLineBreak(resolucaoLatex,180));
	}

	public static String[] divisao(long a, long b, long c, long d)
	{
		String resolucaoLatex = "";

		Racional frac1 = new Racional(a, b);
		Racional frac2 = new Racional(c, d);
		frac1.fatoracao(2);
		frac2.fatoracao(2);
		if(frac1.isSimplificou() || frac2.isSimplificou())
		{
			resolucaoLatex += "\\dfrac{" + a + "}{" + b + "} \\div \\dfrac{" + c + "}{" + d + "}=";

			a = frac1.numerador;
			b = frac1.denominador;
			c = frac2.numerador;
			d = frac2.denominador;
		}

		resolucaoLatex += "\\dfrac{" + a + "}{" + b + "} \\div \\dfrac{" + c + "}{" + d + "}=";
		long aux = c;
		c = d;
		d = aux;
		if(d < 0)
		{
			d *= -1;
			c *= -1;
		}
		resolucaoLatex += "\\dfrac{" + a + "}{" + b + "}" + "\\cdot" + "\\dfrac{" + c + "}{" + d + "}=";

		resolucaoLatex += "\\dfrac{" + a + "\\cdot" + c + "}{" + b + "\\cdot" + d + "}=";
		long resultNumerodor = a * c;
		long resultDenominador = b * d;

		Racional fracSimplificada = new Racional(resultNumerodor, resultDenominador);
		resolucaoLatex += fracSimplificada.showDfrac();

		fracSimplificada.fatoracao(2);

		if(fracSimplificada.isSimplificou())
			resolucaoLatex += "=" + fracSimplificada.showDfrac();

		return separarPassos(Convert.includeLineBreak(resolucaoLatex,180));
	}

	protected static String sinalPlusMinus(boolean positivo)
	{
		if(positivo)
			return "+";
		else
			return "-";
	}

	/**
	 * Separa o LaTeX em passos nos "\\" de nível superior inseridos por
	 * {@link Convert#includeLineBreak} (quebra de linha da equação por largura),
	 * preservando "\\" interno de array/bmatrix. Cada passo é devolvido SEM o
	 * delimitador "\(...\)" — o chamador envolve cada passo e faz um addResolucao.
	 */
	private static String[] separarPassos(String latex)
	{
		List<String> passos = new ArrayList<>();
		int arrayDepth = 0, inicio = 0, i = 0, n = latex.length();
		while(i < n)
		{
			if(latex.startsWith("\\begin{", i))
			{
				arrayDepth++; i += 7;
			}
			else if(arrayDepth > 0 && latex.startsWith("\\end{", i))
			{
				arrayDepth--; i += 5;
			}
			else if(arrayDepth == 0 && latex.startsWith("\\\\", i))
			{
				adicionarPasso(passos, latex.substring(inicio, i));
				i += 2; inicio = i;
			}
			else
			{
				i++;
			}
		}
		adicionarPasso(passos, latex.substring(inicio));
		return passos.toArray(new String[0]);
	}

	private static void adicionarPasso(List<String> passos, String passo)
	{
		String t = passo.trim();
		if(!t.isEmpty())
			passos.add(t);
	}
}
