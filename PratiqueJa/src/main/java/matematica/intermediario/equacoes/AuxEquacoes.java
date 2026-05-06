package matematica.intermediario.equacoes;

import java.util.Random;

public class AuxEquacoes
{
	public static void mudaOrdem(Object [] lista)
	{
		Object aux;
		int posA,posB;
		Random rand=new Random();
		
		for(int i = 0; i < lista.length; i++)
		{
			posA=rand.nextInt(lista.length);
			posB=rand.nextInt(lista.length);

			aux=lista[posA];
			lista[posA]=lista[posB];
			lista[posB]=aux;
		}
	}
	
	public static int indexIgual(String lista[], String alvo)
	{
		for(int i = 0; i < lista.length; i++)
		{
			if(lista[i].equals(alvo))
				return i;
		}
		return -1;
	}
	
	public static String getOperador()
	{
		Random rand=new Random();
		String operadores[] = { "+", "-", "*" };
		return operadores[rand.nextInt(operadores.length)];
	}
}
