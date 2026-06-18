package matematica.avancado.combinatoria.nivel3package;

import matematica.GeradorExercicio;

public class Combinatoria7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaBinomioNewton problema = TextoBinomioNewton.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
