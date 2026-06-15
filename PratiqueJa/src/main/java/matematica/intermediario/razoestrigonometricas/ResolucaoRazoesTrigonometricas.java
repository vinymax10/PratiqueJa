package matematica.intermediario.razoestrigonometricas;

import matematica.DefinicaoCores;
import matematica.ParCor;
import matematica.Racional;
import matematica.expressao.MyExpression;

public class ResolucaoRazoesTrigonometricas
{

	public static String cosHX(String angle, Racional cos, int catetoAdjacente)
	{
		String resolucao=formulaCos(angle)+"\\\\";
		resolucao+=cos.showDfrac()+"=\\dfrac{"+catetoAdjacente+"}{x}\\\\";
		MyExpression expressao = new MyExpression(cos.numerador+"x="+cos.denominador+"*"+catetoAdjacente);
		resolucao+= expressao.resolverLatex();
		return resolucao;
	}
	
	public static String cosCAX(String angle, Racional cos, int hipotenusa)
	{
		String resolucao=formulaCos(angle)+"\\\\";
		resolucao+=cos.showDfrac()+"=\\dfrac{x}{"+hipotenusa+"}\\\\";
		MyExpression expressao = new MyExpression(cos.denominador+"x="+cos.numerador+"*"+hipotenusa);
		resolucao+= expressao.resolverLatex();
		return resolucao;
	}
	
	public static String tagCAX(String angle, Racional tag, int catetoOposto)
	{
		String resolucao=formulaTag(angle)+"\\\\";
		resolucao+=tag.showDfrac()+"=\\dfrac{"+catetoOposto+"}{x}\\\\";
		MyExpression expressao = new MyExpression(tag.numerador+"x="+tag.denominador+"*"+catetoOposto);
		resolucao+= expressao.resolverLatex();
		return resolucao;
	}
	
	public static String tagCOX(String angle, Racional tag, int catetoAdjacente)
	{
		String resolucao=formulaTag(angle)+"\\\\";
		resolucao+=tag.showDfrac()+"=\\dfrac{x}{"+catetoAdjacente+"}\\\\";
		MyExpression expressao = new MyExpression(tag.denominador+"x="+tag.numerador+"*"+catetoAdjacente);
		resolucao+= expressao.resolverLatex();
		return resolucao;
	}
	
	public static String senHX(String angle, Racional sen, int catetoOposto)
	{
		String resolucao=formulaSen(angle)+"\\\\";
		resolucao+=sen.showDfrac()+"=\\dfrac{"+catetoOposto+"}{x}\\\\";
		MyExpression expressao = new MyExpression(sen.numerador+"x="+sen.denominador+"*"+catetoOposto);
		resolucao+= expressao.resolverLatex();
		return resolucao;
	}
	
	public static String senCOX(String angle, Racional sen, int hipotenusa)
	{
		String resolucao=formulaSen(angle)+"\\\\";
		resolucao+=sen.showDfrac()+"=\\dfrac{x}{"+hipotenusa+"}\\\\";
		MyExpression expressao = new MyExpression(sen.denominador+"x="+sen.numerador+"*"+hipotenusa);
		resolucao+= expressao.resolverLatex();
		return resolucao;
	}
	
	public static String tag60COX(int base)
	{
		String angle="\\alpha";
		String resolucao=formulaTag(angle)+"\\\\"+angle+"=60°, \\quad tan~60° = \\sqrt{3}\\\\";
		resolucao+="\\sqrt{3}=\\dfrac{x}{\\sqrt{"+base+"}}\\\\";
		resolucao+="x=\\sqrt{3} \\cdot \\sqrt{"+base+"}=";
		resolucao+="\\sqrt{3 \\cdot "+base+"}\\\\";

		Racional resultado=new Racional((int)Math.sqrt(base*3));
		resolucao+="x=\\sqrt{"+(base*3)+"} = "+resultado.showDfrac();
		
		return resolucao;
	}
	
	public static String tag30COX(int base)
	{
		String angle="\\alpha";
		String resolucao=formulaTag(angle)+"\\\\"+angle+"=30°, \\quad tan~30° = \\dfrac{\\sqrt{3}}{3}\\\\";
		resolucao+="\\dfrac{\\sqrt{3}}{3}=\\dfrac{x}{\\sqrt{"+base+"}}\\\\";
		resolucao+="x=\\dfrac{\\sqrt{3}}{3} \\cdot \\sqrt{"+base+"}=";
		resolucao+="\\dfrac{\\sqrt{3 \\cdot "+base+"}}{3}\\\\";

		Racional resultado=new Racional((int)Math.sqrt(base*3),3);
		resolucao+="x=\\dfrac{\\sqrt{"+(base*3)+"}}{3} = "+resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucao+="="+resultado.showDfrac();
		
		return resolucao;
	}
	
