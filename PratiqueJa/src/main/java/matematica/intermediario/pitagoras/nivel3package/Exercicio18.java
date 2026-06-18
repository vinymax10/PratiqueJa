package matematica.intermediario.pitagoras.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio18 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaPitagoras problema = TextoPitagoras.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
