package matematica.avancado.sistemaequacoes.nivel2package;

import matematica.avancado.sistemaequacoes.ResolucaoComparacao;
import matematica.avancado.sistemaequacoes.SistemaEquacoes;
import modelo.matematica.Conta;


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
