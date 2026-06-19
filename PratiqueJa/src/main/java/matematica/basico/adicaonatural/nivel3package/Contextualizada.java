package matematica.basico.adicaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.adicaonatural.ProblemaSomaContexto;
import matematica.basico.adicaonatural.TextoSomaContexto;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaSomaContexto problema = TextoSomaContexto.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
