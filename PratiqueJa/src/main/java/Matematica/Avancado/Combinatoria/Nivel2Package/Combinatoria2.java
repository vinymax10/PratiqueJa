package Matematica.Avancado.Combinatoria.Nivel2Package;

import Modelo.Matematica.Conta;


public class Combinatoria2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public Combinatoria2(int indice)
	{
		super(indice);

		ProblemaArranjoSimples problema=TextoArranjoSimples.getProblema();
		problema.gerarValores();	
		pergunta=problema.getPergunta();
		
		resultadoCorreto = "" + problema.resultado();
		
		resolucaoLatex = problema.resolucao();
	}

	public static void main(String[] args)
	{
//		for(int i = 0; i < 100; i++)
//		{
			new Combinatoria2(1);
//		}
	}

}
