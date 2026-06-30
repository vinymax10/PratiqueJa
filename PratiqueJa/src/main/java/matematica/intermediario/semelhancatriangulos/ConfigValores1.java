package matematica.intermediario.semelhancatriangulos;

import java.util.Arrays;

import matematica.Racional;
import matematica.expressao.MyExpression;


public class ConfigValores1 extends ConfigValores
{
	public ConfigValores1(int number,boolean hasExpressao) 
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
		intA = vet[0];
		intB = vet[1];
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
				a = new Racional(intB * intC);
				a = a.div(new Racional(intD));
				a.fatoracao(2);
				incognita=a;
				
				choiceNumbers(a);
				resolucaoLatex="\\dfrac{"+nome+"}{"+ intB+"}=\\dfrac{"+ intC+"}{"+ intD+"}\\\\";
				
				if(hasExpressao)
					expressao=d+"*("+aExp + "x+" + bExp+")="+b+"*"+c;
				else
					expressao=d+nome+"="+b+"*"+c;
				
				break;
			}
			
			case 1: 
			{
				b = new Racional(intA * intD);
				b = b.div(new Racional(intC));
				b.fatoracao(2);
				incognita=b;
				
				choiceNumbers(b);
				resolucaoLatex="\\dfrac{"+nome+"}{"+ intA+"}=\\dfrac{"+ intD+"}{"+ intC+"}\\\\";
				if(hasExpressao)
					expressao=c+"*("+aExp + "x+" + bExp+")="+a+"*"+d;
				else
					expressao=c+nome+"="+a+"*"+d;
				
				break;
			}
			
			case 2: 
			{
				c = new Racional(intA * intD);
				c = c.div(new Racional(intB));
				c.fatoracao(2);
				incognita=c;
				
				choiceNumbers(c);
				resolucaoLatex="\\dfrac{"+nome+"}{"+ intD+"}=\\dfrac{"+ intA+"}{"+ intB+"}\\\\";

				if(hasExpressao)
					expressao=b+"*("+aExp + "x+" + bExp+")="+a+"*"+d;
				else
					expressao=b+nome+"="+a+"*"+d;
				
				break;
			}
			
			case 3: 
			{
				d = new Racional(intB * intC);
				d = d.div(new Racional(intA));
				d.fatoracao(2);
				incognita=d;
				
				choiceNumbers(d);
				resolucaoLatex="\\dfrac{"+nome+"}{"+ intC+"}=\\dfrac{"+ intB+"}{"+ intA+"}\\\\";
				if(hasExpressao)
					expressao=a+"*("+aExp + "x+" + bExp+")="+b+"*"+c;
				else
					expressao=a+nome+"="+b+"*"+c;
				
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
