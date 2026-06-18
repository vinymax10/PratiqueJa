package matematica.intermediario.jurosdesconto.nivel1package;

import matematica.GeradorExercicio;

public class JurosDesconto5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaJurosSimples problema = TextoJurosSimples.getProblemaJuros();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
