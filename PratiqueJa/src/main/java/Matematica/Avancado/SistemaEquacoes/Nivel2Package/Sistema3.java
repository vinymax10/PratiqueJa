package Matematica.Avancado.SistemaEquacoes.Nivel2Package;

import Matematica.Avancado.SistemaEquacoes.ResolucaoComparacao;
import Matematica.Avancado.SistemaEquacoes.SistemaEquacoes;
import Modelo.Matematica.Conta;


public class Sistema3 extends Conta
{
	private static final long serialVersionUID = 1L;

//	Sistema metodo comparação
	public Sistema3(int indice)
	{
		super(indice);
		
		SistemaEquacoes sistema=new SistemaEquacoes();
		sistema.construirY1Y2(false);
		
		pergunta="Encontre \\(x\\) pelo método da comparação.";
		
		resultadoCorreto = ""+sistema.x;
		
		resolucaoLatex=ResolucaoComparacao.comparacaoX(sistema);
		
		textLatex=sistema.latex();
	}

	public static void main(String[] args)
	{
		new Sistema3(1);
	}

}
