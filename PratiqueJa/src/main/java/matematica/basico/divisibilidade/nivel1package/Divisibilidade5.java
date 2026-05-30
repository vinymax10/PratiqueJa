package matematica.basico.divisibilidade.nivel1package;

import matematica.basico.divisibilidade.ResolucaoDivisibilidade;
import modelo.matematica.Conta;


public class Divisibilidade5 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Divisibilidade5(int index)
	{
		super(index);
		int ref = 5;
		int number = 1000 + rand.nextInt(9000);

		resultadoCorretoBol = rand.nextBoolean();
		int resto = number % ref;
		if (resultadoCorretoBol)
			number += (ref - resto);
		else if (resto == 0)
			number++;

		pergunta = "" + (number) + " é divisível por " + ref + "?";
		
		resolucaoLatex = ResolucaoDivisibilidade.resolucao5(resultadoCorretoBol, number);
		
	}

	public static void main(String[] args)
	{
		new Divisibilidade5(1);
	}
}
