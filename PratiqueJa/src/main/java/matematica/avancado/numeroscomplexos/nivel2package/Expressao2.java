package matematica.avancado.numeroscomplexos.nivel2package;

import matematica.avancado.numeroscomplexos.NumeroComplexo;
import modelo.matematica.Conta;

public class Expressao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao2(int index)
	{
		super(index);
		
		NumeroComplexo a=NumeroComplexo.contruirFrac(10);
		NumeroComplexo b=NumeroComplexo.contruirFrac(10);
		
		textLatex="("+a+")+("+b+")=";
		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		resolucaoLatex=a.resolucaoSoma(b);
		resolucaoLatex = resolucaoLatex.replace("(", "\\left(").replace(")", "\\right)");

		NumeroComplexo resultado=a.add(b);
		
		boolean real=rand.nextBoolean();
		if(real)
		{
			pergunta="Qual a parte real da soma?";
			resultadoCorreto = "" + resultado.real;
			resolucaoLatex+="\\\\ \\text{Parte real}="+resultado.real.showDfrac();
		}
		else
		{
			pergunta="Qual a parte imaginária da soma?";
			resultadoCorreto = "" + resultado.imaginaria;
			resolucaoLatex+="\\\\ \\text{Parte imaginária}="+resultado.imaginaria.showDfrac();
		}
		
	}

	public static void main(String[] args)
	{
		new Expressao2(1);
	}
}
