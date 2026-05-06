package matematica.avancado.numeroscomplexos.nivel2package;

import matematica.avancado.numeroscomplexos.NumeroComplexo;
import modelo.matematica.Conta;

public class Expressao3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao3(int index)
	{
		super(index);
		
		NumeroComplexo a=NumeroComplexo.contruirFrac(10);
		NumeroComplexo b=NumeroComplexo.contruirFrac(10);

		resolucaoLatex=a.resolucaoMinus(b);

		textLatex="("+a+")-("+b+")=";
		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		resolucaoLatex=a.resolucaoMinus(b);
		resolucaoLatex = resolucaoLatex.replace("(", "\\left(").replace(")", "\\right)");

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
		new Expressao3(1);
	}
}
