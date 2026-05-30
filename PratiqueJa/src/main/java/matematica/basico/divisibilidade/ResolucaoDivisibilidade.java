package matematica.basico.divisibilidade;

public class ResolucaoDivisibilidade
{

	public static String resolucao2(boolean resultadoCorretoBol,int number)
	{
		String resolucaoLatex = "";

		if(resultadoCorretoBol)
		{
			resolucaoLatex += "\\text{O número "+number + " é par,}\\\\";
			resolucaoLatex += "\\text{por isso é divisível por 2.}";
		}
		else
		{
			resolucaoLatex += "\\text{O número "+number + " não é par,}\\\\";
			resolucaoLatex += "\\text{por isso não é divisível por 2.}";
		}
		return resolucaoLatex;
	}

	public static String resolucao10(boolean resultadoCorretoBol,int number)
	{
		String resolucaoLatex = "";

		if(resultadoCorretoBol)
		{
			resolucaoLatex += "\\text{O número "+number + " termina em 0,}\\\\";
			resolucaoLatex += "\\text{por isso é divisível por 10.}";
		}
		else
		{
			resolucaoLatex += "\\text{O número "+number + " não termina em 0,}\\\\";
			resolucaoLatex += "\\text{por isso não é divisível por 10.}";
		}
		return resolucaoLatex;
	}
	
	public static String resolucao5(boolean resultadoCorretoBol,int number)
	{
		String resolucaoLatex = "";

		if(resultadoCorretoBol)
		{
			resolucaoLatex += "\\text{O número "+number + " termina em 0 ou 5,}\\\\";
			resolucaoLatex += "\\text{por isso é divisível por 5.}";
		}
		else
		{
			resolucaoLatex += "\\text{O número "+number + " não termina em 0 ou 5,}\\\\";
			resolucaoLatex += "\\text{por isso não é divisível por 5.}";
		}
		return resolucaoLatex;
	}
	
	public static String resolucao3(boolean resultadoCorretoBol,int number)
	{
		String resolucaoLatex = "";
		String numberStr=String.valueOf(number);
		int somaAlgarismos=0;
		String somaAlgarismosStr="";
		for(int i = 0; i < numberStr.length(); i++)
		{
			somaAlgarismos+=Integer.valueOf(""+numberStr.charAt(i));
			somaAlgarismosStr+=""+numberStr.charAt(i);
			if(i<(numberStr.length()-1))
				somaAlgarismosStr+="+";
		}
		
		resolucaoLatex += "\\text{A soma dos algarismos é } \\\\"
		+somaAlgarismosStr+"="+somaAlgarismos+".\\\\";
		
		if(resultadoCorretoBol)
		{
			resolucaoLatex +="\\text{Como "+somaAlgarismos+" é múltiplo de 3,}\\\\";
			resolucaoLatex += "\\text{"+numberStr+" é divisível por 3.}";
		}
		else
		{
			resolucaoLatex +="\\text{Como "+somaAlgarismos+" não é múltiplo de 3,}\\\\";
			resolucaoLatex += "\\text{"+numberStr+" não é divisível por 3.}";
		}
		
		return resolucaoLatex;
	}

	
	public static String resolucao6(boolean resultadoCorretoBol,int number)
	{
		String resolucaoLatex = "";
		String numberStr=String.valueOf(number);
		int somaAlgarismos=0;
		for(int i = 0; i < numberStr.length(); i++)
		{
			somaAlgarismos+=Integer.valueOf(""+numberStr.charAt(i));
		}
		
		boolean divisível2=(number%2)==0;

		if(resultadoCorretoBol)
		{
			resolucaoLatex += "\\text{O número }"+number+"\\text{ é divisível por 2, }\\\\";
			resolucaoLatex += "\\text{e a soma dos seus algarismos é}\\\\";
			resolucaoLatex += "\\text{"+somaAlgarismos +", que é múltiplo de 3.}\\\\";
			resolucaoLatex += "\\text{Portanto, "+numberStr+" é divisível por 6.}";
		}
		else
		{
			if(!divisível2)
			{
				resolucaoLatex += "\\text{O número "+number+" não é par, }\\\\";
				resolucaoLatex += "\\text{por isso não é divisível por 6.}";
			}
			else
			{
				resolucaoLatex += "\\text{O número }"+number+"\\text{ é divisível por 2,}\\\\";
				resolucaoLatex += "\\text{mas não é divisível por 3.}\\\\";
				resolucaoLatex += "\\text{A soma dos seus algarismos é} \\\\";
				resolucaoLatex += "\\text{"+somaAlgarismos +", que não é múltiplo de 3.}\\\\";
				resolucaoLatex += "\\text{Portanto, "+numberStr+" não é divisível por 6.}";
			}
		}
		return resolucaoLatex;
	}
	
