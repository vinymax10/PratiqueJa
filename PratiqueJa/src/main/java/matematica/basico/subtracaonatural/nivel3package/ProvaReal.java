package matematica.basico.subtracaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.subtracaonatural.ProblemaProvaReal;
import matematica.basico.subtracaonatural.TextoProvaReal;

public class ProvaReal extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProvaReal problema = TextoProvaReal.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
