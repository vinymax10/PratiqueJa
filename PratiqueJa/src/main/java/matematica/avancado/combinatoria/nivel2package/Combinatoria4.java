package matematica.avancado.combinatoria.nivel2package;

import matematica.GeradorExercicio;

public class Combinatoria4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaCombinacaoSimples problema = TextoCombinacaoSimples.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
