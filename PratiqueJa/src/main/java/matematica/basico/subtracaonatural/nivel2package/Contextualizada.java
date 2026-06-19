package matematica.basico.subtracaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.subtracaonatural.ProblemaResta;
import matematica.basico.subtracaonatural.TextoResta;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaResta problema = TextoResta.getProblema();
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
