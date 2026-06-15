package matematica.avancado.combinatoria.nivel2package;

import matematica.GeradorExercicio;

public class Combinatoria2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaArranjoSimples problema = TextoArranjoSimples.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
