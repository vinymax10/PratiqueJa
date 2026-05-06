package matematica.avancado.combinatoria;

import java.util.HashMap;
import java.util.Map;

public class AuxCombinacao
{
	public static int contarRepeticao(String palavra)
	{
        Map<Character, Integer> contador = new HashMap<>();

		for (char c : palavra.toCharArray()) 
			contador.put(c, contador.getOrDefault(c, 0) + 1);
		
		int cont=0;
		for (Map.Entry<Character, Integer> entry : contador.entrySet()) 
		{
			if (entry.getValue() > 1) 
			{
				cont++;
			}
		}
		return cont;
	}
    
	public static String fatorialString(int n)
	{
		String str="";
		for(int i = n; i > 0; i--)
		{	
			str+=i;
			if(i>1)
				str+="\\cdot";
		}
		return str;
	}
	
	public static String fatorialString(int n, int limite)
	{
		String str="";
		for(int i = n; i > limite; i--)
		{	
			str+=i;
			if(i>(limite+1))
				str+="\\cdot";
		}
		return str;
	}
}
