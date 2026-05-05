package Matematica.Intermediario.Potenciacao.Nivel2Package;

import Matematica.Calc;
import Matematica.Intermediario.Potenciacao.ResolucaoPotencia;
import Modelo.Matematica.Conta;


public class Potenciacao3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Potenciacao3(int indice)
	{
		super(indice);
		
		int potenciaMaxima=7;
		
		int a = 1 + rand.nextInt(10);
		if (rand.nextBoolean())
			a *= -1;

		int maxBase = (int) Math.min((Math.log(1000) / Math.log(Math.abs(a))), potenciaMaxima);
		int p = 1 + rand.nextInt(maxBase);
		if (p == 1)
			p = 1 + rand.nextInt(maxBase);

		textLatex = "" + a + "^{-" + p + "}=";

		
		if(a<0)
		{
			resultadoCorreto = "" + Calc.fatoracao(1, (int) -Math.pow(Math.abs(a), p), 2);
			resolucaoLatex = ResolucaoPotencia.resolucaoPotenciaNegativaBaseNegativa(a, p);
		}
		else
		{
			resultadoCorreto = "" + Calc.fatoracao(1, (int) Math.pow(a, p), 2);
			resolucaoLatex = ResolucaoPotencia.resolucaoPotenciaNegativa(a, p);
		}

	}

	public static void main(String[] args)
	{
		new Potenciacao3(1);
	}

}
