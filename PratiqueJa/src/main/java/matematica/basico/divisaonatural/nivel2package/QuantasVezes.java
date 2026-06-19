package matematica.basico.divisaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.divisaonatural.ProblemaDivisaoResto;
import matematica.basico.divisaonatural.TextoQuantasVezesResto;

public class QuantasVezes extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaDivisaoResto problema = TextoQuantasVezesResto.getProblema();
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		embaralharEAdicionarAlternativas(problema.resultado(), problema.getDistratores());
		setResolucao(problema.resolucao());
	}
}
