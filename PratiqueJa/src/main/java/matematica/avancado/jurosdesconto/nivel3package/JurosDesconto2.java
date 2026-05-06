package matematica.avancado.jurosdesconto.nivel3package;

import modelo.matematica.Conta;


public class JurosDesconto2 extends Conta
{
	private static final long serialVersionUID = 1L;

	public JurosDesconto2(int indice)
	{
		super(indice);

		ProblemaDescontoComposto problema=TextoDescontoComposto.getProblemaDesconto();
		problema.gerarValores();	
		pergunta=problema.getPergunta();
		
		resultadoCorreto = "" + problema.resultado();
		
		resolucaoLatex = problema.resolucao();
		resolucaoLatex = resolucaoLatex.replace("(", "\\left(").replace(")", "\\right)");

	}

	public static void main(String[] args)
	{
		new JurosDesconto2(1);
	}

}
