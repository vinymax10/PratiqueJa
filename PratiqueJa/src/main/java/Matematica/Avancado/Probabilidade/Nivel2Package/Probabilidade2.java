package Matematica.Avancado.Probabilidade.Nivel2Package;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import Modelo.Matematica.Conta;

public class Probabilidade2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Probabilidade2(int indice)
	{
		super(indice);

		ProblemaProbabilidadeUniao problema = TextoProbabilidadeUniao.getProblemaProporcao();
		problema.gerarValores();
		pergunta = problema.getPergunta();

		resultadoCorreto = "" + problema.resultado().toString();

		resolucaoLatex = problema.resolucao();
		System.out.println(pergunta);
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
