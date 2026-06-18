package matematica.avancado.estatistica.nivel3package;

import matematica.GeradorExercicio;

public class Estatistica14 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaQuartis problema = TextoQuartis.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
