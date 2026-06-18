package matematica.avancado.probabilidade.nivel3package;

import matematica.GeradorExercicio;

public class Exercicio11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProbabilidadeCondicao problema = TextoProbabilidadeCondicao.getProblemaProporcao();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
