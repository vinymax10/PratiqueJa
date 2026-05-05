package Matematica.Avancado.JurosDesconto.Nivel3Package;

import Modelo.Matematica.Conta;


public class JurosDesconto1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public JurosDesconto1(int indice)
	{
		super(indice);

		ProblemaJurosComposto problema=TextoJurosComposto.getProblemaJuros();
		problema.gerarValores();	
		pergunta=problema.getPergunta();
		
		resultadoCorreto = "" + problema.resultado();
		
		resolucaoLatex = problema.resolucao();
		resolucaoLatex = resolucaoLatex.replace("(", "\\left(").replace(")", "\\right)");
		
	}

	public static void main(String[] args)
	{
		new JurosDesconto1(1);
	}

}
