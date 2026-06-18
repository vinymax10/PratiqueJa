package matematica.avancado.combinatoria.nivel3package;

import matematica.GeradorExercicio;

public class Combinatoria12 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaCombinacaoRestricao problema = TextoCombinacaoRestricao.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
