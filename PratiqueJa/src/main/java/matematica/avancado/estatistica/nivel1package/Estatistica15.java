package matematica.avancado.estatistica.nivel1package;

import matematica.GeradorExercicio;

public class Estatistica15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaAmplitude problema = TextoAmplitude.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
