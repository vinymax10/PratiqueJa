package matematica.basico.adicaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.adicaonatural.ProblemaSomaTripla;
import matematica.basico.adicaonatural.TextoSomaTripla;

public class ProblemaDoisPassos extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaSomaTripla problema = TextoSomaTripla.getProblema();
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