	public static String sen60COX(int hipotenusa)
	{
		String angle="\\alpha";
		String resolucao=formulaSen(angle)+"\\\\"+angle+"=60°, \\quad sen~60° = \\dfrac{\\sqrt{3}}{2}\\\\";
		resolucao+="\\dfrac{\\sqrt{3}}{2}=\\dfrac{x}{\\sqrt{"+hipotenusa+"}}\\\\";
		resolucao+="x=\\dfrac{\\sqrt{3}}{2} \\cdot \\sqrt{"+hipotenusa+"}=";
		resolucao+="\\dfrac{\\sqrt{3 \\cdot "+hipotenusa+"}}{2}\\\\";

		Racional resultado=new Racional((int)Math.sqrt(hipotenusa*3),2);
		resolucao+="x=\\dfrac{\\sqrt{"+(hipotenusa*3)+"}}{2} = "+resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucao+="="+resultado.showDfrac();
		
		return resolucao;
	}
	
	public static String cos30COX(int hipotenusa)
	{
		String angle="\\alpha";
		String resolucao=formulaCos(angle)+"\\\\"+angle+"=30°, \\quad cos~30° = \\dfrac{\\sqrt{3}}{2}\\\\";
		resolucao+="\\dfrac{\\sqrt{3}}{2}=\\dfrac{x}{\\sqrt{"+hipotenusa+"}}\\\\";
		resolucao+="x=\\dfrac{\\sqrt{3}}{2} \\cdot \\sqrt{"+hipotenusa+"}=";
		resolucao+="\\dfrac{\\sqrt{3 \\cdot "+hipotenusa+"}}{2}\\\\";
		Racional resultado=new Racional((int)Math.sqrt(hipotenusa*3),2);
		resolucao+="x=\\dfrac{\\sqrt{"+(hipotenusa*3)+"}}{2} = "+resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucao+="="+resultado.showDfrac();
		
		return resolucao;
	}
	
	public static String tag60CAX(int altura)
	{
		String angle="\\alpha";
		String resolucao=formulaTag(angle)+"\\\\"+angle+"=60°, \\quad tan~60° = \\sqrt{3}\\\\";
		resolucao+="\\sqrt{3}=\\dfrac{\\sqrt{"+altura+"}}{x}\\\\";
		resolucao+="\\sqrt{3} \\cdot x= \\sqrt{"+altura+"}\\\\";
		resolucao+="x= \\dfrac{\\sqrt{"+altura+"}}{\\sqrt{3}}=";
		resolucao+="\\sqrt{\\dfrac{"+altura+"}{3}}\\\\";
		resolucao+="x= \\sqrt{"+(altura/3)+"}=";
		int resultado=(int)Math.sqrt(altura/3);
		resolucao+=resultado;

		return resolucao;
	}
	
	public static String tag30CAX(int altura)
	{
		String angle="\\alpha";
		String resolucao=formulaTag(angle)+"\\\\"+angle+"=30°, \\quad tan~30° = \\dfrac{\\sqrt{3}}{3}\\\\";
		resolucao+="\\dfrac{\\sqrt{3}}{3}=\\dfrac{\\sqrt{"+altura+"}}{x}\\\\";
		resolucao+="\\sqrt{3} \\cdot x=3 \\cdot \\sqrt{"+altura+"}\\\\";
		resolucao+="x= \\dfrac{3 \\cdot \\sqrt{"+altura+"}}{\\sqrt{3}}=";
		resolucao+="3 \\cdot \\sqrt{\\dfrac{"+altura+"}{3}}\\\\";
		resolucao+="x= 3 \\cdot \\sqrt{"+(altura/3)+"}=";
		int resultado=(int)Math.sqrt(altura/3);
		resolucao+="3 \\cdot"+ resultado+"="+(resultado*3)+"\\\\";

		return resolucao;
	}
	
