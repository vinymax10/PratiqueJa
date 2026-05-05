package Matematica.Basico.ResolucaoNatural;

import java.io.IOException;

import Matematica.DefinicaoCores;
import Pdf.Convert;

public class Subtracao 
{
	Numero primeiro;
	Numero segundo;
	Numero resultado;
	int numeroTermos,size;
	String simbolo;
	
	public String subtrair(int a, int b, boolean resolucao)
	{
		primeiro=new Numero(a);
		segundo=new Numero(b);
		resultado=new Numero(a-b);
		simbolo="-";
		
		numeroTermos=Math.max(primeiro.size(), segundo.size());
		size=Math.max(numeroTermos, resultado.size());
		
		if(resolucao)
		{
			int index;
			for (int i = 0; i < primeiro.size(); i++) 
			{
				if(segundo.getDigito(i)>primeiro.getDigito(i))
				{
					primeiro.add(10,i);
					index=i+1;
					while(!primeiro.emprestaUm(index++));
				}
			}
		}
		
		return latex(resolucao);
	}
	
	public String latex(boolean resolucao) 
	{
		String resolucaoLatex=DefinicaoCores.irisBabypink();
		resolucaoLatex += "\\begin{array}[t]{";
		for (int i = 0; i < size+1; i++) 
			resolucaoLatex+="r";
		
		resolucaoLatex+="}";
		
		resolucaoLatex+= primeiro.latexDir(size+1);
		resolucaoLatex+=simbolo+ segundo.latexDir(size+1);
		resolucaoLatex+="\\hline";
		
		if(resolucao)
			resolucaoLatex+= resultado.latexDir(size+1);
		
		resolucaoLatex+="\\end{array}";
		
		return resolucaoLatex;
	}

	public static void main(String[] args) 
	{
		Subtracao subtracao=new Subtracao();

		try
		{
			Convert.toSVG(subtracao.subtrair(111, 77,true),"areaPoligono.svg",true);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
