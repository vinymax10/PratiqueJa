package matematica.basico.areaperimetro;

import matematica.ParCor;

public class ResolucaoAreaPerimetro
{

	public static String formulaAreaRetangulo()
	{
		return ParCor.formula("A=b \\cdot h");
	}
	
	public static String areaRetangulo(int b, int h)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaAreaRetangulo()+"\\\\";
		resolucaoLatex+="b="+b+",\\quad h="+h+"\\\\";
		resolucaoLatex+="A="+b+" \\cdot "+h+"="+(b*h);
		return resolucaoLatex;
	}
	
	public static String formulaPerimetroRetangulo()
	{
		return ParCor.formula("P=2\\cdot (b + h)");
	}
	
	public static String perimetroRetangulo(int b, int h)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaPerimetroRetangulo()+"\\\\";
		resolucaoLatex+="b="+b+",\\quad h="+h+"\\\\";
		resolucaoLatex+="P=2 \\cdot ("+b+"+"+h+")=2 \\cdot ("+(b+h)+")="+(2*(b+h));
		return resolucaoLatex;
	}
	
	public static String formulaPerimetroParalelogramo()
	{
		return ParCor.formula("P=2\\cdot (a+b)");
	}
	
	public static String formulaPerimetroParalelogramo(int a, int b)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaPerimetroParalelogramo()+"\\\\";
		resolucaoLatex+="a="+a+",\\quad b="+b+"\\\\";
		resolucaoLatex+="P=2 \\cdot ("+a+"+"+b+")=2 \\cdot ("+(a+b)+")="+(2*(a+b));
		return resolucaoLatex;
	}
	
	public static String formulaDiagonalQuadrado()
	{
		return ParCor.formula("d=l\\sqrt{2}");
	}
	
	public static String formulaAreaQuadrado()
	{
		return ParCor.formula("A=l^2");
	}
	
	public static String areaQuadrado(int l)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaAreaQuadrado()+"\\\\";
		resolucaoLatex+="l="+l+"\\\\";
		resolucaoLatex+="A="+l+"^2="+l+" \\cdot "+l+"="+(l*l);
		return resolucaoLatex;
	}
	
	public static String formulaPerimetroQuadrado()
	{
		return ParCor.formula("P=4 \\cdot l");
	}
	
	public static String perimetroQuadrado(int l)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaPerimetroQuadrado()+"\\\\";
		resolucaoLatex+="l="+l+"\\\\";
		resolucaoLatex+="P= 4 \\cdot "+l+"="+(4*l);
		return resolucaoLatex;
	}
	
	public static String formulaComprimentoCircunferencia()
	{
		return ParCor.formula("C=2 \\cdot \\pi \\cdot r");
	}
	
	public static String comprimentoCircunferencia(int r)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaComprimentoCircunferencia()+"\\\\";
		resolucaoLatex+="r="+r+"\\\\";
		resolucaoLatex+="C=2 \\cdot \\pi \\cdot"+r+"="+(2*r)+"\\pi \\\\";
		resolucaoLatex+="x="+(2*r);

		return resolucaoLatex;
	}
	
	public static String formulaAreaCirculo()
	{
		return ParCor.formula("A=\\pi \\cdot r^2");
	}
	
	public static String areaCirculo(int r)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaAreaCirculo()+"\\\\";
		resolucaoLatex+="r="+r+"\\\\";
		resolucaoLatex+="A=\\pi \\cdot"+r+"^2=\\pi \\cdot "+r+" \\cdot "+r+"="+(r*r)+"\\pi\\\\";
		resolucaoLatex+="x="+(r*r);

		return resolucaoLatex;
	}
	
	public static String formulaAreaTriangulo()
	{
		return ParCor.formula("A=\\dfrac{b \\cdot h}{2}");
	}
	
	public static String areaTriangulo(int b, int h)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaAreaTriangulo()+"\\\\";
		resolucaoLatex+="b="+b+",\\quad h="+h+"\\\\";
		resolucaoLatex+="A=\\dfrac{"+b+" \\cdot "+h+"}{2}=";
		resolucaoLatex+="\\dfrac{"+b*h+"}{2}=";
		resolucaoLatex+=(b*h)/2;
		return resolucaoLatex;
	}
	
	public static String formulaPerimetroTrianguloEquilatero()
	{
		return ParCor.formula("P=3 \\cdot l");
	}
	
	public static String perimetroTrianguloEquilatero(int l)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaPerimetroTrianguloEquilatero()+"\\\\";
		resolucaoLatex+="l="+l+"\\\\";
		resolucaoLatex+="P=3 \\cdot"+l+"="+(3*l);
		return resolucaoLatex;
	}
	
	public static String formulaPerimetroTrianguloIsosceles()
	{
		return ParCor.formula("P=2 \\cdot a + b");
	}
	
	public static String perimetroTrianguloIsosceles(int a, int b)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaPerimetroTrianguloIsosceles()+"\\\\";
		resolucaoLatex+="a="+a+",\\quad b="+b+"\\\\";
		resolucaoLatex+="P=2 \\cdot"+a+"+"+b+"="+(2*a)+"+"+b+"="+((2*a)+b) ;
		return resolucaoLatex;
	}
	
	public static String formulaPerimetroTriangulo()
	{
		return ParCor.formula("P=a + b + c");
	}
	
	public static String perimetroTriangulo(int a, int b, int c)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaPerimetroTriangulo()+"\\\\";
		resolucaoLatex+="a="+a+",\\quad b="+b+",\\quad c="+c+"\\\\";
		resolucaoLatex+="P="+a+"+"+b+"+"+c+"="+(a+b+c);
		return resolucaoLatex;
	}
	
	public static String formulaAreaTrianguloEquilatero()
	{
		return ParCor.formula("A=\\dfrac{l^2 \\cdot \\sqrt{3}}{4}");
	}
	
	public static String areaTrianguloEquilatero(int l)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaAreaTrianguloEquilatero()+"\\\\";
		resolucaoLatex+="l="+l+"\\\\";
		resolucaoLatex+="A=\\dfrac{"+l+"^2 \\cdot \\sqrt{3}}{4}=";
		resolucaoLatex+="\\dfrac{"+(l*l)+"\\cdot \\sqrt{3}}{4}=";
		resolucaoLatex+=(l*l)/4+"\\sqrt{3}\\\\";
		resolucaoLatex+="x="+(l*l)/4;

		return resolucaoLatex;
	}
	
	public static String formulaLosango()
	{
		return ParCor.formula("A=\\dfrac{D \\cdot d}{2}");
	}
	
	public static String losango(int D, int d)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaLosango()+"\\\\";
		resolucaoLatex+="D="+D+",\\quad d="+d+"\\\\";
		resolucaoLatex+="A=\\dfrac{"+(D)+" \\cdot "+(d)+"}{2}=";
		resolucaoLatex+="\\dfrac{"+(D)*(d)+"}{2}=";
		resolucaoLatex+=(D*d)/2;
		return resolucaoLatex;
	}
	
	public static String formulaPerimetroTrapezio()
	{
		return ParCor.formula("P=a+b+c+d");
	}
	
	public static String perimetroTrapezio(int a, int b, int c, int d)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaPerimetroTrapezio()+"\\\\";
		resolucaoLatex+="a="+a+",\\quad b="+b+",\\quad c="+c+",\\quad d="+d+"\\\\";
		resolucaoLatex+="P="+a+"+"+b+"+"+c+"+"+d+"="+(a+b+c+d);
		return resolucaoLatex;
	}
	
	public static String formulaAreaTrapezio()
	{
		return ParCor.formula("A=\\dfrac{(B+b) \\cdot h}{2}");
	}
	
	public static String areaTrapezio(int altura, int baseMaior, int baseMenor)
	{
		String resolucaoLatex="";
		resolucaoLatex+=formulaAreaTrapezio()+"\\\\";
		resolucaoLatex+="B="+baseMaior+",\\quad b="+baseMenor+",\\quad h="+altura+"\\\\";
		resolucaoLatex+="A=\\dfrac{("+baseMaior+"+"+baseMenor+") \\cdot "+altura+"}{2}=";
		resolucaoLatex+="\\dfrac{"+(baseMaior+baseMenor)+" \\cdot "+altura+"}{2}=";
		resolucaoLatex+="\\dfrac{"+(baseMaior+baseMenor)*altura+"}{2}=";
		resolucaoLatex+=(baseMenor + baseMaior) * altura/2;
		return resolucaoLatex;
	}

	public static String formulaLadoLosango()
	{
		return ParCor.formula("l=\\sqrt{\\left(\\dfrac{D}{2}\\right)^2+\\left(\\dfrac{d}{2}\\right)^2}");
	}
}