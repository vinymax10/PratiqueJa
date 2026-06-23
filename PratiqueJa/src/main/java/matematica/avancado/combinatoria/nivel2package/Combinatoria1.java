package matematica.avancado.combinatoria.nivel2package;

import matematica.GeradorExercicio;

public class Combinatoria1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaCombinacaoSimples problema = TextoCombinacaoSimples.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