	public static String resolucao9(boolean resultadoCorretoBol,int number)
	{
		String resolucaoLatex = "";
		String numberStr=String.valueOf(number);
		int somaAlgarismos=0;
		String somaAlgarismosStr="";
		for(int i = 0; i < numberStr.length(); i++)
		{
			somaAlgarismos+=Integer.valueOf(""+numberStr.charAt(i));
			somaAlgarismosStr+=""+numberStr.charAt(i);
			if(i<(numberStr.length()-1))
				somaAlgarismosStr+="+";
		}
		
		resolucaoLatex += "\\text{A soma dos algarismos é } \\\\"
		+somaAlgarismosStr+"="+somaAlgarismos+".\\\\";
		
		if(resultadoCorretoBol)
		{
			resolucaoLatex +="\\text{Como "+somaAlgarismos+" é múltiplo de 9,}\\\\";
			resolucaoLatex += "\\text{"+numberStr+" é divisível por 9.}";
		}
		else
		{
			resolucaoLatex +="\\text{Como "+somaAlgarismos+" não é múltiplo de 9,}\\\\";
			resolucaoLatex += "\\text{"+numberStr+" não é divisível por 9.}";
		}
		
		return resolucaoLatex;
	}
	
	public static String resolucao4(boolean resultadoCorretoBol,int number)
	{
		String resolucaoLatex = "";
		String numberStr=String.valueOf(number);
		int valor=0;
		boolean maior2Digitos=(numberStr.length()>=2);
		
		if(maior2Digitos)
			valor=Integer.valueOf(""+numberStr.charAt(numberStr.length()-2)+numberStr.charAt(numberStr.length()-1));
		
		if(resultadoCorretoBol)
		{
			if(maior2Digitos)
			{
				resolucaoLatex += "\\text{O número formado pelos dois }\\\\"
				+"\\text{últimos algarismos de "+number+" é "+valor+",}\\\\"
				+"\\text{que é múltiplo de 4.}\\\\";
				resolucaoLatex += "\\text{Portanto, "+numberStr+" é divisível por 4.}";
			}
			else
			{
				resolucaoLatex += "\\text{O número "+number+ " é múltiplo de 4.}";
			}
		}
		else
		{
			if(maior2Digitos)
			{
				resolucaoLatex += "\\text{O número formado pelos dois}\\\\"
				+"\\text{últimos algarismos de "+number+" é "+valor+",}\\\\"
				+"\\text{que não é múltiplo de 4.}\\\\";
				resolucaoLatex += "\\text{Portanto, "+numberStr+" não é divisível por 4.}";
			}
			else
			{
				resolucaoLatex += "\\text{O número "+number+ " não é múltiplo de 4.}";
			}
		}
		return resolucaoLatex;
	}
	
	public static String resolucao8(boolean resultadoCorretoBol,int number)
	{
		String resolucaoLatex = "";
		String numberStr=String.valueOf(number);
		int valor=0;
		boolean maior3Digitos=(numberStr.length()>=3);
		
		if(maior3Digitos)
			valor=Integer.valueOf(""+numberStr.charAt(numberStr.length()-3)+numberStr.charAt(numberStr.length()-2)+numberStr.charAt(numberStr.length()-1));
		
		if(resultadoCorretoBol)
		{
			if(maior3Digitos)
			{
				resolucaoLatex += "\\text{O número formado pelos três }\\\\"
				+"\\text{últimos algarismos de "+number+" é "+valor+",}\\\\"
				+"\\text{que é múltiplo de 8.}\\\\";
				resolucaoLatex += "\\text{Portanto, "+numberStr+" é divisível por 8.}";
			}
			else
			{
				resolucaoLatex += "\\text{O número "+number+ " é múltiplo de 8.}";
			}
		}
		else
		{
			if(maior3Digitos)
			{
				resolucaoLatex += "\\text{O número formado pelos três }\\\\"
				+"\\text{últimos algarismos de "+number+" é "+valor+",}\\\\"
				+"\\text{que não é múltiplo de 8.}\\\\";
				resolucaoLatex += "\\text{Portanto, "+numberStr+" não é divisível por 8.}";
			}
			else
			{
				resolucaoLatex += "\\text{O número "+number+ " não é múltiplo de 8.}";
			}
		}
		return resolucaoLatex;
	}
	
	public static String resolucao7(boolean resultadoCorretoBol,int number)
	{
		String resolucaoLatex = "";
		String resultadoStr=String.valueOf(number);
		int ultimoDigito;
		int sobraDigito;
		int resultado=number;
		do
		{
			resultadoStr=String.valueOf(resultado);
			ultimoDigito=Integer.valueOf(""+resultadoStr.charAt(resultadoStr.length()-1));
			sobraDigito=Integer.valueOf(""+resultadoStr.substring(0, resultadoStr.length()-1));
			
			resolucaoLatex += "\\text{O último dígito de "+resultado+" é "+ultimoDigito+"}\\\\";
			resultado=sobraDigito-(ultimoDigito*2);
			resolucaoLatex += ultimoDigito+"\\cdot 2 = "+(ultimoDigito*2)+"\\rightarrow"+sobraDigito+"-"+(ultimoDigito*2)+"="+resultado+"\\\\";
		}
		while(String.valueOf(resultado).length()>2);
		
		if(resultadoCorretoBol)
		{
			resolucaoLatex += "\\text{O número "+resultado+" é múltiplo de 7.}\\\\";
			resolucaoLatex += "\\text{Portanto, "+number+" é divisível por 7.}";
		}
		else
		{
			resolucaoLatex += "\\text{O número "+resultado+" não é múltiplo de 7.}\\\\";
			resolucaoLatex += "\\text{Portanto, "+number+" não é divisível por 7.}";
		}
	
		return resolucaoLatex;
	}

}