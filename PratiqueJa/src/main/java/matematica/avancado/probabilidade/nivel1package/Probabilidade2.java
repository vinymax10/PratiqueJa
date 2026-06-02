package matematica.avancado.probabilidade.nivel1package;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import modelo.matematica.Conta;

public class Probabilidade2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Probabilidade2(int indice)
	{
		super(indice);

		ProblemaProbabilidadeSimples2 problema = TextoProbabilidadeSimples2.getProblemaProporcao();
		problema.gerarValores();
		pergunta = problema.getPergunta();

		resultadoCorreto = "" + problema.resultado();

		resolucaoLatex = problema.resolucao();
	}

	public static void main(String[] args)
	{
        try
		{
			System.setOut(new PrintStream(System.out, true, "UTF-8"));
		}
		catch(UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for(int i = 0; i < 50; i++)
		{
        	new Probabilidade2(1);
		}
	}

}
