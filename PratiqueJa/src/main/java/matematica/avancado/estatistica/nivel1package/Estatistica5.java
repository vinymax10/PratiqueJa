package matematica.avancado.estatistica.nivel1package;

import matematica.GeradorExercicio;

public class Estatistica5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaMediaSimples problema = TextoMediaSimples.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
