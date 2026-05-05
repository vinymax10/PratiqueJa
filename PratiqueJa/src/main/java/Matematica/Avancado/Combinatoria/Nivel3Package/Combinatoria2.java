package Matematica.Avancado.Combinatoria.Nivel3Package;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import Modelo.Matematica.Conta;


public class Combinatoria2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Combinatoria2(int indice)
	{
		super(indice);

		ProblemaCombinacaoRepeticao problema=TextoCombinacaoRepeticao.getProblema();
		problema.gerarValores();	
		pergunta=problema.getPergunta();
		
		resultadoCorreto = "" + problema.resultado();
		
		resolucaoLatex = problema.resolucao();
	}

	public static void main(String[] args) throws UnsupportedEncodingException
	{
//		for(int i = 0; i < 100; i++)
//		{
//			System.setOut(new PrintStream(System.out, true, "UTF-8"));
			new Combinatoria2(1);
//		}
	}

}
