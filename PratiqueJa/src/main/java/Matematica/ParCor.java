package Matematica;

import Auxiliar.ColorHolder;
import Auxiliar.CorAux;

public class ParCor
{
	String corForte;
	String corFraca;

	public ParCor(String corForte, String corFraca)
	{
		this.corForte = corForte;
		this.corFraca = corFraca;
	}

	public String getCorForte()
	{
		return corForte;
	}

	public void setCorForte(String corForte)
	{
		this.corForte = corForte;
	}

	public String getCorFraca()
	{
		return corFraca;
	}

	public void setCorFraca(String corFraca)
	{
		this.corFraca = corFraca;
	}
	
	public static ParCor parCor(int indice)
	{
		indice=indice%6;
		switch(indice)
		{
			case 0: return new ParCor("#6370FF", "#969FFF");// Azul
			case 1: return new ParCor("#DE7B40", "#DE976C");// Laranja
			case 2: return new ParCor("#008A70","#70DCBF");// Verde
			case 3: return new ParCor("#FA6257","#FA9189");// Vermelho
			case 4: return new ParCor("#FF6A85","#FF9DAF");// Rosa
			case 5: return new ParCor("#766E62","#C6BDAF");// Cinza
			default: return new ParCor("#6370FF", "#969FFF");// Azul
		}
	}

	public static String formula(String formula)
	{
		String cor = CorAux.convertHexPorc(ColorHolder.getFORMULA());
		String str="\\definecolor{azulEscuro}{rgb}{"+cor+"}";
		str+="\\textcolor{azulEscuro}{"+formula+"}";
		return str;
	}
}
