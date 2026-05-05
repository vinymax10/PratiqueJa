package Matematica.Avancado.SistemaEquacoes.Nivel2Package;

import Matematica.Avancado.SistemaEquacoes.ResolucaoComparacao;
import Matematica.Avancado.SistemaEquacoes.SistemaEquacoes;
import Modelo.Matematica.Conta;


public class Sistema4 extends Conta
{
	private static final long serialVersionUID = 1L;

//	Sistema metodo subtituição
	public Sistema4(int indice)
	{
		super(indice);
		
		SistemaEquacoes sistema=new SistemaEquacoes();
		sistema.construirX1X2(false);
		
		pergunta="Encontre \\(y\\) pelo método da comparação.";
		
		resultadoCorreto = ""+sistema.y;
		
		resolucaoLatex=ResolucaoComparacao.comparacaoY(sistema);
		
		textLatex=sistema.latex();
	}

	public static void main(String[] args)
	{
		new Sistema4(1);
	}

}
