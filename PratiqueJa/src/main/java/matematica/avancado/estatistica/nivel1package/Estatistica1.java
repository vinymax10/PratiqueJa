package matematica.avancado.estatistica.nivel1package;

import matematica.GeradorExercicio;

public class Estatistica1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaMediaSimples problema = TextoMediaSimples.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
