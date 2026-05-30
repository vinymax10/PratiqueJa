package matematica.intermediario.jurosdesconto.nivel2package;

import modelo.matematica.Conta;


public class JurosDesconto1 extends Conta
{
	private static final long serialVersionUID = 1L;

	public JurosDesconto1(int indice)
	{
		super(indice);

		ProblemaDescontoSimples problema=TextoDescontoSimples.getProblemaDesconto();
		problema.gerarValores();	
		pergunta=problema.getPergunta();
		
		resultadoCorreto = "" + problema.resultado();
		
		resolucaoLatex = problema.resolucao();
		
	}

	public static void main(String[] args)
	{
		new JurosDesconto1(1);
	}

}
