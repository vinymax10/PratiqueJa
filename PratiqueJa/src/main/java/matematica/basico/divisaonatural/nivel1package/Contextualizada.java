package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.divisaonatural.ProblemaDivisaoExata;
import matematica.basico.divisaonatural.TextoDivisaoExata;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaDivisaoExata problema = TextoDivisaoExata.getProblema();
		problema.gerarValores(1);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
