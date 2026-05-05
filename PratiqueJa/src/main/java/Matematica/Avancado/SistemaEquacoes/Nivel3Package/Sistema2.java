package Matematica.Avancado.SistemaEquacoes.Nivel3Package;

import Matematica.Avancado.SistemaEquacoes.ResolucaoAdicao;
import Matematica.Avancado.SistemaEquacoes.SistemaEquacoes;
import Modelo.Matematica.Conta;


public class Sistema2 extends Conta
{
	private static final long serialVersionUID = 1L;

//	Sistema metodo subtituição
	public Sistema2(int indice)
	{
		super(indice);
		
		SistemaEquacoes sistema=new SistemaEquacoes();
		sistema.construirX1(true);
		
		pergunta="Encontre \\(z\\) pelo método da adição.";
		
		resultadoCorreto = ""+sistema.tres.valor;
		
		resolucaoLatex=ResolucaoAdicao.adicaoY(sistema);
		
		textLatex=sistema.latex();
	}

	public static void main(String[] args)
	{
		new Sistema2(1);
	}

}
