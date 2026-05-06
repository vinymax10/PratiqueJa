package matematica.avancado.sistemaequacoes.nivel2package;

import matematica.avancado.sistemaequacoes.ResolucaoSubtituicao;
import matematica.avancado.sistemaequacoes.SistemaEquacoes;
import modelo.matematica.Conta;


public class Sistema2 extends Conta
{
	private static final long serialVersionUID = 1L;

//	Sistema metodo subtituição
	public Sistema2(int indice)
	{
		super(indice);
		
		SistemaEquacoes sistema=new SistemaEquacoes();
		sistema.construirX1(false);
		
		pergunta="Encontre \\(y\\) pelo método da substituição.";
		
		resultadoCorreto = ""+sistema.y;
		
		resolucaoLatex=ResolucaoSubtituicao.substituicaoY(sistema);
		
		textLatex=sistema.latex();
	}

	public static void main(String[] args)
	{
		new Sistema2(1);
	}

}
