package util;

import java.util.List;

public class ListAux
{
	public static int containsNumOcorrencias(Object objecto, List<?> list)
	{
		int cont = 0;
		for(Object elemento : list)
		{
			if(elemento.equals(objecto))
				cont++;
		}
		return cont;
	}
	
	public static boolean containsNome(String nome, List<?> list,Class<?> classe, Long id)
	{
	    String nomeElemento;
	    Long idElemento;
		for(Object elemento : list)
		{
			nomeElemento=(String) ClasseAux.getValorAtributo(elemento,"nome");
			if(nomeElemento!=null&&nomeElemento.equals(nome))
			{
				if(id==null)
					return true;
				else 
				{
					idElemento=(Long)ClasseAux.getValorAtributo(elemento,"id");
					if(!idElemento.equals(id))
						return true;
				}
					
			}
		}
		return false;
	}

}
