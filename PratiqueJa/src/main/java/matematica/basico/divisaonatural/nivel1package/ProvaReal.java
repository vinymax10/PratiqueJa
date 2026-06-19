package matematica.basico.divisaonatural.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.divisaonatural.ProblemaProvaReal;
import matematica.basico.divisaonatural.TextoProvaReal;

public class ProvaReal extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProvaReal problema = TextoProvaReal.getProblema();
		problema.gerarValores(1);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
