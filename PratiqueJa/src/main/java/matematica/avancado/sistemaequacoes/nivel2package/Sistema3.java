package matematica.avancado.sistemaequacoes.nivel2package;

import matematica.avancado.sistemaequacoes.ResolucaoComparacao;
import matematica.avancado.sistemaequacoes.SistemaEquacoes;
import modelo.matematica.Conta;


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
