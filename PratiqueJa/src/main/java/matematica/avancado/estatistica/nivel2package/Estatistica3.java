package matematica.avancado.estatistica.nivel2package;

import matematica.GeradorExercicio;

public class Estatistica3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaFrequencia problema = TextoFrequencia.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
