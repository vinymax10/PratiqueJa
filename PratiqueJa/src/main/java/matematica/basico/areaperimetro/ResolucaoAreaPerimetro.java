package matematica.basico.areaperimetro;

import matematica.ParCor;

public class ResolucaoAreaPerimetro
{

	public static String formulaAreaRetangulo()
	{
		return ParCor.formula("A=b \\cdot h");
	}

	public static String[] areaRetangulo(int b, int h)
	{
		return new String[]
		{
			"\\(" + formulaAreaRetangulo() + "\\)",
			"\\(b=" + b + ",\\quad h=" + h + "\\)",
			"\\(A=" + b + " \\cdot " + h + "=" + (b * h) + "\\)"
		};
	}

	public static String formulaPerimetroRetangulo()
	{
		return ParCor.formula("P=2\\cdot (b + h)");
	}

	public static String[] perimetroRetangulo(int b, int h)
	{
		return new String[]
		{
			"\\(" + formulaPerimetroRetangulo() + "\\)",
			"\\(b=" + b + ",\\quad h=" + h + "\\)",
			"\\(P=2 \\cdot (" + b + "+" + h + ")=2 \\cdot (" + (b + h) + ")=" + (2 * (b + h)) + "\\)"
		};
	}

	public static String formulaPerimetroParalelogramo()
	{
		return ParCor.formula("P=2\\cdot (a+b)");
	}

	public static String[] formulaPerimetroParalelogramo(int a, int b)
	{
		return new String[]
		{
			"\\(" + formulaPerimetroParalelogramo() + "\\)",
			"\\(a=" + a + ",\\quad b=" + b + "\\)",
			"\\(P=2 \\cdot (" + a + "+" + b + ")=2 \\cdot (" + (a + b) + ")=" + (2 * (a + b)) + "\\)"
		};
	}

	public static String formulaDiagonalQuadrado()
	{
		return ParCor.formula("d=l\\sqrt{2}");
	}

	public static String formulaAreaQuadrado()
	{
		return ParCor.formula("A=l^2");
	}

	public static String[] areaQuadrado(int l)
	{
		return new String[]
		{
			"\\(" + formulaAreaQuadrado() + "\\)",
			"\\(l=" + l + "\\)",
			"\\(A=" + l + "^2=" + l + " \\cdot " + l + "=" + (l * l) + "\\)"
		};
	}

	public static String formulaPerimetroQuadrado()
	{
		return ParCor.formula("P=4 \\cdot l");
	}

	public static String[] perimetroQuadrado(int l)
	{
		return new String[]
		{
			"\\(" + formulaPerimetroQuadrado() + "\\)",
			"\\(l=" + l + "\\)",
			"\\(P= 4 \\cdot " + l + "=" + (4 * l) + "\\)"
		};
	}

	public static String formulaComprimentoCircunferencia()
	{
		return ParCor.formula("C=2 \\cdot \\pi \\cdot r");
	}

	public static String[] comprimentoCircunferencia(int r)
	{
		return new String[]
		{
			"\\(" + formulaComprimentoCircunferencia() + "\\)",
			"\\(r=" + r + "\\)",
			"\\(C=2 \\cdot \\pi \\cdot" + r + "=" + (2 * r) + "\\pi \\)",
			"\\(x=" + (2 * r) + "\\)"
		};
	}

	public static String formulaAreaCirculo()
	{
		return ParCor.formula("A=\\pi \\cdot r^2");
	}

	public static String[] areaCirculo(int r)
	{
		return new String[]
		{
			"\\(" + formulaAreaCirculo() + "\\)",
			"\\(r=" + r + "\\)",
			"\\(A=\\pi \\cdot" + r + "^2=\\pi \\cdot " + r + " \\cdot " + r + "=" + (r * r) + "\\pi\\)",
			"\\(x=" + (r * r) + "\\)"
		};
	}

	public static String formulaAreaTriangulo()
	{
		return ParCor.formula("A=\\dfrac{b \\cdot h}{2}");
	}

	public static String[] areaTriangulo(int b, int h)
	{
		return new String[]
		{
			"\\(" + formulaAreaTriangulo() + "\\)",
			"\\(b=" + b + ",\\quad h=" + h + "\\)",
			"\\(A=\\dfrac{" + b + " \\cdot " + h + "}{2}=" + "\\dfrac{" + b * h + "}{2}=" + (b * h) / 2 + "\\)"
		};
	}

