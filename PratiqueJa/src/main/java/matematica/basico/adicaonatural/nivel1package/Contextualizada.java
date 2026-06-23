package matematica.basico.adicaonatural.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.adicaonatural.ProblemaSomaContexto;
import matematica.basico.adicaonatural.TextoSomaContexto;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaSomaContexto problema = TextoSomaContexto.getProblema();
		problema.gerarValores(1);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for (String passo : problema.resolucao())
			addResolucao(passo);
	}
}
