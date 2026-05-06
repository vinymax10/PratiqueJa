package matematica.intermediario.radiciacao.nivel3package;

import matematica.Racional;
import modelo.matematica.Conta;


public class Radiciacao4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Radiciacao4(int indice)
	{
		super(indice);
		int b = 2 + rand.nextInt(9);
		int x = 1 + rand.nextInt(10);
		int y = 1 + rand.nextInt(10);

		while(b==4||b==9)
			b = 2 + rand.nextInt(9);
		
		Racional bR =new Racional(b);

		Racional a =new Racional(x * x,b);
		a.fatoracao(2);
		
		Racional c =new Racional(y * y,b);
		c.fatoracao(2);
		
		while(c.igual(bR)||c.igual(a))
		{
			y = 1 + rand.nextInt(10);
			c =new Racional(y * y,b);
		}
		
		textLatex = " \\dfrac{ \\sqrt{" + a.showDfrac() + "} }{ \\sqrt{" + b + "} + \\sqrt{" + c.showDfrac() + "} }" + "=";
		
		resolucaoLatex = " \\dfrac{ \\sqrt{" + a.showDfrac() + "} }{ \\sqrt{" + b + "} + \\sqrt{" + c.showDfrac() + "} }" + 
		"\\cdot \\dfrac{ \\sqrt{" + b + "} - \\sqrt{" + c.showDfrac() + "} }{ \\sqrt{" + b + "} - \\sqrt{" + c.showDfrac() + "} }=\\\\";
		
		resolucaoLatex += " \\dfrac{ \\sqrt{" + a.showDfrac() + "} \\cdot \\sqrt{" + b + "} - \\sqrt{" + a.showDfrac() + "} \\cdot \\sqrt{" + c.showDfrac() + "}}{ (\\sqrt{" + b + "})^2 - (\\sqrt{" + c.showDfrac() + "})^2 }=\\\\";
//	
		resolucaoLatex += " \\dfrac{ \\sqrt{" + a.showDfrac() +"\\cdot" +bR.showDfrac()+ "} - \\sqrt{" + a.showDfrac() +"\\cdot" +c.showDfrac()+ "} }{ "+b+" - "+c.showDfrac()+" }=";
//
		Racional parcial=a.mult(c);
		parcial.fatoracao(2);
		Racional denominador=bR.minus(c);
		denominador.fatoracao(2);

		resolucaoLatex += " \\dfrac{ \\sqrt{" + (x*x) + "} - \\sqrt{" +parcial.showDfrac() + "} }{ "+denominador.showDfrac()+" }=\\\\";

		parcial=new Racional(x*y,b);
		parcial.fatoracao(2);
		
		resolucaoLatex += " \\dfrac{ " + x + " - " +parcial.showDfrac() + " }{ "+denominador.showDfrac()+" }=";

		parcial=new Racional(x).minus(parcial);
		parcial.fatoracao(2);

		resolucaoLatex += " \\dfrac{ " +parcial.showDfrac() + " }{ "+denominador.showDfrac()+" }=";

		if(parcial.denominador!=1||denominador.denominador!=1)
		{
			resolucaoLatex += parcial.showDfrac()+" \\cdot " + denominador.inverter().showDfrac()+"=";
			
			parcial=parcial.mult(denominador.inverter());
			resolucaoLatex += parcial.showDfrac();
			
			parcial.fatoracao(2);
			if(parcial.isSimplificou())
				resolucaoLatex +="="+ parcial.showDfrac();
		}
		else
		{
			parcial=parcial.mult(denominador.inverter());
			parcial.fatoracao(2);
			resolucaoLatex += parcial.showDfrac();
		}
		resolucaoLatex = resolucaoLatex.replace("(", "\\left(").replace(")", "\\right)");

		resultadoCorreto = parcial.toString();
	}

	public static void main(String[] args)
	{
		new Radiciacao4(1);
	}

}
