package matematica.intermediario.sistemaequacoes.nivel1package;

import matematica.intermediario.sistemaequacoes.ResolucaoAdicao;
import matematica.intermediario.sistemaequacoes.SistemaEquacoes;
import modelo.matematica.Conta;


public class Sistema1 extends Conta
{
	private static final long serialVersionUID = 1L;

//	Sistema metodo subtituição
	public Sistema1(int indice)
	{
		super(indice);
		
		SistemaEquacoes sistema=new SistemaEquacoes();
		sistema.construirY1(false);
		
		pergunta="Encontre \\(x\\) pelo método da adição.";
		
		resultadoCorreto = ""+sistema.x;
		
		resolucaoLatex=ResolucaoAdicao.adicaoX(sistema);
		
		textLatex=sistema.latex();
	}

	public static void main(String[] args)
	{
		new Sistema1(1);
	}

}
