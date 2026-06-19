package matematica.basico.divisaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisaonatural.ProblemaTresEtapas;
import matematica.basico.divisaonatural.TextoTresEtapas;

public class TresEtapas extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaTresEtapas problema = TextoTresEtapas.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
