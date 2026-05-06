package matematica.intermediario.divisibilidade.nivel2package;

import matematica.intermediario.divisibilidade.ResolucaoDivisibilidade;
import modelo.matematica.Conta;


public class Divisibilidade8 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Divisibilidade8(int index)
	{
		super(index);
		int ref = 8;
		int number = 1000 + rand.nextInt(9000);

		resultadoCorretoBol = rand.nextBoolean();
		int resto = number % ref;
		if (resultadoCorretoBol)
			number += (ref - resto);
		else if (resto == 0)
			number++;

		pergunta = "" + (number) + " é divisível por " + ref + "?";
		
		resolucaoLatex = ResolucaoDivisibilidade.resolucao8(resultadoCorretoBol, number);

	}

	public static void main(String[] args)
	{
		new Divisibilidade8(1);
	}
}
