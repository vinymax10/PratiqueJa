package matematica.intermediario.pitagoras.nivel3package;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import modelo.matematica.Conta;


public class Exercicio2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Exercicio2(int index)
	{
		super(index);

		ProblemaPitagoras problema = TextoPitagoras.getProblema();
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
	    		new Exercicio2(1);
			}
	}


}
