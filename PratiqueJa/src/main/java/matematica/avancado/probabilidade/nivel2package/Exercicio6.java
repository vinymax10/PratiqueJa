package matematica.avancado.probabilidade.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio6 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProbabilidadeCondicao2 problema = TextoProbabilidadeCondicao2.getProblemaProporcao();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
