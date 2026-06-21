package matematica.avancado.funcaoquadratica.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.funcaoquadratica.ProblemaFuncaoQuadratica;
import matematica.avancado.funcaoquadratica.TextoFuncaoQuadratica;

public class Expressao9 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaFuncaoQuadratica problema = TextoFuncaoQuadratica.getProblema();
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
