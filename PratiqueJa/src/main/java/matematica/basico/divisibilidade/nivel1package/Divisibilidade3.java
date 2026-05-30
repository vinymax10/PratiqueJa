package matematica.basico.divisibilidade.nivel1package;

import matematica.basico.divisibilidade.ResolucaoDivisibilidade;
import modelo.matematica.Conta;


public class Divisibilidade3 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Divisibilidade3(int index)
	{
		super(index);

		int ref = 3;
		int number = 1000 + rand.nextInt(9000);

		resultadoCorretoBol = rand.nextBoolean();
		int resto = number % ref;
		if (resultadoCorretoBol)
			number += (ref - resto);
		else if (resto == 0)
			number++;

		pergunta = "" + (number) + " é divisível por " + ref + "?";

		resolucaoLatex = ResolucaoDivisibilidade.resolucao3(resultadoCorretoBol, number);

	}

	public static void main(String[] args)
	{
		for (int i = 0; i < 1000; i++)
			new Divisibilidade3(1);
	}
}
