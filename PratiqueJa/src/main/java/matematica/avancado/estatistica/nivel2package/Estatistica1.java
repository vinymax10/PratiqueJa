package matematica.avancado.estatistica.nivel2package;

import matematica.GeradorExercicio;

public class Estatistica1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaMediaPonderada problema = TextoMediaPonderada.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
