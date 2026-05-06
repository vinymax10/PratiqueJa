package matematica.avancado.combinatoria.nivel1package;

import modelo.matematica.Conta;


public class Combinatoria1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Combinatoria1(int indice)
	{
		super(indice);

		ProblemaPermutacaoSimples problema=TextoPermutacaoSimples.getProblema();
		problema.gerarValores();	
		pergunta=problema.getPergunta();
		
		resultadoCorreto = "" + problema.resultado();
		
		resolucaoLatex = problema.resolucao();
	}

	public static void main(String[] args)
	{
//		for(int i = 0; i < 100; i++)
//		{
			new Combinatoria1(1);
//		}
	}

}
