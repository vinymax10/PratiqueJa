package matematica.basico.adicaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.adicaonatural.ProblemaSomaTripla;
import matematica.basico.adicaonatural.TextoSomaTripla;

public class ProblemaDoisPassos extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaSomaTripla problema = TextoSomaTripla.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for (String passo : problema.resolucao())
			addResolucao(passo);
	}
}
