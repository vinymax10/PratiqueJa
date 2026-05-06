package matematica.avancado.sistemaequacoes.nivel3package;

import matematica.avancado.sistemaequacoes.ResolucaoSubtituicao;
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
		sistema.construirX1(true);
		
		pergunta="Encontre \\(z\\) pelo método da substituição.";
		
		resultadoCorreto = ""+sistema.tres.valor;
		
		resolucaoLatex=ResolucaoSubtituicao.substituicaoY(sistema);
		
		textLatex=sistema.latex();
	}

	public static void main(String[] args)
	{
		new Sistema4(1);
	}

}
