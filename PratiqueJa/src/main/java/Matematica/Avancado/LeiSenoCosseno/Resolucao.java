package Matematica.Avancado.LeiSenoCosseno;

import Matematica.DefinicaoCores;
import Matematica.ParCor;
import Matematica.Racional;

public class Resolucao
{
	public static String leiSenoNumerador(String angle1, String angle2,
	Racional numerador2, Racional sen1,Racional sen2)
	{
		String resolucaoLatex = "";
		resolucaoLatex+=ParCor.formula("\\dfrac{x}{sen~"+angle1+"} = \\dfrac{"+numerador2.showDfrac()+"}{sen~"+angle2+"}")+"\\\\";
		resolucaoLatex+="\\dfrac{x}{"+sen1.showDfrac()+"} = \\dfrac{"+numerador2.showDfrac()+"}{"+sen2.showDfrac()+"}\\\\";
		resolucaoLatex+="x \\cdot"+sen2.showDfrac()+" = "+numerador2.showDfrac()+" \\cdot "+sen1.showDfrac()+"\\\\";
		Racional parcial=numerador2.mult(sen1);
		parcial.fatoracao(2);
		resolucaoLatex+="x \\cdot"+sen2.showDfrac()+" = "+parcial.showDfrac()+"\\\\";
		resolucaoLatex+="x = "+parcial.showDfrac()+"  \\cdot"+sen2.inverter().showDfrac();
		parcial=parcial.mult(sen2.inverter());
		parcial.fatoracao(2);
		resolucaoLatex+=" = "+parcial.showDfrac();
		
		return resolucaoLatex;
	}
	
	public static String leiSenoDenominador(String angle1, String angle2,
	Racional numerador1, Racional numerador2, Racional sen2)
	{
		String resolucaoLatex = "";
		resolucaoLatex+=ParCor.formula("\\dfrac{"+numerador1.showDfrac()+"}{sen~"+angle1+"} = \\dfrac{"+numerador2.showDfrac()+"}{sen~"+angle2+"}")+"\\\\";
		resolucaoLatex+="\\dfrac{"+numerador1.showDfrac()+"}{sen~"+angle1+"} = \\dfrac{"+numerador2.showDfrac()+"}{"+sen2.showDfrac()+"}\\\\";
		resolucaoLatex+="sen~"+angle1+" \\cdot"+numerador2.showDfrac()+" = "+numerador1.showDfrac()+" \\cdot "+sen2.showDfrac()+"\\\\";
		Racional parcial=numerador1.mult(sen2);
		parcial.fatoracao(2);
		resolucaoLatex+="sen~"+angle1+" \\cdot"+numerador2.showDfrac()+" = "+parcial.showDfrac()+"\\\\";
		
		resolucaoLatex+="sen~"+angle1+" = \\dfrac{"+parcial.showDfrac()+"}{"+numerador2.showDfrac()+"}";
		parcial=parcial.div(numerador2);
		parcial.fatoracao(2);
		if(parcial.isSimplificou())
			resolucaoLatex+=" = "+parcial.showDfrac();
		
		return resolucaoLatex;
	}
	
	public static String leiCosLado(String angle,
	Racional lado1, Racional lado2, Racional cos)
	{
		String resolucaoLatex = ParCor.formula("a^2=b^2+c^2-2bc~cos~"+angle)+"\\\\";
		resolucaoLatex += "a=x, \\quad b="+lado1.showDfrac()+",\\quad c="+lado2.showDfrac()
		+", \\quad cos~"+angle+"="+cos.showDfrac()+"\\\\";
		resolucaoLatex +="x^2="+lado1.showDfrac()+"^2+"+lado2.showDfrac()+"^2 - 2 \\cdot "+
		lado1.showDfrac()+" \\cdot "+lado2.showDfrac()+" \\cdot "+cos.showDfrac()+"\\\\";
		Racional parcial = new Racional(2).mult(lado1).mult(lado2).mult(cos);
		parcial.fatoracao(2);
		
		if(parcial.positivo())
			resolucaoLatex +="x^2="+lado1.mult(lado1).showDfrac()+"+"+lado2.mult(lado2).showDfrac()+" - "+
			parcial.showDfrac()+"=";
		else
			resolucaoLatex +="x^2="+lado1.mult(lado1).showDfrac()+"+"+lado2.mult(lado2).showDfrac()+" - "+
			"("+parcial.showDfrac()+")=";
		
		parcial=lado1.mult(lado1).add(lado2.mult(lado2)).minus(parcial);
		
		resolucaoLatex +=parcial.showDfrac()+"\\\\";
		resolucaoLatex +="x=\\sqrt{"+parcial.showDfrac()+"}="+((int)Math.sqrt(parcial.numerador));

		return resolucaoLatex;
	}
	
	public static String leiCosCosseno(String angle,
	Racional lado1, Racional lado2, Racional lado3)
	{
		String resolucaoLatex = ParCor.formula("a^2=b^2+c^2-2bc~cos~"+angle)+"\\\\";
		resolucaoLatex += "a="+lado1.showDfrac()+", \\quad b="+lado2.showDfrac()+",\\quad c="+lado3.showDfrac()+"\\\\";
		resolucaoLatex +=lado1.showDfrac()+"^2="+lado2.showDfrac()+"^2+"+lado3.showDfrac()+"^2 - 2 \\cdot "+
		lado2.showDfrac()+" \\cdot "+lado3.showDfrac()+" \\cdot cos~"+angle+"\\\\";
		
		resolucaoLatex +=lado1.mult(lado1).showDfrac()+"="+lado2.mult(lado2).showDfrac()+"+"+lado3.mult(lado3).showDfrac()
		+"-"+new Racional(2).mult(lado2).mult(lado3).showDfrac()+" \\cdot cos~"+angle+"\\\\";
		
		resolucaoLatex +=new Racional(2).mult(lado2).mult(lado3).showDfrac()+" \\cdot cos~"+angle+"="+
		lado2.mult(lado2).showDfrac()+"+"+lado3.mult(lado3).showDfrac()
		+"-"+lado1.mult(lado1).showDfrac()+"\\\\";
		
		Racional parcial = lado2.mult(lado2).add(lado3.mult(lado3)).minus(lado1.mult(lado1));
		parcial.fatoracao(2);
		
		resolucaoLatex +=new Racional(2).mult(lado2).mult(lado3).showDfrac()+" \\cdot cos~"+angle+"="+
		parcial.showDfrac()+"\\\\";
		
		parcial=parcial.div(new Racional(2).mult(lado2).mult(lado3));
		
		resolucaoLatex +="cos~"+angle+"="+parcial.showDfrac();

		parcial.fatoracao(2);
		
		if(parcial.isSimplificou())
			resolucaoLatex +="="+parcial.showDfrac();
		
//		if(parcial.positivo())
//			resolucaoLatex +="x^2="+lado1.mult(lado1).showDfrac()+"+"+lado2.mult(lado2).showDfrac()+" - "+
//			parcial.showDfrac()+"=";
//		else
//			resolucaoLatex +="x^2="+lado1.mult(lado1).showDfrac()+"+"+lado2.mult(lado2).showDfrac()+" - "+
//			"("+parcial.showDfrac()+")=";
//		
//		parcial=lado1.mult(lado1).add(lado2.mult(lado2)).minus(parcial);
//		
//		resolucaoLatex +=parcial.showDfrac()+"\\\\";
//		resolucaoLatex +="x=\\sqrt{"+parcial.showDfrac()+"}="+((int)Math.sqrt(parcial.numerador));

		return resolucaoLatex;
	}
}
