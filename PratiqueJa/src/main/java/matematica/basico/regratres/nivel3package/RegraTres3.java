package matematica.basico.regratres.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.regratres.ProblemaRegraTres;

// Problema de regra de três composta (3 grandezas) — variação
public class RegraTres3 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaRegraTres problema = TextoRegraTresComposta.getProblemaProporcao();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado().toString());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
