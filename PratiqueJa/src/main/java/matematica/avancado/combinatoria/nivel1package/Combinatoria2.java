package matematica.avancado.combinatoria.nivel1package;

import matematica.GeradorExercicio;

public class Combinatoria2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaPrincipioMultiplicacao problema = TextoPrincipioMultiplicacao.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
