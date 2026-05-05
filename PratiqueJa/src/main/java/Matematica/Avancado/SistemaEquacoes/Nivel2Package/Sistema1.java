package Matematica.Avancado.SistemaEquacoes.Nivel2Package;

import Matematica.Avancado.SistemaEquacoes.ResolucaoSubtituicao;
import Matematica.Avancado.SistemaEquacoes.SistemaEquacoes;
import Modelo.Matematica.Conta;


public class Sistema1 extends Conta
{
	private static final long serialVersionUID = 1L;

//	Sistema metodo subtituição
	public Sistema1(int indice)
	{
		super(indice);
		
		SistemaEquacoes sistema=new SistemaEquacoes();
		sistema.construirY1(false);
		
		pergunta="Encontre \\(x\\) pelo método da substituição.";
		
		resultadoCorreto = ""+sistema.x;
		
		resolucaoLatex=ResolucaoSubtituicao.substituicaoX(sistema);
		
		textLatex=sistema.latex();
	}

	public static void main(String[] args)
	{
		new Sistema1(1);
	}

}
