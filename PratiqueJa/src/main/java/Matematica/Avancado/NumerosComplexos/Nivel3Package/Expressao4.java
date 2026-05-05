package Matematica.Avancado.NumerosComplexos.Nivel3Package;

import Matematica.Avancado.NumerosComplexos.NumeroComplexo;
import Modelo.Matematica.Conta;

public class Expressao4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao4(int index)
	{
		super(index);
		
		NumeroComplexo a=NumeroComplexo.contruir(10);
		NumeroComplexo b=NumeroComplexo.contruir(10);
		
		textLatex="\\dfrac{"+a+"}{"+b+"}=";
		resolucaoLatex=a.resolucaoDivisao(b);
		NumeroComplexo resultado=a.div(b);
		
		boolean real=rand.nextBoolean();
		if(real)
		{
			pergunta="Qual a parte real da divisão?";
			resultadoCorreto = "" + resultado.real;
			resolucaoLatex+="\\\\ \\text{Parte real}="+resultado.real.showDfrac();
		}
		else
		{
			pergunta="Qual a parte imaginária da divisão?";
			resultadoCorreto = "" + resultado.imaginaria;
			resolucaoLatex+="\\\\ \\text{Parte imaginária}="+resultado.imaginaria.showDfrac();
		}
		
	}

	public static void main(String[] args)
	{
		new Expressao4(1);
	}
}
