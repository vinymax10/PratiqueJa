package matematica.intermediario.jurosdesconto.nivel3package;

import matematica.GeradorExercicio;

public class JurosDesconto13 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaJurosComposto problema = TextoJurosComposto.getProblemaJuros();
		problema.gerarValores();

		String resolucao = problema.resolucao().replace("(", "\\left(").replace(")", "\\right)");

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao("\\(" + resolucao + "\\)");
	}
}
