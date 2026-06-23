package matematica.basico.multiplicacaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaonatural.ProblemaProduto;
import matematica.basico.multiplicacaonatural.TextoProduto;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProduto problema = TextoProduto.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for (String passo : problema.resolucao())
			addResolucao(passo);
	}
}
