package Matematica.Basico.ResolucaoNatural;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Matematica.DefinicaoCores;
import Pdf.Convert;

public class Multiplicacao 
{
	Numero vaiUm;
	Numero primeiro;
	Numero segundo;
	List<Numero>parcial;
	Numero resultado;
	int numeroTermos,size;
	String simbolo;
	
	public String multiplicar(int a, int b, boolean resolucao)
	{
		primeiro=new Numero(a);
		segundo=new Numero(b);
		resultado=new Numero(a*b);
		vaiUm=new Numero(0);
		simbolo=" \\times ";
		parcial=new ArrayList<Numero>();
		numeroTermos=maiorNumero();
		size=Math.max(numeroTermos, resultado.size());
		int valor;
		if(resolucao)
		{
			if(segundo.size()>1)
			{
				for (int i = 0; i < segundo.size(); i++) 
				{
					valor=segundo.getDigito(i);
					Numero numero=new Numero(valor*a*Math.max(1,((int)Math.pow(10, i))));
					parcial.add(numero);
				}
				
				numeroTermos=maiorNumero();
				
				for (int i = 0; i < numeroTermos; i++) 
				{
					valor=vaiUm.getDigito(i);
					for(Numero numero : parcial)
						valor+=numero.getDigito(i);
					
					if(valor>9)
						vaiUm.add(valor/10);
					else 
						vaiUm.add(0);
				}
			}
			else
			{
				for (int i = 0; i < numeroTermos; i++) 
				{
					valor=primeiro.getDigito(i)*segundo.getDigito(0)+vaiUm.getDigito(i);
					if(valor>9)
						vaiUm.add(valor/10);
					else 
						vaiUm.add(0);
				}
			}
			
		}
		
		return latex(resolucao);
	}
	
	private int maiorNumero()
	{
		int size=0;
		
		if(primeiro.size()>size)
			size=primeiro.size();
		
		if(segundo.size()>size)
			size=segundo.size();
		
		if(resultado.size()>size)
			size=resultado.size();
		
		if(vaiUm.size()>size)
			size=vaiUm.size();
		
		for(Numero numero : parcial)
		{
			if(numero.size()>size)
				size=numero.size();
		}
		
		return size;
	}
	
	public String latex(boolean resolucao) 
	{
		String resolucaoLatex=DefinicaoCores.irisBabypink();
		resolucaoLatex += "\\begin{array}[t]{";
		for (int i = 0; i < size+1; i++) 
			resolucaoLatex+="r";
		
		resolucaoLatex+="}";
		
		if(segundo.size()==1)
		{
			if(vaiUm.contemAlgumValor())
				resolucaoLatex+= vaiUm.latexSomaVaiUm(size+1);
		}
		
		resolucaoLatex+= primeiro.latexDir(size+1);
		resolucaoLatex+=simbolo+ segundo.latexDir(size+1);
		resolucaoLatex+="\\hline";
		
		Numero numero;
		if(resolucao)
		{
			if(segundo.size()>1)
			{
				if(vaiUm.contemAlgumValor())
					resolucaoLatex+= vaiUm.latexSomaVaiUm(size+1);
		
				for (int i = 0; i < parcial.size(); i++) 
				{
					numero=parcial.get(i);
					if(i==parcial.size()-1)
						resolucaoLatex+="+"+numero.latexDir(size+1);
					else
						resolucaoLatex+= numero.latexDir(size+1);
				}
				resolucaoLatex+="\\hline";
			}
			resolucaoLatex+= resultado.latexDir(size+1);
		}
		
		resolucaoLatex+="\\end{array}";
		
		return resolucaoLatex;
	}

	public static void main(String[] args) 
	{
	}
}
