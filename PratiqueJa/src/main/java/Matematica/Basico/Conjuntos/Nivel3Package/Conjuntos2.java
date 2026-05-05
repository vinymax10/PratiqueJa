package Matematica.Basico.Conjuntos.Nivel3Package;

import Modelo.Matematica.Conta;


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
//		System.out.println(""+pergunta);

//		System.out.println("pergunta: "+pergunta);
//		System.out.println("resultadoCorreto: "+resultadoCorreto);
//
//		System.out.println("resolucaoLatex: "+resolucaoLatex);

		
	}

	public static void main(String[] args)
	{
		for(int i = 0; i < 100; i++)
		{
			new Conjuntos2(1);
		}
	}

}
