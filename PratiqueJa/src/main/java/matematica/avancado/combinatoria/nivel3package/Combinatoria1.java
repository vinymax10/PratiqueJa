package matematica.avancado.combinatoria.nivel3package;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import modelo.matematica.Conta;


public class Combinatoria1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Combinatoria1(int indice)
	{
		super(indice);

		ProblemaPermutacaoRepeticao problema=TextoPermutacaoRepeticao.getProblema();
		problema.gerarValores();	
		pergunta=problema.getPergunta();
		
		resultadoCorreto = "" + problema.resultado();
		
		resolucaoLatex = problema.resolucao();
	}

	public static void main(String[] args) throws UnsupportedEncodingException
	{
//		for(int i = 0; i < 100; i++)
//		{
		System.setOut(new PrintStream(System.out, true, "UTF-8"));
			new Combinatoria1(1);
//		}
	}

}
