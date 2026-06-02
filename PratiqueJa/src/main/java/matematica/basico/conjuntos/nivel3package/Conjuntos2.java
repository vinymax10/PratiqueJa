package matematica.basico.conjuntos.nivel3package;

import modelo.matematica.Conta;


public class Conjuntos2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Conjuntos2(int indice)
	{
		super(indice);

		OperacoesUniao problema=TextoOperacoesUniao.getProblema();
		problema.gerarValores();	
		pergunta=problema.getPergunta();
		
		resultadoCorreto = "" + problema.resultado();
		
		resolucaoLatex = problema.resolucao();

//

		
	}

	public static void main(String[] args)
	{
		for(int i = 0; i < 100; i++)
		{
			new Conjuntos2(1);
		}
	}

}
