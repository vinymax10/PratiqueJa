package Matematica.Avancado.NumerosComplexos;

import java.util.Random;

import Matematica.Racional;
import Matematica.Avancado.NumerosComplexos.Expressao.ExpressionNC;

public class NumeroComplexo
{
	public boolean realRaiz;
	public Racional real;
	public Racional imaginaria;
	public Racional modulo;

	public static NumeroComplexo contruir(int limite)
	{
		Random rand =new Random();
		NumeroComplexo novo=new NumeroComplexo();
		novo.real=new Racional(-limite+rand.nextInt(limite*2));
		novo.imaginaria=new Racional(-limite+rand.nextInt(limite*2));
		while(novo.imaginaria.isZero())
			novo.imaginaria=new Racional(-limite+rand.nextInt(limite*2));

		return novo;
	}
	
	public static NumeroComplexo contruirFrac(int limite)
	{
		Random rand =new Random();
		NumeroComplexo novo=new NumeroComplexo();
		novo.real=new Racional(-limite+rand.nextInt(limite*2),1+rand.nextInt(limite));
		novo.imaginaria=new Racional(-limite+rand.nextInt(limite*2),1+rand.nextInt(limite));
		while(novo.imaginaria.isZero())
			novo.imaginaria=new Racional(-limite+rand.nextInt(limite*2),1+rand.nextInt(limite));

		return novo;
	}
	
	public static NumeroComplexo contruirModulo(int limite)
	{
		int base,altura,hipotenusa;
		Random rand =new Random();

		hipotenusa = 2 + rand.nextInt(limite);
		altura = 2 + rand.nextInt(limite);
//		hipotenusa=5;
//		altura=3;
		while(hipotenusa <= altura||((hipotenusa * hipotenusa - altura * altura)<altura))
		{
			altura = 2 + rand.nextInt(limite);
			hipotenusa = 2 + rand.nextInt(limite);
		}
		
		base = hipotenusa * hipotenusa - altura * altura;
			
		NumeroComplexo novo=new NumeroComplexo();
		
		if(Math.sqrt(base) % 1 == 0)
		{
			base=(int)Math.sqrt(base);
			novo.realRaiz=false;
		}
		else
			novo.realRaiz=true;
		
		novo.real=new Racional(base);
		novo.imaginaria=new Racional(altura);
		novo.modulo=new Racional(hipotenusa);
		return novo;
	}
	
	public NumeroComplexo add(NumeroComplexo x)
	{
		NumeroComplexo novo=new NumeroComplexo();
		novo.real=this.real.add(x.real);
		novo.imaginaria=this.imaginaria.add(x.imaginaria);
		novo.fatoracao();

		return novo;
	}
	
	public NumeroComplexo minus(NumeroComplexo x)
	{
		NumeroComplexo novo=new NumeroComplexo();
		novo.real=this.real.minus(x.real);
		novo.imaginaria=this.imaginaria.minus(x.imaginaria);
		novo.fatoracao();

		return novo;
	}
	
	public NumeroComplexo mult(NumeroComplexo x)
	{
		NumeroComplexo novo=new NumeroComplexo();
		Racional ac= this.real.mult(x.real);
		Racional ad= this.real.mult(x.imaginaria);
		Racional cb= this.imaginaria.mult(x.real);
		Racional bd= this.imaginaria.mult(x.imaginaria);
		novo.real=ac.minus(bd);
		novo.imaginaria=ad.add(cb);
		novo.fatoracao();
		
		return novo;
	}
	
	public void fatoracao()
	{
		real.fatoracao(2);
		imaginaria.fatoracao(2);
	}
	public NumeroComplexo div(NumeroComplexo x)
	{
		NumeroComplexo conjugadoX=x.conjugado();
		NumeroComplexo numerador=mult(conjugadoX);
		NumeroComplexo denominador=x.mult(conjugadoX);
		NumeroComplexo novo=new NumeroComplexo();
		novo.real=new Racional(numerador.real.numerador,denominador.real.numerador);
		novo.imaginaria=new Racional(numerador.imaginaria.numerador,denominador.real.numerador);
		novo.fatoracao();

		return novo;
	}
	
	public NumeroComplexo conjugado()
	{
		NumeroComplexo novo=new NumeroComplexo();
		novo.real=real;
		novo.imaginaria=imaginaria.trocaSinal();
		
		return novo;
	}

	@Override
	public String toString()
	{
		String str="";
		
		str+=toStringReal();
		
		str+=toStringImaginaria();
		
		if(real.numerador==0&&imaginaria.numerador==0)
			str+=0;
		
		return str;
	}
	
	public String toStringSemLatex()
	{
		String str="";
		
		str+=toStringRealSemLatex();
		
		str+=toStringImaginariaSemLatex();
		
		if(real.numerador==0&&imaginaria.numerador==0)
			str+=0;
		
		return str;
	}
	
	public String toStringImaginaria()
	{
		String str="";
		if(!imaginaria.isZero())
			str+=imaginaria.dFracSinal(real.isZero(),true)+"i";

		return str;
	}
	
	public String toStringImaginariaSemLatex()
	{
		String str="";
		
		if(!imaginaria.isZero())
		{
			if(imaginaria.positivo()&&!real.isZero())
				str+="+";
			else if(imaginaria.denominador==1&&imaginaria.numerador==-1)
				str+="-";
			
			if(imaginaria.denominador!=1||imaginaria.numerador!=-1&&imaginaria.numerador!=1)
				str+=imaginaria;
			
			str+="i";
		}
		
		return str;
	}
	
