package matematica.basico.adicaosubtracaointeiro.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.adicaosubtracaointeiro.ProblemaInteiroContexto;
import matematica.basico.adicaosubtracaointeiro.TextoInteiroContexto;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaInteiroContexto problema = TextoInteiroContexto.getProblema();
		problema.gerarValores(1);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteirasComNegativos(problema.resultado());
		for (String passo : problema.resolucao())
			addResolucao(passo);
	}
}
