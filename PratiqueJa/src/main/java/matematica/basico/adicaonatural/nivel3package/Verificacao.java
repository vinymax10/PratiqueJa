package matematica.basico.adicaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.adicaonatural.ProblemaVerificacao;
import matematica.basico.adicaonatural.TextoVerificacao;

public class Verificacao extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaVerificacao problema = TextoVerificacao.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
