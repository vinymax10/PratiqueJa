package Matematica.Intermediario.Divisibilidade.Nivel2Package;

import Matematica.Intermediario.Divisibilidade.ResolucaoDivisibilidade;
import Modelo.Matematica.Conta;


public class Divisibilidade7 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Divisibilidade7(int index)
	{
		super(index);
		int ref = 7;
		int number = 1000 + rand.nextInt(9000);

		resultadoCorretoBol = rand.nextBoolean();
		int resto = number % ref;
		if (resultadoCorretoBol)
			number += (ref - resto);
		else if (resto == 0)
			number++;

		pergunta = "" + (number) + " é divisível por " + ref + "?";

		resolucaoLatex = ResolucaoDivisibilidade.resolucao7(resultadoCorretoBol, number);

	}

	public static void main(String[] args)
	{
		new Divisibilidade7(1);
	}
}
