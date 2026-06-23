package matematica.basico.subtracaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.subtracaonatural.ProblemaTresEtapas;
import matematica.basico.subtracaonatural.TextoTresEtapas;

public class TresEtapas extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaTresEtapas problema = TextoTresEtapas.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
