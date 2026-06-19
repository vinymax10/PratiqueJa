package matematica.basico.adicaosubtracaointeiro.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.adicaosubtracaointeiro.ProblemaInteiroContexto;
import matematica.basico.adicaosubtracaointeiro.TextoInteiroContexto;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaInteiroContexto problema = TextoInteiroContexto.getProblema();
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteirasComNegativos(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
