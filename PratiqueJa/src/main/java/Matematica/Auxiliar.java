package Matematica;


public class Auxiliar
{
	public static String getNumber(int number, String literal, boolean first)
	{
		String str="";
		if(number>=0&&!first)
			str+="+";
		
		if(literal.equals(""))
			str+=""+number;
		else
		{
			if(number!=1&&number!=-1)
				str+=""+number;
			else if(number==-1)
				str+="-";
		}
		
		if(number!=0)
			str+=""+literal;
		
		return str;
	}
	
	public static void main(String[] args) 
	{
		System.out.println("---------não first----------");
		System.out.println(Auxiliar.getNumber(0, "", false));
		System.out.println(Auxiliar.getNumber(1, "", false));
		System.out.println(Auxiliar.getNumber(-1, "", false));
		System.out.println(Auxiliar.getNumber(2, "", false));
		System.out.println(Auxiliar.getNumber(-2, "", false));

		System.out.println(Auxiliar.getNumber(0, "x", false));
		System.out.println(Auxiliar.getNumber(1, "x", false));
		System.out.println(Auxiliar.getNumber(-1, "x", false));
		System.out.println(Auxiliar.getNumber(2, "x", false));
		System.out.println(Auxiliar.getNumber(-2, "x", false));
		
		System.out.println("---------first----------");
		System.out.println(Auxiliar.getNumber(0, "", true));
		System.out.println(Auxiliar.getNumber(1, "", true));
		System.out.println(Auxiliar.getNumber(-1, "", true));
		System.out.println(Auxiliar.getNumber(2, "", true));
		System.out.println(Auxiliar.getNumber(-2, "", true));

		System.out.println(Auxiliar.getNumber(0, "x", true));
		System.out.println(Auxiliar.getNumber(1, "x", true));
		System.out.println(Auxiliar.getNumber(-1, "x", true));
		System.out.println(Auxiliar.getNumber(2, "x", true));
		System.out.println(Auxiliar.getNumber(-2, "x", true));
	}

}
