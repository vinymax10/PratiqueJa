package matematica.basico.conjuntos.nivel3package;

import matematica.GeradorExercicio;

public class Conjuntos11 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		OperacoesMenos problema = TextoOperacoesMenos.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
