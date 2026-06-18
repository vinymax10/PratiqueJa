package matematica.basico.conjuntos.nivel3package;

import matematica.GeradorExercicio;

public class Conjuntos4 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		OperacoesUniao problema = TextoOperacoesUniao.getProblema();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		setResolucao(problema.resolucao());
	}
}
