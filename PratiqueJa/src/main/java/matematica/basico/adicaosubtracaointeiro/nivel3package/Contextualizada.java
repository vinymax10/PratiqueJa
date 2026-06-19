package matematica.basico.adicaosubtracaointeiro.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.adicaosubtracaointeiro.ProblemaInteiroContexto;
import matematica.basico.adicaosubtracaointeiro.TextoInteiroContexto;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaInteiroContexto problema = TextoInteiroContexto.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteirasComNegativos(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
