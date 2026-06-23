package matematica.basico.subtracaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.subtracaonatural.ProblemaResta;
import matematica.basico.subtracaonatural.TextoResta;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaResta problema = TextoResta.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
