package matematica.basico.divisaonatural.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.divisaonatural.ProblemaProvaReal;
import matematica.basico.divisaonatural.TextoProvaReal;

public class ProvaReal extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProvaReal problema = TextoProvaReal.getProblema();
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
