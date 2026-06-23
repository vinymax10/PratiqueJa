package matematica.avancado.estatistica.nivel2package;

import matematica.GeradorExercicio;

public class Estatistica2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaMediana problema = TextoMediana.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
