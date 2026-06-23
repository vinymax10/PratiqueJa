package matematica.avancado.estatistica.nivel1package;

import matematica.GeradorExercicio;

public class Estatistica2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaModa problema = TextoModa.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
