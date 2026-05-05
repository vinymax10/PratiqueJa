package Matematica.Avancado.JurosDesconto.Nivel1Package;

import Modelo.Matematica.Conta;


public class JurosDesconto1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public JurosDesconto1(int indice)
	{
		super(indice);

		ProblemaJurosSimples problema=TextoJurosSimples.getProblemaJuros();
		problema.gerarValores();	
		pergunta=problema.getPergunta();
		
		resultadoCorreto = "" + problema.resultado();
		
		resolucaoLatex = problema.resolucao();
		
	}

	public static void main(String[] args)
	{
		new JurosDesconto1(1);
	}

}
