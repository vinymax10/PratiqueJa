package matematica.basico.regratres.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.regratres.ProblemaRegraTres;

// Problema de regra de três simples (proporção inversa) — terceira variação
public class RegraTres6 extends GeradorExercicio
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
