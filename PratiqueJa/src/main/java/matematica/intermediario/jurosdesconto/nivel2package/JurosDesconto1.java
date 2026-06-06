package matematica.intermediario.jurosdesconto.nivel2package;

import matematica.GeradorExercicio;

public class JurosDesconto1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaDescontoSimples problema = TextoDescontoSimples.getProblemaDesconto();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
