package matematica.basico.divisibilidade;

import java.util.ArrayList;
import java.util.List;

import matematica.DefinicaoCores;

public class ResolucaoDivisores
{
	List<Integer> lista=new ArrayList<Integer>();

	public String numerosDividores(int number)
	{
		String resolucaoLatex="";
		resolucaoLatex+=fatoresPrimos(number)+"\\\\ \\\\";
		Integer integer;
		resolucaoLatex+="\\text{Divisores}\\\\";
		String listDivisores="";
		for (int i = 0; i < lista.size(); i++) 
		{
			integer = lista.get(i);
			listDivisores+=integer+"";
			if(i< lista.size()-1)
				listDivisores+=",~";
		}
		
		listDivisores=addBreakLine(listDivisores,",~");
		resolucaoLatex+=listDivisores;
		
		resolucaoLatex+="\\\\";
		resolucaoLatex+="\\text{Número de divisores: }"+lista.size();

		return resolucaoLatex;
	}
	
	public String somaDividores(int number)
	{
		String resolucaoLatex="";
		resolucaoLatex+=fatoresPrimos(number)+"\\\\ \\\\";
		Integer integer;
		resolucaoLatex+="\\text{Soma dos divisores}\\\\";
		int soma=0;
		String listDivisores="";
		for (int i = 0; i < lista.size(); i++) 
		{
			integer = lista.get(i);
			soma+=integer;
			listDivisores+=integer+"";
			if(i< lista.size()-1)
				listDivisores+="+";
		}
		
		listDivisores=addBreakLine(listDivisores,"\\+");
		resolucaoLatex+=listDivisores+"="+soma;

		return resolucaoLatex;
	}
	
	public String numerosDividoresParesImpares(int number, boolean par)
	{
		String tipo="pares";
		int resto=0;
		if(!par)
		{
			resto=1;
			tipo="ímpares";
		}
		
		String resolucaoLatex="";
		resolucaoLatex+=fatoresPrimosPares(number,par)+"\\\\ \\\\";
		Integer integer;
		int cont=0;
		String listDivisores="";
		for (int i = 0; i < lista.size(); i++) 
		{
			integer = lista.get(i);
			if(integer%2==resto)
			{
				cont++;
				if(cont==1)
					listDivisores+="\\textcolor{iris}{"+integer+"}";
				else 
					listDivisores+=",~"+"\\textcolor{iris}{"+integer+"}";
			}
		}
		
		if(cont>0)
		{
			resolucaoLatex+="\\text{Divisores "+tipo+"}\\\\";
			listDivisores=addBreakLine(listDivisores,",~");
			resolucaoLatex+=listDivisores+"\\\\";
		}
		resolucaoLatex+="\\text{Número de divisores "+tipo+": }"+cont;

		return resolucaoLatex;
	}
	
	private String addBreakLine(String listDivisores, String separador)
	{
		String []list=listDivisores.split(separador);
		listDivisores="";
		for(int i = 0; i < list.length; i++)
		{
			String string = list[i];
			listDivisores+=string;
			if(i<list.length-1)
			{
				listDivisores+=separador.replaceAll("\\\\","");
				
				if((i+1)%10==0)
				{
					listDivisores+="\\\\";
				}
			}
			
				
		}
		
		return listDivisores;
	}
	
	public int numerosDividoresResultado(int number)
	{
		lista=new ArrayList<Integer>();
		lista.add(1);
		int divisor=2;
		int novoDivisor;
		Integer integer;
		int size;
		while(number!=1)
		{
			if(number%divisor==0)
			{
				size=lista.size();
				for (int i = 0; i < size; i++) 
				{
					integer = lista.get(i);
					
					novoDivisor=integer*divisor;
					if(!lista.contains(novoDivisor))
						lista.add(novoDivisor);
				}

				if(number%divisor==0)
					number/=divisor;
			}
			else
				divisor++;
		}
		return lista.size();
	}
	
