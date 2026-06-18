package matematica.avancado.estatistica.nivel1package;

import matematica.GeradorExercicio;

public class Estatistica10 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaModa problema = TextoModa.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
