package matematica.basico.divisibilidade.nivel2package;

import matematica.basico.divisibilidade.ResolucaoDivisibilidade;
import modelo.matematica.Conta;


public class Divisibilidade4 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Divisibilidade4(int index)
	{
		super(index);
		int ref = 4;
		int number = 1000 + rand.nextInt(9000);

		resultadoCorretoBol = rand.nextBoolean();
		int resto = number % ref;
		if (resultadoCorretoBol)
			number += (ref - resto);
		else if (resto == 0)
			number++;

		pergunta = "" + (number) + " é divisível por " + ref + "?";
	
		resolucaoLatex = ResolucaoDivisibilidade.resolucao4(resultadoCorretoBol, number);

	}

	public static void main(String[] args)
	{
		new Divisibilidade4(1);
	}
}
