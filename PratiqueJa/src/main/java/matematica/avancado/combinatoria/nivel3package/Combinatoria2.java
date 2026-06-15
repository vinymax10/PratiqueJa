package matematica.avancado.combinatoria.nivel3package;

import matematica.GeradorExercicio;

public class Combinatoria2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaCombinacaoRepeticao problema = TextoCombinacaoRepeticao.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
