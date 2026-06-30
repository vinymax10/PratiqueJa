package matematica.intermediario.semelhancatriangulos;

import java.util.Arrays;

import matematica.Racional;
import matematica.expressao.MyExpression;


public class ConfigValores2 extends ConfigValores
{
	public ConfigValores2(int number,boolean hasExpressao) 
	{
		super();
		
		this.hasExpressao=hasExpressao;

		int intA = 5 + rand.nextInt(10);
		int intB = 5 + rand.nextInt(10);
		int intC = 5 + rand.nextInt(10);
		int intD = 5 + rand.nextInt(10);

		while (intA == intB)
			intB = 5 + rand.nextInt(10);

		while (intC == intB || intC == intA)
			intC = 5 + rand.nextInt(10);

		while (intD == intB || intD == intA || intD == intC)
			intD = 5 + rand.nextInt(10);

		int vet[] =
			{ intA, intB, intC, intD };

		Arrays.sort(vet);
		intB = vet[0];
		intA = vet[1];
		intC = vet[2];
		intD = vet[3];
		
		MyExpression resolucao;
		String expressao="";
		
		a=new Racional(intA);
		b=new Racional(intB);
		c=new Racional(intC);
		d=new Racional(intD);
		
		nome="x";
		
		switch(number)
		{
			case 0: 
			{
				a = b.mult(c).div(d.minus(b));
				a.fatoracao(2);
				incognita=a;
				
				choiceNumbers(a);
				
				resolucaoLatex="\\dfrac{"+nome+"}{"+nome+"+"+ intC+"}=\\dfrac{"+ intB+"}{"+ intD+"}\\\\";
				if(hasExpressao)
					expressao=d+"*("+aExp + "x+" + bExp+")="+b+"*("+aExp + "x+" + bExp+"+"+c+")";
				else
					expressao=d+nome+"="+b+"*("+nome+"+"+c+")";

				break;
			}
			
			case 1: 
			{
				b = a.mult(d).div(a.add(c));
				b.fatoracao(2);
				incognita=b;
				
				choiceNumbers(b);
				
				resolucaoLatex="\\dfrac{"+nome+"}{"+ intD+"}=\\dfrac{"+ intA+"}{"+intA+"+"+ intC+"}\\\\";
				if(hasExpressao)
					expressao="("+aExp + "x+" + bExp+")*("+a+"+"+c+")="+a+"*"+d;
				else
					expressao=nome+"*("+a+"+"+c+")="+a+"*"+d;
				break;
			}
			
			case 2: 
			{
				c = a.mult(d).minus(b.mult(a)).div(b);
				c.fatoracao(2);
				incognita=c;
				
				choiceNumbers(c);
				
				resolucaoLatex="\\dfrac{"+b+"}{"+ intD+"}=\\dfrac{"+ intA+"}{"+intA+"+"+nome+"}\\\\";
				if(hasExpressao)
					expressao=b+"*("+a+"+"+aExp + "x+" + bExp+")="+a+"*"+d;
				else
					expressao=b+"*("+a+"+"+nome+")="+a+"*"+d;
				break;
			}
			
			case 3: 
			{
				d = b.mult(a).add(b.mult(c)).div(a);
				d.fatoracao(2);
				incognita=d;
				
				choiceNumbers(d);
				
				resolucaoLatex="\\dfrac{"+nome+"}{"+ intB+"}=\\dfrac{"+intA+"+"+ intC+"}{"+ intA+"}\\\\";
				
				if(hasExpressao)
					expressao=a+"*("+aExp + "x+" + bExp+")="+b+"*("+a+"+"+c+")";
				else
					expressao=nome+"*"+a+"="+b+"*("+a+"+"+c+")";
				
				break;
			}
		}
		
		resolucao = new MyExpression(expressao);
		resolucaoLatex += resolucao.resolverLatex();
		resolucaoPassos = resolucaoLatex.split("\\\\\\\\");
		if (resolucaoPassos.length > 0)
		{
			String ultimo = resolucaoPassos[resolucaoPassos.length - 1].trim();
			int lastEq = ultimo.lastIndexOf('=');
			if (lastEq >= 0)
				resolucaoPassos[resolucaoPassos.length - 1] = ultimo.substring(0, lastEq + 1) + "\\mathbf{" + ultimo.substring(lastEq + 1).trim() + "}";
		}
	}
	
	
}
