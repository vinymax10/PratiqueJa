package Matematica.Avancado.FuncaoAfim.Nivel1Package;

import Matematica.Racional;
import Modelo.Matematica.Conta;

public class Expressao1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Expressao1(int index)
	{
		super(index);

		int a = 1 + rand.nextInt(9);
		int b = 1 + rand.nextInt(20);

		if (rand.nextBoolean())
			a *= -1;

		if (rand.nextBoolean())
			b *= -1;

		int num = 1 + rand.nextInt(20);
		String nomeA = "";
		if (a != 1 && a != -1)
			nomeA = "" + a;
		else if (a == -1)
			nomeA = "-";

		String nomeB = "";
		if (b > 0)
			nomeB = "+";

		nomeB += b;

		textLatex = "f(x)=" + nomeA + "x" + nomeB;

		Racional resultado = new Racional(a * num + b);

		textLatex = textLatex.replace("(", "\\left(").replace(")", "\\right)");

		pergunta="Encontre \\( f(" + num + ") \\)";
		
		resultadoCorreto = "" + resultado.toString();
		
		resolucaoLatex="f("+num+")=";
		
		if(a!=-1&&a!=1)
			resolucaoLatex+= + a + "\\cdot "+num+ nomeB +" = \\\\" ;
		
		resolucaoLatex+=(a * num)+ nomeB+" = "+ resultado;

	}

	public static void main(String[] args)
	{
		new Expressao1(1);
	}
}
