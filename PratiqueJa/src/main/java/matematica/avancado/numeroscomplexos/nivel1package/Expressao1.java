package matematica.avancado.numeroscomplexos.nivel1package;

import matematica.avancado.numeroscomplexos.NumeroComplexo;
import modelo.matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int index)
	{
		super(index);
		
		NumeroComplexo a=NumeroComplexo.contruir(30);
		NumeroComplexo b=NumeroComplexo.contruir(30);
		
		textLatex="("+a+")+("+b+")=";
		resolucaoLatex=a.resolucaoSoma(b);
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
		new Expressao1(1);
	}
}
