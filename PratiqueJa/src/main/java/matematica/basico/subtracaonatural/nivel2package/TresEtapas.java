package matematica.basico.subtracaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.subtracaonatural.ProblemaTresEtapas;
import matematica.basico.subtracaonatural.TextoTresEtapas;

public class TresEtapas extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaTresEtapas problema = TextoTresEtapas.getProblema();
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
