package matematica.avancado.combinatoria.nivel1package;

import matematica.GeradorExercicio;

public class Combinatoria1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaPermutacaoSimples problema = TextoPermutacaoSimples.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
