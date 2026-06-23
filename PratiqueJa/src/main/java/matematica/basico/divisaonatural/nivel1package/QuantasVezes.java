package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.divisaonatural.ProblemaQuantasVezes;
import matematica.basico.divisaonatural.TextoQuantasVezes;

public class QuantasVezes extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaQuantasVezes problema = TextoQuantasVezes.getProblema();
		problema.gerarValores(1);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
