package matematica.basico.multiplicacaonatural.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.multiplicacaonatural.ProblemaProduto;
import matematica.basico.multiplicacaonatural.TextoProduto;

public class Contextualizada extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaProduto problema = TextoProduto.getProblema();
		problema.gerarValores(1);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
