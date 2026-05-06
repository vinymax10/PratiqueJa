package matematica.intermediario.radiciacao.nivel3package;

import matematica.Racional;
import modelo.matematica.Conta;


public class Radiciacao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao2(int indice)
	{
		super(indice);
		int b = 2 + rand.nextInt(9);
		int x = 1 + rand.nextInt(10);
		int y = 1 + rand.nextInt(10);
		
		while(b==4||b==9)
			b = 2 + rand.nextInt(9);

		int a = b * x * x;
		int c = b * y * y;
		while(c==b||c==a)
		{
			y = 1 + rand.nextInt(10);
			c = b * y * y;
		}
		

		textLatex = " \\dfrac{ \\sqrt{" + a + "} }{ \\sqrt{" + b + "} + \\sqrt{" + c + "} }" + "=";
		
		resolucaoLatex = " \\dfrac{ \\sqrt{" + a + "} }{ \\sqrt{" + b + "} + \\sqrt{" + c + "} }" + 
		"\\cdot \\dfrac{ \\sqrt{" + b + "} - \\sqrt{" + c + "} }{ \\sqrt{" + b + "} - \\sqrt{" + c + "} }=\\\\";
		
		resolucaoLatex += " \\dfrac{ \\sqrt{" + a + "} \\cdot \\sqrt{" + b + "} - \\sqrt{" + a + "} \\cdot \\sqrt{" + c + "}}{ (\\sqrt{" + b + "})^2 - (\\sqrt{" + c + "})^2 }=\\\\";
	
		resolucaoLatex += " \\dfrac{ \\sqrt{" + a + " \\cdot " + b + "} - \\sqrt{" + a + " \\cdot" + c + "}}{ "+b+" - "+c+" }=\\\\";

		resolucaoLatex += " \\dfrac{ \\sqrt{" + a*b + "} - \\sqrt{" + a*c + "} }{ "+(b-c)+" }=";

		resolucaoLatex += " \\dfrac{ "+(x*b)+" - "+(x*b*y)+" }{ "+(b-c)+" }=\\\\";

		Racional resultado=new Racional((x*b)-(x*b*y),b-c);
		
		resolucaoLatex += resultado.showDfrac();
		resultado.fatoracao(2);
		if(resultado.isSimplificou())
			resolucaoLatex += "="+resultado.showDfrac();
		
		resultadoCorreto = resultado.toString();
	}

	public static void main(String[] args)
	{
		new Radiciacao2(1);
	}

}
