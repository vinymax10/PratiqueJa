package matematica.avancado.probabilidade.nivel1package;

import matematica.GeradorExercicio;

public class Probabilidade1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProbabilidadeSimples problema = TextoProbabilidadeSimples.getProblemaProporcao();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
