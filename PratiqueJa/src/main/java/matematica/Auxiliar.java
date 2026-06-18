package matematica;


public class Auxiliar
{
	public static String getNumber(int number, String literal, boolean first)
	{
		// Coeficiente zero em termo não-inicial não deve aparecer (suprime "+0", "+0x" etc.)
		if(number==0&&!first)
			return "";

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
