package matematica.basico.regratres.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.regratres.ProblemaRegraTres;

// Problema de regra de três simples (proporção direta) — quarta variação
public class RegraTres15 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaRegraTres problema = TextoRegraTresSimples.getProblemaProporcao();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado().toString());
		setResolucao("\\(" + problema.resolucao() + "\\)");
	}
}
