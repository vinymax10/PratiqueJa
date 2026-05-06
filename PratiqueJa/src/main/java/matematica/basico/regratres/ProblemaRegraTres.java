package matematica.basico.regratres;

import java.util.Random;

import matematica.Racional;
import matematica.expressao.MyExpression;

public class ProblemaRegraTres
{
	public String texto;
	public String coluna1;
	public String coluna2;
	public String coluna3;
	public TipoProporcao tipoProporcao;
	
	public Racional a,b,c,d,e;
	public int metricaCol1, metricaCol2,metricaCol3;
	
	public ProblemaRegraTres(String texto, String coluna1, String coluna2, String coluna3, TipoProporcao tipoProporcao)
	{
		super();
		this.texto = texto;
		this.coluna1 = coluna1;
		this.coluna2 = coluna2;
		this.coluna3 = coluna3;
		this.tipoProporcao=tipoProporcao;
		this.metricaCol1=1;
		this.metricaCol2=1;
		this.metricaCol3=1;
	}
	
	public ProblemaRegraTres(String texto, String coluna1, String coluna2, 
	String coluna3, TipoProporcao tipoProporcao, int metricaCol1, int metricaCol2)
	{
		super();
		this.texto = texto;
		this.coluna1 = coluna1;
		this.coluna2 = coluna2;
		this.coluna3 = coluna3;
		this.tipoProporcao=tipoProporcao;
		this.metricaCol1=metricaCol1;
		this.metricaCol2=metricaCol2;
		this.metricaCol3=1;
	}
	
	public ProblemaRegraTres(String texto, String coluna1, String coluna2, 
	String coluna3, TipoProporcao tipoProporcao, int metricaCol1, int metricaCol2, int metricaCol3)
	{
		super();
		this.texto = texto;
		this.coluna1 = coluna1;
		this.coluna2 = coluna2;
		this.coluna3 = coluna3;
		this.tipoProporcao=tipoProporcao;
		this.metricaCol1=metricaCol1;
		this.metricaCol2=metricaCol2;
		this.metricaCol3=metricaCol3;
	}
	
	public void gerarValores()
	{
		Random rand=new Random();
		int aInt=1 + rand.nextInt(10);
		a = new Racional(metricaCol1*aInt);
		if(a.numerador==1)
			a.numerador++;
		
		Racional y = new Racional(2 + rand.nextInt(5));
		Racional t = new Racional(2 + rand.nextInt(5));

		switch(tipoProporcao)
		{
			case ABCX: //corrigido a proporcao
				do
				{
					b = new Racional(metricaCol2);
					b=b.mult(new Racional(aInt)).add(new Racional(1 + rand.nextInt(10)));
					if(b.numerador==1)
						b.numerador++;
				}
				while(a.equals(b));
				
				c=a.mult(y);break;
				
			case ABXC: //corrigido a proporcao
				do
				{
					b = new Racional(metricaCol2);
					b=b.mult(new Racional(aInt)).add(new Racional(1 + rand.nextInt(10)));
					if(b.numerador==1)
						b.numerador++;
				}
				while(a.equals(b));
				
				c=b.mult(y);break;
			
			case IABCX,IABXC: //corrigido a proporcao
				do
					c = new Racional(2 + rand.nextInt(10));
				while(a.equals(c));
				
				b = new Racional(metricaCol2/aInt);
				if(b.numerador==1)
					b.numerador++;

				y=new Racional((int)Math.ceil(b.div(c).decimal()));
				b=c.mult(y);
				break;
				
			case IABCDEX:
				b = new Racional(metricaCol2);
				b=b.add(new Racional(1 + rand.nextInt(10)));
				if(b.numerador==1)
					b.numerador++;
				
				c = new Racional(metricaCol3/aInt);
				if(c.numerador==1)
					c.numerador++;

				do
					d = new Racional(2 + rand.nextInt(10));
				while(a.equals(d));
				
				e = b.mult(d).mult(y);
				break;
				
			case IABCDXE:
				
				b = new Racional(metricaCol2);
				b=b.add(new Racional(1 + rand.nextInt(10)));
				if(b.numerador==1)
					b.numerador++;
				
				c = new Racional(metricaCol3/aInt);
				c=c.add(new Racional(1 + rand.nextInt(10)));

				if(c.numerador==1)
					c.numerador++;
				
				d = a.mult(y);
				e = c.mult(t);

				break;
				
			case IABCXDE: 
				b = new Racional(metricaCol2);
				b=b.add(new Racional(1 + rand.nextInt(10)));
				if(b.numerador==1)
					b.numerador++;
				
				c = new Racional(metricaCol3/aInt);
				c=c.add(new Racional(1 + rand.nextInt(10)));

				if(c.numerador==1)
					c.numerador++;
				
				d = b.mult(y).mult(t);
				e = c.mult(y);
				
				break;
		}
	}
	
