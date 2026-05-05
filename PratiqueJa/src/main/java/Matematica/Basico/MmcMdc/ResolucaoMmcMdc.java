package Matematica.Basico.MmcMdc;

import Matematica.DefinicaoCores;

public class ResolucaoMmcMdc
{
	public static String mmc(int a, int b)
	{
		String resolucaoLatex="";
		int divisor=2;
		
		resolucaoLatex = "\\begin{array}[t]{r r|l}";
		String resultado="";
		int mmc=1;
		int cont=0;
		while(a!=1||b!=1)
		{
			if(a%divisor==0||b%divisor==0)
			{
				resolucaoLatex+=a+",&"+ b+"&"+divisor+"\\\\";
				mmc*=divisor;
				
				if(cont==0)
					resultado+=divisor;
				else
					resultado+=" \\cdot "+divisor;

				if(a%divisor==0)
					a/=divisor;
				
				if(b%divisor==0)
					b/=divisor;
				
				cont++;
			}
			else
				divisor++;
		}

		resultado=ajusteResultado(resultado);
		
		resolucaoLatex+="\\hline";
		if(cont<=1)
			resolucaoLatex+=a+",&"+ b+"&"+mmc+"\\\\";
		else
			resolucaoLatex+=a+",&"+ b+"&"+resultado+"="+mmc+"\\\\";

		resolucaoLatex+="\\end{array}";
		return resolucaoLatex;
	}
	
	private static String ajusteResultado(String resultado)
	{
		String []list=resultado.split(" \\\\cdot ");
		
		int potencia=1;
		resultado="";
		String termo="", prox="";
		for(int i = 0; i < list.length-1; i++)
		{
			termo = list[i];
			prox = list[i+1];
			
			if(termo.equals(prox))
				potencia++;
			else
			{
				if(potencia>1)
					resultado+=termo+"^{"+potencia+"}  \\cdot ";
				else
					resultado+=termo+" \\cdot ";
				
				potencia=1;
			}
		}
		
		if(potencia>1)
			resultado+=prox+"^{"+potencia+"}";
		else
			resultado+=prox;
		
		return resultado;
	}
	
	public static String mmc(int a, int b, int c)
	{
		String resolucaoLatex="";
		int divisor=2;
		
		resolucaoLatex = "\\begin{array}[t]{r r r|l}";
		String resultado="";
		int mmc=1;
		int cont=0;
		while(a!=1||b!=1||c!=1)
		{
			if(a%divisor==0||b%divisor==0||c%divisor==0)
			{
				resolucaoLatex+=a+",&"+ b+",&"+ c+"&"+divisor+"\\\\";
				
				mmc*=divisor;
				
				if(cont==0)
					resultado+=divisor;
				else
					resultado+=" \\cdot "+divisor;

				if(a%divisor==0)
					a/=divisor;
				
				if(b%divisor==0)
					b/=divisor;
				
				if(c%divisor==0)
					c/=divisor;
				
				cont++;
			}
			else
				divisor++;
		}
		
		resultado=ajusteResultado(resultado);
		
		resolucaoLatex+="\\hline";
		if(cont<=1)
			resolucaoLatex+=a+",&"+ b+",&"+ c+"&"+mmc+"\\\\";
		else
			resolucaoLatex+=a+",&"+ b+",&"+ c+"&"+resultado+"="+mmc+"\\\\";
		
		resolucaoLatex+="\\end{array}";
		return resolucaoLatex;
	}
	
	public static String mdc(int a, int b)
	{
		String resolucaoLatex="";
		int divisor=2;
		resolucaoLatex ="";
		resolucaoLatex += DefinicaoCores.irisBabypink();
		resolucaoLatex += "\\begin{array}[t]{r r|l}";
		String resultado="";
		int mmc=1;
		int cont=0;
		while(a!=1||b!=1)
		{
			if(a%divisor==0||b%divisor==0)
			{
				if(a%divisor==0&&b%divisor==0)
				{
					resolucaoLatex+="\\textcolor{iris}{"+a+"},& \\textcolor{iris}{"+ b+"}&\\textcolor{iris}{"+divisor+"}\\\\";

					mmc*=divisor;
					
					if(cont==0)
						resultado+=divisor;
					else
						resultado+=" \\cdot "+divisor;
					
					cont++;
				}
				else
					resolucaoLatex+=a+",&"+ b+"&"+divisor+"\\\\";

				if(a%divisor==0)
					a/=divisor;
				
				if(b%divisor==0)
					b/=divisor;
			}
			else
				divisor++;
		}
		
		resultado=ajusteResultado(resultado);
		
		resolucaoLatex+="\\hline";
		if(cont<=1)
			resolucaoLatex+=a+",&"+ b+"&"+mmc+"\\\\";
		else
			resolucaoLatex+=a+",&"+ b+"&"+resultado+"="+mmc+"\\\\";

		resolucaoLatex+="\\end{array}";
		return resolucaoLatex;
	}
	
	public static String mdc(int a, int b, int c)
	{
		String resolucaoLatex="";
		int divisor=2;
		resolucaoLatex ="";
		resolucaoLatex += DefinicaoCores.irisBabypink();
		resolucaoLatex += "\\begin{array}[t]{r r r|l}";
		String resultado="";
		int mmc=1;
		int cont=0;
		while(a!=1||b!=1||c!=1)
		{
			if(a%divisor==0||b%divisor==0||c%divisor==0)
			{
				if(a%divisor==0&&b%divisor==0&&c%divisor==0)
				{
					resolucaoLatex+="\\textcolor{iris}{"+a+"},& \\textcolor{iris}{"+ b+"},& \\textcolor{iris}{"+ c+"}&\\textcolor{iris}{"+divisor+"}\\\\";

					mmc*=divisor;
					
					if(cont==0)
						resultado+=divisor;
					else
						resultado+=" \\cdot "+divisor;
					
					cont++;
				}
				else
					resolucaoLatex+=a+",&"+ b+",&"+ c+"&"+divisor+"\\\\";

				if(a%divisor==0)
					a/=divisor;
				
				if(b%divisor==0)
					b/=divisor;
				
				if(c%divisor==0)
					c/=divisor;
			}
			else
				divisor++;
		}
		
		resultado=ajusteResultado(resultado);

		resolucaoLatex+="\\hline";
		if(cont<=1)
			resolucaoLatex+=a+",&"+ b+",&"+ c+"&"+mmc+"\\\\";
		else
			resolucaoLatex+=a+",&"+ b+",&"+ c+"&"+resultado+"="+mmc+"\\\\";

		resolucaoLatex+="\\end{array}";
		return resolucaoLatex;
	}
	
	public static void main(String[] args)
	{
	}

}