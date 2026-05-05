package Auxiliar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringAux
{
	
	public static String toConvertDFrac(String fracao)
	{
		if(fracao.contains("/"))
		{
			String list[]=fracao.split("/");
			if(list.length==2)
				return "\\dfrac{" + list[0] + "}{" + list[1] + "}";
		}
		return fracao;
	}
	
	public static String toLowerCaseFirst(String str)
	{
		char c[] = str.toCharArray();
		c[0] = Character.toLowerCase(c[0]);
		String newStr = new String(c);
		return newStr;
	}

	public static String getDataStr(LocalDate data)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(data);
	}
}
