package matematica.intermediario.jurosdesconto.nivel3package;

import matematica.GeradorExercicio;

public class JurosDesconto17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaDescontoComposto problema = TextoDescontoComposto.getProblemaDesconto();
		problema.gerarValores();

		String resolucao = problema.resolucao().replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao("\\(" + resolucao + "\\)");
	}
}
