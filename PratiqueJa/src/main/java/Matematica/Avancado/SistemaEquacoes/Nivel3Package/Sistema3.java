package Matematica.Avancado.SistemaEquacoes.Nivel3Package;

import Matematica.Avancado.SistemaEquacoes.ResolucaoSubtituicao;
import Matematica.Avancado.SistemaEquacoes.SistemaEquacoes;
import Modelo.Matematica.Conta;


public class Sistema3 extends Conta
{
	private static final long serialVersionUID = 1L;

//	Sistema metodo subtituição
	public Sistema3(int indice)
	{
		super(indice);
		
		SistemaEquacoes sistema=new SistemaEquacoes();
		sistema.construirY1(true);
		
		pergunta="Encontre \\(z\\) pelo método da substituição.";
		
		resultadoCorreto = ""+sistema.tres.valor;
		
		resolucaoLatex=ResolucaoSubtituicao.substituicaoX(sistema);
		
		textLatex=sistema.latex();
	}

	public static void main(String[] args)
	{
		new Sistema3(1);
	}

}