	public ProblemaRegraTres clone()
	{
		return new ProblemaRegraTres(texto,coluna1,coluna2,coluna3,tipoProporcao,metricaCol1,metricaCol2,metricaCol3);
	}
	
	public String getPergunta()
	{
		String pergunta=texto;
		pergunta=pergunta.replace("$a", a.toString());
		pergunta=pergunta.replace("$b", b.toString());
		pergunta=pergunta.replace("$c", c.toString());
		if(pergunta.contains("$d"))
			pergunta=pergunta.replace("$d", d.toString());
		if(pergunta.contains("$e"))
			pergunta=pergunta.replace("$e", e.toString());

		return pergunta;
	}
	
	public String getMatriz()
	{
		String matriz="";
		switch(tipoProporcao)
		{
			case ABCX:
				matriz="\\begin{array}[t]{cc}" + " (\\downarrow) \\text{"+coluna1+"} & \\text{"+coluna2+"} (\\downarrow) \\\\ \\hline"
				+ a + "&" + b + "\\\\ \\hline " + c + "& x" + "\\\\ \\hline " + "\\end{array}\\\\\\\\";break;
				
			case ABXC: matriz="\\begin{array}[t]{cc}" + " (\\downarrow) \\text{"+coluna1+"} & \\text{"+coluna2+"} (\\downarrow) \\\\ \\hline"
			+ a + "&" + b + "\\\\ \\hline " + " x &"+ c + "\\\\ \\hline " + "\\end{array}\\\\\\\\";break;	
		
			case IABCX:
				matriz="\\begin{array}[t]{cc}" + " (\\downarrow) \\text{"+coluna1+"} & \\text{"+coluna2+"} (\\uparrow) \\\\ \\hline"
				+ a + "&" + b + "\\\\ \\hline " + c + "& x" + "\\\\ \\hline " + "\\end{array}\\\\\\\\";break;
				
			case IABXC: matriz="\\begin{array}[t]{cc}" + " (\\downarrow) \\text{"+coluna1+"} & \\text{"+coluna2+"} (\\uparrow) \\\\ \\hline"
			+ a + "&" + b + "\\\\ \\hline " + " x &"+ c + "\\\\ \\hline " + "\\end{array}\\\\\\\\";break;	

			case IABCDEX: matriz="\\begin{array}[t]{ccc}" + " (\\downarrow) \\text{"+coluna1+"} & \\text{"+coluna2+"} (\\downarrow) & \\text{"+coluna3+"} (\\uparrow) \\\\ \\hline"
			+ a + "&" + b + "&" + c + "\\\\ \\hline " + d+ "&"+ e +"&"+ " x "+"\\\\ \\hline " + "\\end{array}\\\\\\\\";break;

			case IABCDXE: matriz="\\begin{array}[t]{ccc}" + " (\\downarrow) \\text{"+coluna1+"} & \\text{"+coluna2+"} (\\downarrow) & \\text{"+coluna3+"} (\\uparrow) \\\\ \\hline"
			+ a + "&" + b + "&" + c + "\\\\ \\hline " + d+"&"+ " x "+ "&"+ e +"\\\\ \\hline " + "\\end{array}\\\\\\\\";break;
			
			case IABCXDE: matriz="\\begin{array}[t]{ccc}" + " (\\downarrow) \\text{"+coluna1+"} & \\text{"+coluna2+"} (\\downarrow) & \\text{"+coluna3+"} (\\uparrow) \\\\ \\hline"
			+ a + "&" + b + "&" + c + "\\\\ \\hline " + " x "+"&"+ d + "&"+ e +"\\\\ \\hline " + "\\end{array}\\\\\\\\";break;
		}
		
		return matriz;
	}
	