	public int somaDividoresResultado(int number)
	{
		lista=new ArrayList<Integer>();
		lista.add(1);
		int divisor=2;
		int novoDivisor;
		Integer integer;
		int size;
		int soma=1;
		while(number!=1)
		{
			if(number%divisor==0)
			{
				size=lista.size();
				for (int i = 0; i < size; i++) 
				{
					integer = lista.get(i);
					novoDivisor=integer*divisor;
					if(!lista.contains(novoDivisor))
					{
						lista.add(novoDivisor);
						soma+=novoDivisor;
					}
				}

				if(number%divisor==0)
					number/=divisor;
			}
			else
				divisor++;
		}
		return soma;
	}
	
	public int numerosDividoresResultadoPares(int number, boolean par)
	{
		int resto=0;
		if(!par)
			resto=1;
		
		numerosDividoresResultado(number);
		int cont=0;
		Integer integer;
		for (int i = 0; i < lista.size(); i++) 
		{
			integer = lista.get(i);
			if(integer%2==resto)
				cont++;
		}
		return cont;
	}
	
	public String fatoresPrimos(int number)
	{
		String resolucaoLatex="";
		int divisor=2;
		lista=new ArrayList<Integer>();
		lista.add(1);
		
		resolucaoLatex += DefinicaoCores.irisBabypink();
		resolucaoLatex += "\\begin{array}[t]{r |l|l}";
		resolucaoLatex += "&&1\\\\";

		String resultado="";
		int cont=0;
		int novoDivisor;
		Integer integer;
		int size;
		while(number!=1)
		{
			if(number%divisor==0)
			{
				resultado="";
				size=lista.size();
				cont=0;
				for (int i = 0; i < size; i++) 
				{
					integer = lista.get(i);
					
					novoDivisor=integer*divisor;
					if(!lista.contains(novoDivisor))
					{
						cont++;
						lista.add(novoDivisor);
						if(cont>1)
							resultado+=",~";
						resultado+=""+novoDivisor;

					}
				}
				resolucaoLatex+=number+",&"+ divisor+"&"+resultado+"\\\\";


				if(number%divisor==0)
					number/=divisor;
				
			}
			else
				divisor++;
		}
		resolucaoLatex+="\\hline";
		resolucaoLatex+=number+",&\\\\";

		resolucaoLatex+="\\end{array}";
		return resolucaoLatex;
	}
	
	public String fatoresPrimosPares(int number, boolean par)
	{
		int resto=0;
		if(!par)
			resto=1;
		
		String resolucaoLatex="";
		int divisor=2;
		lista=new ArrayList<Integer>();
		lista.add(1);
		
		resolucaoLatex += DefinicaoCores.irisBabypink();
		resolucaoLatex += "\\begin{array}[t]{r |l|l}";
		if(par)
			resolucaoLatex += "&&1\\\\";
		else
			resolucaoLatex += "&&\\textcolor{iris}{1}\\\\";

		String resultado="";
		int cont=0;
		int novoDivisor;
		Integer integer;
		int size;
		while(number!=1)
		{
			if(number%divisor==0)
			{
				resultado="";
				size=lista.size();
				cont=0;
				for (int i = 0; i < size; i++) 
				{
					integer = lista.get(i);
					
					novoDivisor=integer*divisor;
					if(!lista.contains(novoDivisor))
					{
						cont++;
						lista.add(novoDivisor);
						if(cont>1)
							resultado+=",~";
						
						if(novoDivisor%2==resto)
							resultado+="\\textcolor{iris}{"+novoDivisor+"}";
						else
							resultado+=""+novoDivisor;

					}
				}
				resolucaoLatex+=number+",&"+ divisor+"&"+resultado+"\\\\";


				if(number%divisor==0)
					number/=divisor;
				
			}
			else
				divisor++;
		}
		resolucaoLatex+="\\hline";
		resolucaoLatex+=number+",&\\\\";

		resolucaoLatex+="\\end{array}";
		return resolucaoLatex;
	}
	
	public static void main(String[] args)
	{
//		ResolucaoDivisores resolucaoDivisores=new ResolucaoDivisores();
//		String resolucaoLatex=resolucaoDivisores.numerosDividores(28);
	}

}