	public static String formulaPerimetroTrianguloEquilatero()
	{
		return ParCor.formula("P=3 \\cdot l");
	}

	public static String[] perimetroTrianguloEquilatero(int l)
	{
		return new String[]
		{
			"\\(" + formulaPerimetroTrianguloEquilatero() + "\\)",
			"\\(l=" + l + "\\)",
			"\\(P=3 \\cdot" + l + "=" + (3 * l) + "\\)"
		};
	}

	public static String formulaPerimetroTrianguloIsosceles()
	{
		return ParCor.formula("P=2 \\cdot a + b");
	}

	public static String[] perimetroTrianguloIsosceles(int a, int b)
	{
		return new String[]
		{
			"\\(" + formulaPerimetroTrianguloIsosceles() + "\\)",
			"\\(a=" + a + ",\\quad b=" + b + "\\)",
			"\\(P=2 \\cdot" + a + "+" + b + "=" + (2 * a) + "+" + b + "=" + ((2 * a) + b) + "\\)"
		};
	}

	public static String formulaPerimetroTriangulo()
	{
		return ParCor.formula("P=a + b + c");
	}

	public static String[] perimetroTriangulo(int a, int b, int c)
	{
		return new String[]
		{
			"\\(" + formulaPerimetroTriangulo() + "\\)",
			"\\(a=" + a + ",\\quad b=" + b + ",\\quad c=" + c + "\\)",
			"\\(P=" + a + "+" + b + "+" + c + "=" + (a + b + c) + "\\)"
		};
	}

	public static String formulaAreaTrianguloEquilatero()
	{
		return ParCor.formula("A=\\dfrac{l^2 \\cdot \\sqrt{3}}{4}");
	}

	public static String[] areaTrianguloEquilatero(int l)
	{
		return new String[]
		{
			"\\(" + formulaAreaTrianguloEquilatero() + "\\)",
			"\\(l=" + l + "\\)",
			"\\(A=\\dfrac{" + l + "^2 \\cdot \\sqrt{3}}{4}=" + "\\dfrac{" + (l * l) + "\\cdot \\sqrt{3}}{4}=" + (l * l) / 4 + "\\sqrt{3}\\)",
			"\\(x=" + (l * l) / 4 + "\\)"
		};
	}

	public static String formulaLosango()
	{
		return ParCor.formula("A=\\dfrac{D \\cdot d}{2}");
	}

	public static String[] losango(int D, int d)
	{
		return new String[]
		{
			"\\(" + formulaLosango() + "\\)",
			"\\(D=" + D + ",\\quad d=" + d + "\\)",
			"\\(A=\\dfrac{" + (D) + " \\cdot " + (d) + "}{2}=" + "\\dfrac{" + (D) * (d) + "}{2}=" + (D * d) / 2 + "\\)"
		};
	}

	public static String formulaPerimetroTrapezio()
	{
		return ParCor.formula("P=a+b+c+d");
	}

	public static String[] perimetroTrapezio(int a, int b, int c, int d)
	{
		return new String[]
		{
			"\\(" + formulaPerimetroTrapezio() + "\\)",
			"\\(a=" + a + ",\\quad b=" + b + ",\\quad c=" + c + ",\\quad d=" + d + "\\)",
			"\\(P=" + a + "+" + b + "+" + c + "+" + d + "=" + (a + b + c + d) + "\\)"
		};
	}

	public static String formulaAreaTrapezio()
	{
		return ParCor.formula("A=\\dfrac{(B+b) \\cdot h}{2}");
	}

	public static String[] areaTrapezio(int altura, int baseMaior, int baseMenor)
	{
		return new String[]
		{
			"\\(" + formulaAreaTrapezio() + "\\)",
			"\\(B=" + baseMaior + ",\\quad b=" + baseMenor + ",\\quad h=" + altura + "\\)",
			"\\(A=\\dfrac{(" + baseMaior + "+" + baseMenor + ") \\cdot " + altura + "}{2}=" + "\\dfrac{" + (baseMaior + baseMenor) + " \\cdot " + altura + "}{2}=" + "\\dfrac{" + (baseMaior + baseMenor) * altura + "}{2}=" + (baseMenor + baseMaior) * altura / 2 + "\\)"
		};
	}

	public static String formulaLadoLosango()
	{
		return ParCor.formula("l=\\sqrt{\\left(\\dfrac{D}{2}\\right)^2+\\left(\\dfrac{d}{2}\\right)^2}");
	}
}
