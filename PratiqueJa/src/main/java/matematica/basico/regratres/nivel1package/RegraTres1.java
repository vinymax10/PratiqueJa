package matematica.basico.regratres.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.regratres.ProblemaRegraTres;

public class RegraTres1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaRegraTres problema = TextoRegraTresSimples.getProblemaProporcao();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado().toString());
		for(String passo : problema.resolucao())
			addResolucao("\\(" + passo + "\\)");
	}
}
