package matematica.avancado.funcaoexponencial.nivel2package;

import matematica.GeradorExercicio;
import matematica.avancado.funcaoexponencial.ProblemaFuncaoExponencial;
import matematica.avancado.funcaoexponencial.TextoFuncaoExponencial;
import matematica.avancado.funcaoexponencial.TipoFuncaoExponencial;

public class Expressao5 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaFuncaoExponencial problema = TextoFuncaoExponencial.getProblema(TipoFuncaoExponencial.MEIAVIDA);
		problema.gerarValores(2);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
