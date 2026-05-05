package Infra;

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

}
