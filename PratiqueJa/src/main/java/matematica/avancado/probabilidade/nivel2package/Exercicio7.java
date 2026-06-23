package matematica.avancado.probabilidade.nivel2package;

import matematica.GeradorExercicio;

public class Exercicio7 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProbabilidadeUniao problema = TextoProbabilidadeUniao.getProblemaProporcao();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