	public String toStringReal()
	{
		String str="";
		if(real.numerador!=0)
		{
			if(realRaiz)
				str+="\\sqrt{"+real.showDfrac()+"}";
			else
				str+=real.dFracSinal(true,false);
		}
		
		return str;
	}
	
	public String toStringRealSemLatex()
	{
		String str="";
		if(real.numerador!=0)
		{
			if(realRaiz)
				str+="\\sqrt{"+real+"}";
			else
				str+=real;
		}
		
		return str;
	}
	
	
	public String resolucaoModulo()
	{
		String resolucaoLatex="";
		long hipotenusa=modulo.numerador;
		long catetoMaiorQuad=real.numerador;
		long catetoMenor=imaginaria.numerador;
		resolucaoLatex = "|z|^2 = a^2 + b^2 \\\\";
		resolucaoLatex += "a=" + getSqrt(catetoMaiorQuad) + ", ~~ b=" + catetoMenor + "\\\\";
		resolucaoLatex += "|z|^2 =" + getSqrt(catetoMaiorQuad) + "^2+" + catetoMenor + "^2" + "\\\\";
		resolucaoLatex += "|z|^2 =" + catetoMaiorQuad + "+" + (catetoMenor * catetoMenor) + " = " + (catetoMaiorQuad + catetoMenor * catetoMenor) + " \\\\";
		resolucaoLatex += "|z| = \\sqrt{" + (catetoMaiorQuad + catetoMenor * catetoMenor) + "}" + " = " + hipotenusa ;
		
		return resolucaoLatex;
	}
	
	private static String getSqrt(long x)
	{
		if(Math.sqrt(x) % 1 == 0)
			return "" + (int) Math.sqrt(x);
		else
			return "\\sqrt{" + (x) + "}";
	}
	
	public String resolucaoSoma(NumeroComplexo x)
	{
		String resolucaoLatex="";
	
		ExpressionNC expressao = new ExpressionNC(real+"+"+imaginaria+"i+"+x.real+"+"+x.imaginaria+"i");
		resolucaoLatex = expressao.resolverLatex();

		return resolucaoLatex;
	}
	
	public String resolucaoMinus(NumeroComplexo x)
	{
		String resolucaoLatex="";
		ExpressionNC expressao = new ExpressionNC(real+"+"+imaginaria+"i+"+x.real.trocaSinal()+"+"+x.imaginaria.trocaSinal()+"i");
		resolucaoLatex = expressao.resolverLatex();
		
		return resolucaoLatex;
	}
	
	public String resolucaoMultiplicacao(NumeroComplexo x)
	{
		Racional ac= this.real.mult(x.real);
		Racional ad= this.real.mult(x.imaginaria);
		Racional cb= this.imaginaria.mult(x.real);
		Racional bd= this.imaginaria.mult(x.imaginaria);
		String resolucaoLatex="";
		
		ExpressionNC expressao = new ExpressionNC(ac+"+"+ad+"i+"+cb+"i+"+bd+"i^2");
		resolucaoLatex = expressao.resolverLatex();
		
		return resolucaoLatex;
	}
	
	public String resolucaoQuadrado()
	{
		String resolucaoLatex="("+this+") \\cdot ("+this+")=\\\\";
		resolucaoLatex+=resolucaoMultiplicacao(this);
		
		return resolucaoLatex;
	}
	
	public String resolucaoDivisao(NumeroComplexo x)
	{
		NumeroComplexo conjugadoX=x.conjugado();
		String numeradorResolucao=resolucaoMultiplicacao(conjugadoX);
		String denominadorResolucao=x.resolucaoMultiplicacao(conjugadoX);
		numeradorResolucao=numeradorResolucao.replaceAll("\\\\\\\\", "");
		denominadorResolucao=denominadorResolucao.replaceAll("\\\\\\\\", "");
		String []listNumerador=numeradorResolucao.split("=");
		String []listDenominador=denominadorResolucao.split("=");
//		for(String string : listDenominador)
//			System.out.println(string);
//		
//		for(String string : listNumerador)
//			System.out.println(string);
		
		int contNumerador=0,contDenominador=0;
		int limite=Math.max(listNumerador.length, listDenominador.length);
		String denominador,numerador;
		String resolucaoLatex="\\dfrac{("+this+")\\cdot("+conjugadoX+")}{("+x+")\\cdot("+conjugadoX+")}=\\\\";
		for(int i = 0; i < limite; i++)
		{
			numerador = listNumerador[contNumerador++];
			denominador = listDenominador[contDenominador++];
			resolucaoLatex+="\\dfrac{"+numerador+"}{"+denominador+"}";
			if(i<(limite-1))
				resolucaoLatex+="=";
			if(i<(limite-2))
				resolucaoLatex+="\\\\";
			
			if(listNumerador.length<=contNumerador)
				contNumerador=listNumerador.length-1;
			
			if(listDenominador.length<=contDenominador)
				contDenominador=listDenominador.length-1;
		}
		
		resolucaoLatex+="=\\\\"+div(x);
		
		return resolucaoLatex;
	}
	
}
