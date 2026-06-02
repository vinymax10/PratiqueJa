package matematica;


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

		

	}

}
