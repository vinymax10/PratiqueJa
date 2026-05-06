package matematica.avancado.numeroscomplexos.nivel3package;

import matematica.avancado.numeroscomplexos.NumeroComplexo;
import modelo.matematica.Conta;

public class Expressao2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao2(int index)
	{
		super(index);
		
		NumeroComplexo a=NumeroComplexo.contruir(10);
		
		textLatex="("+a+")^2=";
		resolucaoLatex=a.resolucaoQuadrado();
		NumeroComplexo resultado=a.mult(a);
		
		boolean real=rand.nextBoolean();
		if(real)
		{
			pergunta="Qual a parte real do quadrado?";
			resultadoCorreto = "" + resultado.real;
			resolucaoLatex+="\\\\ \\text{Parte real}="+resultado.real.showDfrac();
		}
		else
		{
			pergunta="Qual a parte imaginária do quadrado?";
			resultadoCorreto = "" + resultado.imaginaria;
			resolucaoLatex+="\\\\ \\text{Parte imaginária}="+resultado.imaginaria.showDfrac();
		}
		
	}

	public static void main(String[] args)
	{
		new Expressao2(1);
	}
}
