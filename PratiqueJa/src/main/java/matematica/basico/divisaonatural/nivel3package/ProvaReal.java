package matematica.basico.divisaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.divisaonatural.ProblemaProvaReal;
import matematica.basico.divisaonatural.TextoProvaReal;

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
