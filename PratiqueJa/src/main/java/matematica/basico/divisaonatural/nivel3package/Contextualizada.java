package matematica.basico.divisaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisaonatural.ProblemaDivisaoResto;
import matematica.basico.divisaonatural.TextoDivisaoResto;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaDivisaoResto problema = TextoDivisaoResto.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		embaralharEAdicionarAlternativas(problema.resultado(), problema.getDistratores());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
