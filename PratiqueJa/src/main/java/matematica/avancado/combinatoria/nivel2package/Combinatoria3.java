package matematica.avancado.combinatoria.nivel2package;

import matematica.GeradorExercicio;

public class Combinatoria3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaPascal problema = TextoPascal.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
