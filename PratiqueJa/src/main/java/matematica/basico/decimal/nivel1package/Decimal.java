package matematica.basico.decimal.nivel1package;

import modelo.matematica.Conta;

public class Decimal extends Conta
{
	private static final long serialVersionUID = 1L;

	public Decimal(int index)
	{
		super(index);
		
		int a=1+rand.nextInt(20);
		int b=1+rand.nextInt(20);
		while(b==a)
			b=1+rand.nextInt(20);
		
		double resultado = (double)a/b;
		textLatex="\\dfrac{"+a+"}{"+b+"} = ";
		resultadoCorreto=""+resultado;
		resultadoCorreto=resultadoCorreto.replace(".", ",");
		String str[]=resultadoCorreto.split(",");
		
		resultadoCorreto=str[0]+","+str[1].substring(0,Math.min(str[1].length(), 3));
		
		index=resultadoCorreto.length()-1;
		while(resultadoCorreto.charAt(index)=='0'||resultadoCorreto.charAt(index)==',')
			resultadoCorreto=resultadoCorreto.substring(0, index--);
		
		System.out.println("textLatex: "+textLatex);
		System.out.println("resultadoCorreto: "+resultadoCorreto);
		
	}
	
	public static void main(String[] args) 
	{
		new Decimal(1);
	}
}
