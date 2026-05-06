package matematica.avancado.numeroscomplexos.nivel1package;

import matematica.avancado.numeroscomplexos.NumeroComplexo;
import modelo.matematica.Conta;

public class Expressao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao2(int index)
	{
		super(index);
		
		NumeroComplexo a=NumeroComplexo.contruir(30);
		NumeroComplexo b=NumeroComplexo.contruir(30);

		textLatex="("+a+")-("+b+")=";
		resolucaoLatex=a.resolucaoMinus(b);
		NumeroComplexo resultado=a.minus(b);
		
		boolean real=rand.nextBoolean();
		if(real)
		{
			pergunta="Qual a parte real da subtração?";
			resultadoCorreto = "" + resultado.real;
			resolucaoLatex+="\\\\ \\text{Parte real}="+resultado.real.showDfrac();
		}
		else
		{
			pergunta="Qual a parte imaginária da subtração?";
			resultadoCorreto = "" + resultado.imaginaria;
			resolucaoLatex+="\\\\ \\text{Parte imaginária}="+resultado.imaginaria.showDfrac();
		}
		
	}

	public static void main(String[] args)
	{
		new Expressao2(1);
	}
}
