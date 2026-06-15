package matematica.avancado.combinatoria.nivel3package;

import matematica.GeradorExercicio;

public class Combinatoria1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaPermutacaoRepeticao problema = TextoPermutacaoRepeticao.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
