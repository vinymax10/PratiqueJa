package matematica.resolucaonatural;

import java.util.ArrayList;
import java.util.List;

public class Numero 
{
	List <Termo> termos=new ArrayList<Termo>();
	int inicioValido;
	
	public Numero() {}
	
	public Numero(int numero) 
	{
		String strNumero=""+numero;
		for (int i = 0; i < strNumero.length(); i++) 
		{
			termos.add(new Termo(Integer.valueOf(""+strNumero.charAt(i))));
		}
	}
	
	public Numero(int numero, int validadeDigitos, int deslocamento, boolean somenteDifitosValidos) 
	{
		String strNumero=""+numero;
		
		for (int i = 0; i < deslocamento-strNumero.length(); i++) 
			termos.add(new Termo(0));

		for (int i = 0; i < strNumero.length(); i++) 
		{
			termos.add(new Termo(Integer.valueOf(""+strNumero.charAt(i))));
		}
		
		if(somenteDifitosValidos)
			this.inicioValido=posicaoPrimeiroDigitoValido();
		else
			this.inicioValido=validadeDigitos;

	}
	
	public boolean emprestaUm(int posicao)
	{
		Termo termo= getTermo(posicao);
		termo.temNovoDigito=true;
		if(termo.digito>0)
		{
			termo.novoDigito=termo.digito-1;
			return true;
		}
		else
		{
			termo.novoDigito=9;
			return false;
		}
	}
	
	public int posicaoPrimeiroDigitoValido()
	{
		int posicao=0;
		for(Termo termo : termos)
		{
			if(termo.digito==0)
				posicao++;
			else
				break;
		}
		return posicao;
	}
	
	public void add(int digito)
	{
		termos.add(0,new Termo(digito));
	}
	
	public void addDir(int digito)
	{
		termos.add(new Termo(digito));
	}
	
	public boolean contemAlgumValor()
	{
		
		for (Termo termo : termos) 
		{
			if(termo.digito!=0)
			{
				return true;
			}
		}
		return false;
	}
	
	public String latexDir(int size) 
	{
		String resolucaoLatex="\\\\";
		for (int i = 0; i < size; i++) 
		{
			resolucaoLatex=""+getDigitoStrDir(i)+resolucaoLatex;

			if(i<size-1)
				resolucaoLatex="&"+resolucaoLatex;
		}
		
		return resolucaoLatex;
	}
	
	public String latexEsq(int size) 
	{
		String resolucaoLatex="&";
		for (int i = 0; i < size; i++) 
		{
			if(i>=inicioValido)
				resolucaoLatex+=""+getDigitoStrEsq(i);
	
			if(i<size-2)
				resolucaoLatex+="&";
		}
		resolucaoLatex+="\\\\";
		
		return resolucaoLatex;
	}
	
	public String latexSomaVaiUm(int size) 
	{
		String resolucaoLatex="\\\\";
		String strDigito;
		for (int i = 0; i < size; i++) 
		{
			strDigito="";
			if(!getDigitoStrDir(i).equals("0"))
				strDigito=" \\small \\textcolor{iris}{"+getDigitoStrDir(i)+"}";
			
			resolucaoLatex=strDigito+resolucaoLatex;
			
			if(i<size-1)
				resolucaoLatex="&"+resolucaoLatex;
		}
		
		return resolucaoLatex;
	}
	
	public int getDigito(int posicao)
	{
		if(posicao>size()-1)
			return 0;
		
		return termos.get(termos.size()-posicao-1).digitoValido();
	}
	
	public String getDigitoStrDir(int posicao)
	{
		if(posicao>size()-1)
			return "";
		
		return ""+termos.get(termos.size()-posicao-1).latex();
	}
	
	public String getDigitoStrEsq(int posicao)
	{
		if(posicao>size()-1)
			return "";
		
		return ""+termos.get(posicao).latex();
	}
	
	public Termo getTermo(int posicao)
	{
		return termos.get(termos.size()-posicao-1);
	}
	
	public void add(int valor, int posicao)
	{
		Termo termo=getTermo(posicao);
		if(termo.temNovoDigito)
			termo.novoDigito+=valor;
		else
			termo.digito+=valor;

	}
	
	public int getParte(int posicao)
	{
		int valor=termos.get(0).digito;
		for (int i = 1; i <= posicao; i++) 
		{
			valor*=10;
			valor+=termos.get(i).digito;
		}
		return valor;
	}
	
	public int inteiro()
	{
		int valor=termos.get(0).digito;
		for (int i = 1; i < termos.size(); i++) 
		{
			valor*=10;
			valor+=termos.get(i).digito;
		}
		return valor;
	}
	
	public int size()
	{
		return termos.size();
	}
	
	public List<Termo> getTermos()
	{
		return termos;
	}

	public void setTermos(List<Termo> termos)
	{
		this.termos = termos;
	}

	@Override
	public String toString() 
	{
		String str="";
		
		for (Termo termo : termos) 
		{
			str+=termo.digito;
			
		}
		return str;
	}
	
	
}
