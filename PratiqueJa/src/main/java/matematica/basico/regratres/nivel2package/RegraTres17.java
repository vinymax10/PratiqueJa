package matematica.basico.regratres.nivel2package;

import matematica.GeradorExercicio;
import matematica.basico.regratres.ProblemaRegraTres;

// Problema de regra de três simples (proporção inversa) — sétima variação
public class RegraTres17 extends GeradorExercicio
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
