package matematica.basico.adicaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.adicaonatural.ProblemaSomaContexto;
import matematica.basico.adicaonatural.TextoSomaContexto;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaSomaContexto problema = TextoSomaContexto.getProblema();
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
