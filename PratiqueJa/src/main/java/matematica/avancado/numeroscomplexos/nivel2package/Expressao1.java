package matematica.avancado.numeroscomplexos.nivel2package;

import matematica.avancado.numeroscomplexos.NumeroComplexo;
import modelo.matematica.Conta;

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
