package matematica.basico.regratres.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.regratres.ProblemaRegraTres;

public class RegraTres1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaRegraTres problema = TextoRegraTresInversa.getProblemaProporcao();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado().toString());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
