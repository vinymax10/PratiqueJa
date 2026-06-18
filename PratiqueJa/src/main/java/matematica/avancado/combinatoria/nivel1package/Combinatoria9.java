package matematica.avancado.combinatoria.nivel1package;

import matematica.GeradorExercicio;

public class Combinatoria9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaPermutacaoCircular problema = TextoPermutacaoCircular.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
