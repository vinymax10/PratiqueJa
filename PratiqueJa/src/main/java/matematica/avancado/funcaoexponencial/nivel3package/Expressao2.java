package matematica.avancado.funcaoexponencial.nivel3package;

import matematica.GeradorExercicio;
import matematica.avancado.funcaoexponencial.ProblemaFuncaoExponencial;
import matematica.avancado.funcaoexponencial.TextoFuncaoExponencial;
import matematica.avancado.funcaoexponencial.TipoFuncaoExponencial;

public class Expressao2 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaFuncaoExponencial problema = TextoFuncaoExponencial.getProblema(TipoFuncaoExponencial.JUROS);
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