	public String resolucao()
	{
//		String num1, String den1, String num2, String den2
		String resolucaoLatex = getMatriz();
		MyExpression expressao;
		switch(tipoProporcao)
		{
			case ABCX:
				resolucaoLatex += "\\dfrac{" + a + "}{" + c + "}=\\dfrac{" + b + "}{" + "x" + "}\\\\";
				
				expressao = new MyExpression(a+"x" + "=" + c + "*" + b);
				resolucaoLatex += expressao.resolverLatex();
				break;
				
			case ABXC:
				resolucaoLatex += "\\dfrac{" + a + "}{" + "x" + "}=\\dfrac{" + b + "}{" + c + "}\\\\";
				
				expressao = new MyExpression(b+"x" + "=" + a + "*" + c);
				resolucaoLatex += expressao.resolverLatex();
				break;
				
			case IABCX:
				resolucaoLatex += "\\dfrac{" + a + "}{" + c + "}=\\dfrac{" + "x" + "}{" + b + "}\\\\";
				
				expressao = new MyExpression(c+"x" + "=" + a + "*" + b);
				resolucaoLatex += expressao.resolverLatex();
				break;
				
			case IABXC:
				resolucaoLatex += "\\dfrac{" + a + "}{" + "x" + "}=\\dfrac{" + c + "}{" + b + "}\\\\";
				
				expressao = new MyExpression(c+"x" + "=" + a + "*" + b);
				resolucaoLatex += expressao.resolverLatex();
				break;
				
			case IABCDEX:
				resolucaoLatex += "\\dfrac{" + a + "}{" + d + "}=\\dfrac{" + b + "}{" + e + "} \\cdot \\dfrac{" + "x" + "}{" + c + "}\\\\";
				
				expressao = new MyExpression(d + "*" + b + "x"+ "=" + a+"*"+e+ "*" +c);
				resolucaoLatex += expressao.resolverLatex();
				break;
				
			case IABCDXE:
				resolucaoLatex += "\\dfrac{" + a + "}{" + d + "}=\\dfrac{" + b + "}{" + "x" + "} \\cdot \\dfrac{" + e + "}{" + c + "}\\\\";
				
				expressao = new MyExpression(a + "*" + c + "x"+ "=" + d+"*"+b+ "*" +e);
				resolucaoLatex += expressao.resolverLatex();
				break;
				
			case IABCXDE: 
				resolucaoLatex += "\\dfrac{" + a + "}{" + "x" + "}=\\dfrac{" + b + "}{" + d + "} \\cdot \\dfrac{" + e + "}{" + c + "}\\\\";
				
				expressao = new MyExpression(b + "*" + e + "x"+ "=" + a+ "*" +d+ "*" +c);
				resolucaoLatex += expressao.resolverLatex();
				break;
				
		}
		
		return resolucaoLatex;
	}
	
	public Racional resultado()
	{
		Racional x=null;
		switch(tipoProporcao)
		{
			case ABCX:
				x=c.mult(b).div(a);break;
				
			case AXBC,ABXC:
				x= a.mult(c).div(b);break;
			
			case XABC:
				x= a.mult(b).div(c);break;
				
			case IABCX,IABXC:
				x=a.mult(b).div(c);break;
			
			case IABCDEX:
				x=a.mult(e).mult(c).div(d).div(b);break;
				
			case IABCDXE:
				x=d.mult(b).mult(e).div(a).div(c);break;
				
			case IABCXDE: 
				x=a.mult(d).mult(c).div(b).div(e);break;

		}
		
		x.fatoracao(2);
		return x;
	}
}
