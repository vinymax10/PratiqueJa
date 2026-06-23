package matematica.basico.subtracaonatural.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.subtracaonatural.ProblemaProvaReal;
import matematica.basico.subtracaonatural.TextoProvaReal;

public class ProvaReal extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProvaReal problema = TextoProvaReal.getProblema();
		problema.gerarValores(1);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
