package matematica.intermediario.potenciacao.nivel2package;

import matematica.Calc;
import matematica.Racional;
import matematica.intermediario.potenciacao.ResolucaoPotencia;
import modelo.matematica.Conta;


public class Potenciacao4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Potenciacao4(int indice)
	{
		super(indice);
		
		int potenciaMaxima=7;
		
		int a = 1 + rand.nextInt(10);
		if (rand.nextBoolean())
			a *= -1;

		int b = 2 + rand.nextInt(9);

		while(a%b==0)
			b = 2 + rand.nextInt(9);
		
		if (rand.nextBoolean())
			b *= -1;

		Racional racional=new Racional(a,b);
		racional.fatoracao(2);
		
		int maxBase = (int) Math.min((Math.log(1000) / Math.log((Math.abs(a)+ Math.abs(b))/2)), potenciaMaxima);

		int p = 1 + rand.nextInt(maxBase);

		textLatex = "\\left("+racional.showDfrac()+"\\right)^{-" + p + "}=";

		resultadoCorreto = "" + Calc.fatoracao((int) Math.pow(b, p), (int) Math.pow(a, p), 2);
		resolucaoLatex = ResolucaoPotencia.resolucaoPotenciaNegativa((int)racional.numerador,(int) racional.denominador, p);

	}

	public static void main(String[] args)
	{
		new Potenciacao4(1);
	}

}