	public static String sen60HX(int base)
	{
		String angle="\\alpha";
		String resolucao=formulaSen(angle)+"\\\\"+angle+"=60°, \\quad sen~60° = \\dfrac{\\sqrt{3}}{2}\\\\";
		resolucao+="\\dfrac{\\sqrt{3}}{2}=\\dfrac{\\sqrt{"+base+"}}{x}\\\\";
		resolucao+="\\sqrt{3} \\cdot x=2 \\cdot \\sqrt{"+base+"}\\\\";
		resolucao+="x= \\dfrac{2 \\cdot \\sqrt{"+base+"}}{\\sqrt{3}}=";
		resolucao+="2 \\cdot \\sqrt{\\dfrac{"+base+"}{3}}\\\\";
		resolucao+="x= 2 \\cdot \\sqrt{"+(base/3)+"}=";
		int resultado=(int)Math.sqrt(base/3);
		resolucao+="2 \\cdot"+ resultado+"="+(resultado*2)+"\\\\";

		return resolucao;
	}
	
	public static String cos30HX(int base)
	{
		String angle="\\alpha";
		String resolucao=formulaCos(angle)+"\\\\"+angle+"=30°, \\quad cos~30° = \\dfrac{\\sqrt{3}}{2}\\\\";
		resolucao+="\\dfrac{\\sqrt{3}}{2}=\\dfrac{\\sqrt{"+base+"}}{x}\\\\";
		resolucao+="\\sqrt{3} \\cdot x=2 \\cdot \\sqrt{"+base+"}\\\\";
		resolucao+="x= \\dfrac{2 \\cdot \\sqrt{"+base+"}}{\\sqrt{3}}=";
		resolucao+="2 \\cdot \\sqrt{\\dfrac{"+base+"}{3}}\\\\";
		resolucao+="x= 2 \\cdot \\sqrt{"+(base/3)+"}=";
		int resultado=(int)Math.sqrt(base/3);
		resolucao+="2 \\cdot"+ resultado+"="+(resultado*2)+"\\\\";

		return resolucao;
	}
	
	public static String sen30COX(int hipotenusa)
	{
		String angle="\\alpha";
		String resolucao=formulaSen(angle)+"\\\\"+angle+"=30°, \\quad sen~30° = \\dfrac{1}{2}\\\\";
		resolucao+="\\dfrac{1}{2}=\\dfrac{x}{"+hipotenusa+"}\\\\";
		MyExpression expressao = new MyExpression(2+"x="+1+"*"+hipotenusa);
		resolucao+= expressao.resolverLatex();
		return resolucao;
	}
	
	public static String cos60COX(int hipotenusa)
	{
		String angle="\\alpha";
		String resolucao=formulaCos(angle)+"\\\\"+angle+"=60°, \\quad cos~60° = \\dfrac{1}{2}\\\\";
		resolucao+="\\dfrac{1}{2}=\\dfrac{x}{"+hipotenusa+"}\\\\";
		MyExpression expressao = new MyExpression(2+"x="+1+"*"+hipotenusa);
		resolucao+= expressao.resolverLatex();
		return resolucao;
	}
	
	public static String cos60HX(int altura)
	{
		String angle="\\alpha";
		String resolucao=formulaCos(angle)+"\\\\"+angle+"=60°, \\quad cos~60° = \\dfrac{1}{2}\\\\";
		resolucao+="\\dfrac{1}{2}=\\dfrac{"+altura+"}{x}\\\\";
		MyExpression expressao = new MyExpression("x="+2+"*"+altura);
		resolucao+= expressao.resolverLatex();
		return resolucao;
	}
	
	public static String sen30HX(int altura)
	{
		String angle="\\alpha";
		String resolucao=formulaSen(angle)+"\\\\"+angle+"=30°, \\quad sen~30° = \\dfrac{1}{2}\\\\";
		resolucao+="\\dfrac{1}{2}=\\dfrac{"+altura+"}{x}\\\\";
		MyExpression expressao = new MyExpression("x="+2+"*"+altura);
		resolucao+= expressao.resolverLatex();
		return resolucao;
	}
	
	public static String cos(String angle, int catetoAdjacente, int hipotenusa)
	{
		String resolucao=formulaCos(angle)+"\\\\";
		Racional resultado=new Racional(catetoAdjacente,hipotenusa);
		resolucao+="cos~"+angle+"="+resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucao+="="+resultado.showDfrac();
			
		return resolucao;
	}
	
	public static String tag(String angle, int catetoOposto, int catetoAdjacente)
	{
		String resolucao=formulaTag(angle)+"\\\\";
		Racional resultado=new Racional(catetoOposto,catetoAdjacente);
		resolucao+="tan~"+angle+"="+resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucao+="="+resultado.showDfrac();
			
		return resolucao;
	}
	
	public static String sen(String angle, int catetoOposto, int hipotenusa)
	{
		String resolucao=formulaSen(angle)+"\\\\";
		Racional resultado=new Racional(catetoOposto,hipotenusa);
		resolucao+="sen~"+angle+"="+resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucao+="="+resultado.showDfrac();
			
		return resolucao;
	}
	
