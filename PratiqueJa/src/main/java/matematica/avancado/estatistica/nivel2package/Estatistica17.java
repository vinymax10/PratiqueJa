package matematica.avancado.estatistica.nivel2package;

import matematica.GeradorExercicio;

public class Estatistica17 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaMediaPonderada problema = TextoMediaPonderada.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
