package matematica.basico.divisaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.divisaonatural.ProblemaDivisaoResto;
import matematica.basico.divisaonatural.TextoDivisaoResto;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaDivisaoResto problema = TextoDivisaoResto.getProblema();
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		embaralharEAdicionarAlternativas(problema.resultado(), problema.getDistratores());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
