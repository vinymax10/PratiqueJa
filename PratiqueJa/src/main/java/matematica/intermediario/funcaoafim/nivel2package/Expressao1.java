package matematica.intermediario.funcaoafim.nivel2package;

import matematica.GeradorExercicio;
import matematica.intermediario.funcaoafim.ProblemaFuncaoAfim;
import matematica.intermediario.funcaoafim.TextoFuncaoAfim;

public class Expressao1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaFuncaoAfim problema = TextoFuncaoAfim.getProblema();
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
