package Matematica.Basico.Racionais;

import Matematica.Racional;
import Pdf.Convert;


public class ResolucaoRacionais
{
	public static String resolucaoCompleta(long a, long b, long c, long d, boolean positivo)
	{
		String resolucaoLatex = "";
		
		Racional frac1 = new Racional(a, b);
		Racional frac2 = new Racional(c, d);
		frac1.fatoracao(2);
		frac2.fatoracao(2);
		if(b!=d && frac1.isSimplificou() || frac2.isSimplificou())
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
			if(positivo)
				resolucaoLatex +=simplesSoma(frac1, frac2);
			else
				resolucaoLatex +=simplesMenos(frac1,frac2);
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

		return resolucaoLatex;
	}
	
	public static String simplesSoma(Racional a, Racional b)
	{
		String resolucaoLatex = "";
		resolucaoLatex += "\\dfrac{" + a.numerador + "+" + b.numerador + "}{" + a.denominador + "}";
		
		Racional resultado = new Racional(a.numerador + b.numerador,a.denominador);
		
		resolucaoLatex += "=" + resultado.showDfrac();

		resultado.fatoracao(2);

		if(resultado.isSimplificou()&&!resultado.isZero())
			resolucaoLatex += "=" + resultado.showDfrac();

		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,180);

		return resolucaoLatex;
	}
	
	public static String simplesMenos(Racional a, Racional b)
	{
		String resolucaoLatex = "";
		resolucaoLatex += "\\dfrac{" + a.numerador + "-" + b.numerador + "}{" + a.denominador + "}";
		
		Racional resultado = new Racional(a.numerador - b.numerador,a.denominador);
		resolucaoLatex += "=" + resultado.showDfrac();

		resultado.fatoracao(2);

		if(resultado.isSimplificou()&&!resultado.isZero())
			resolucaoLatex += "=" + resultado.showDfrac();

		resolucaoLatex=Convert.includeLineBreak(resolucaoLatex,180);

		return resolucaoLatex;
	}

	public static String Multiplicacao(long a, long b, long c, long d)
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

		return Convert.includeLineBreak(resolucaoLatex,180);
	}

	public static String divisao(long a, long b, long c, long d)
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

		return Convert.includeLineBreak(resolucaoLatex,180);
	}

	protected static String sinalPlusMinus(boolean positivo)
	{
		if(positivo)
			return "+";
		else
			return "-";
	}
}
