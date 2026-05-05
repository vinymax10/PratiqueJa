package Matematica;


public class FatoresPrimos
{
	public static String decomposicao(int num)
	{
		String resultado="";
		int divisor=2;
		int cont=0;
		while(num != 1)
		{
			if(num%divisor==0)
			{
				num/=divisor;
				cont++;
			}
			else
			{
				if(cont!=0)
				{
					resultado+=" "+divisor+"^"+cont;
					cont=0;
				}
				divisor++;
			}
		}
		resultado+=" "+divisor+"^"+cont;
		
		return resultado;
	}

	public static void main(String[] args)
	{
		System.out.println(decomposicao(14));
	}
}
