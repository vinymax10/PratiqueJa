package matematica.basico.divisibilidade.nivel2package;

import matematica.basico.divisibilidade.ResolucaoDivisibilidade;
import modelo.matematica.Conta;


public class Divisibilidade9 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Divisibilidade9(int index)
	{
		super(index);
		int ref = 9;
		int number = 1000 + rand.nextInt(9000);

		resultadoCorretoBol = rand.nextBoolean();
		int resto = number % ref;
		if (resultadoCorretoBol)
			number += (ref - resto);
		else if (resto == 0)
			number++;

		pergunta = "" + (number) + " é divisível por " + ref + "?";

		resolucaoLatex = ResolucaoDivisibilidade.resolucao9(resultadoCorretoBol, number);

	}

	public boolean isCorreta()
	{
		return respostaAlunoBol == resultadoCorretoBol;
	}

	public static void main(String[] args)
	{
		new Divisibilidade9(1);
	}
}
