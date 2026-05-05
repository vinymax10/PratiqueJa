package Matematica.Avancado.NumerosComplexos.Nivel2Package;

import Matematica.Avancado.NumerosComplexos.NumeroComplexo;
import Modelo.Matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int index)
	{
		super(index);
		
		NumeroComplexo a=NumeroComplexo.contruir(10);
		NumeroComplexo b=NumeroComplexo.contruir(10);
		
		textLatex="("+a+") \\cdot ("+b+")=";
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
		new Expressao1(1);
	}
}
