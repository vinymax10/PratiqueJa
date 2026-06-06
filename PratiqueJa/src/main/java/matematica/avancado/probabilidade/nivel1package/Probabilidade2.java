package matematica.avancado.probabilidade.nivel1package;

import matematica.GeradorExercicio;

public class Probabilidade2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProbabilidadeSimples2 problema = TextoProbabilidadeSimples2.getProblemaProporcao();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