	public static String formulaSen(String angle)
	{
		String formula=ParCor.formula("sen~"+angle+"=\\dfrac{\\text{Cateto Oposto}}{\\text{Hipotenusa}}");
		return formula;
	}

	public static String formulaCos(String angle)
	{
		String formula=ParCor.formula("cos~"+angle+"=\\dfrac{\\text{Cateto Adjacente}}{\\text{Hipotenusa}}");
		return formula;
	}
	
	public static String formulaTag(String angle)
	{
		String formula=ParCor.formula("tan~"+angle+"=\\dfrac{\\text{Cateto Oposto}}{\\text{Cateto Adjacente}}");
		return formula;
	}

	public static String tan45COX(int base)
	{
		String angle="\\alpha";
		String resolucao=formulaTag(angle)+"\\\\"+angle+"=45°, \\quad tan~45° = 1\\\\";
		resolucao+="1=\\dfrac{x}{"+base+"}\\\\";
		resolucao+="x="+base;
		return resolucao;
	}

	public static String tan45CAX(int altura)
	{
		String angle="\\alpha";
		String resolucao=formulaTag(angle)+"\\\\"+angle+"=45°, \\quad tan~45° = 1\\\\";
		resolucao+="1=\\dfrac{"+altura+"}{x}\\\\";
		resolucao+="x="+altura;
		return resolucao;
	}

	public static String sen45COX(int hyp2)
	{
		int y=(int)Math.sqrt(hyp2/2);
		String angle="\\alpha";
		String resolucao=formulaSen(angle)+"\\\\"+angle+"=45°, \\quad sen~45° = \\dfrac{\\sqrt{2}}{2}\\\\";
		resolucao+="\\dfrac{\\sqrt{2}}{2}=\\dfrac{x}{\\sqrt{"+hyp2+"}}\\\\";
		resolucao+="x=\\dfrac{\\sqrt{2}}{2}\\cdot\\sqrt{"+hyp2+"}=\\dfrac{\\sqrt{"+(2*hyp2)+"}}{2}\\\\";
		resolucao+="x=\\dfrac{"+(2*y)+"}{2}="+y;
		return resolucao;
	}

	public static String cos45CAX(int hyp2)
	{
		int y=(int)Math.sqrt(hyp2/2);
		String angle="\\alpha";
		String resolucao=formulaCos(angle)+"\\\\"+angle+"=45°, \\quad cos~45° = \\dfrac{\\sqrt{2}}{2}\\\\";
		resolucao+="\\dfrac{\\sqrt{2}}{2}=\\dfrac{x}{\\sqrt{"+hyp2+"}}\\\\";
		resolucao+="x=\\dfrac{\\sqrt{2}}{2}\\cdot\\sqrt{"+hyp2+"}=\\dfrac{\\sqrt{"+(2*hyp2)+"}}{2}\\\\";
		resolucao+="x=\\dfrac{"+(2*y)+"}{2}="+y;
		return resolucao;
	}

	public static String identidadePitagorica(String angle, int p, int q, int r)
	{
		String resolucao=ParCor.formula("\\text{sen}^2~"+angle+" + \\cos^2~"+angle+" = 1")+"\\\\";
		resolucao+="\\left(\\dfrac{"+p+"}{"+r+"}\\right)^2+\\cos^2~"+angle+"=1\\\\";
		resolucao+="\\dfrac{"+(p*p)+"}{"+( r*r)+"}+\\cos^2~"+angle+"=1\\\\";
		resolucao+="\\cos^2~"+angle+"=1-\\dfrac{"+(p*p)+"}{"+( r*r)+"}=\\dfrac{"+(r*r-p*p)+"}{"+( r*r)+"}\\\\";
		resolucao+="\\cos~"+angle+"=\\dfrac{"+q+"}{"+r+"}";
		Racional cosVal=new Racional(q,r);
		cosVal.fatoracao(2);
		if(cosVal.isSimplificou())
			resolucao+="="+cosVal.showDfrac();
		return resolucao;
	}

	public static String angulosComplementares(int alpha, int beta, int p, int r)
	{
		Racional senVal=new Racional(p,r);
		String resolucao=ParCor.formula("\\text{sen}~\\alpha=\\cos(90°-\\alpha)")+"\\\\";
		resolucao+="\\cos~"+beta+"°=\\text{sen}~"+alpha+"°="+senVal.showDfrac();
		return resolucao;
	}
}