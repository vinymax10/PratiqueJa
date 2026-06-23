package matematica.avancado.combinatoria.nivel3package;

import matematica.GeradorExercicio;

public class Combinatoria3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaBinomioNewton problema = TextoBinomioNewton.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
