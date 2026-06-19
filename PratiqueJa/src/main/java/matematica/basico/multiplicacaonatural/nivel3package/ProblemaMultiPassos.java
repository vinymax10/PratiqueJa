package matematica.basico.multiplicacaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaonatural.ProblemaTriploProduto;
import matematica.basico.multiplicacaonatural.TextoTriploProduto;

public class ProblemaMultiPassos extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaTriploProduto problema = TextoTriploProduto.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
