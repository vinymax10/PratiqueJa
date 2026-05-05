package Matematica.Avancado.NumerosComplexos.Nivel3Package;

import Matematica.Avancado.NumerosComplexos.NumeroComplexo;
import Modelo.Matematica.Conta;

public class Expressao3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao3(int index)
	{
		super(index);
		
		NumeroComplexo a=NumeroComplexo.contruirFrac(10);
		NumeroComplexo b=NumeroComplexo.contruirFrac(10);
		
		textLatex="("+a+") \\cdot ("+b+")=";
		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		resolucaoLatex=a.resolucaoMultiplicacao(b);
		NumeroComplexo resultado=a.mult(b);
		
		boolean real=rand.nextBoolean();
		if(real)
		{
			pergunta="Qual a parte real da multiplicação?";
			resultadoCorreto = "" + resultado.real;
			resolucaoLatex+="\\\\ \\text{Parte real}="+resultado.real.showDfrac();
		}
		else
		{
			pergunta="Qual a parte imaginária da multiplicação?";
			resultadoCorreto = "" + resultado.imaginaria;
			resolucaoLatex+="\\\\ \\text{Parte imaginária}="+resultado.imaginaria.showDfrac();
		}
		
	}

	public static void main(String[] args)
	{
		new Expressao3(1);
	}
}
