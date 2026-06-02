package matematica.avancado.probabilidade.nivel2package;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import modelo.matematica.Conta;

public class Probabilidade1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Probabilidade1(int indice)
	{
		super(indice);

		ProblemaProbabilidadeCondicao2 problema = TextoProbabilidadeCondicao2.getProblemaProporcao();
		problema.gerarValores();
		pergunta = problema.getPergunta();

		resultadoCorreto = "" + problema.resultado().toString();

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
        	new Probabilidade1(1);
		}
	}

}
