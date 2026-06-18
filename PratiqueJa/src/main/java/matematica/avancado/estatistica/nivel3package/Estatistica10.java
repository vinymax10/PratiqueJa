package matematica.avancado.estatistica.nivel3package;

import matematica.GeradorExercicio;

public class Estatistica10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaDispersao problema = TextoDispersao.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
