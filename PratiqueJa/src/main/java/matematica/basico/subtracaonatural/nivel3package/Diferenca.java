package matematica.basico.subtracaonatural.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.subtracaonatural.ProblemaDiferenca;
import matematica.basico.subtracaonatural.TextoDiferenca;

public class Diferenca extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaDiferenca problema = TextoDiferenca.getProblema();
		problema.gerarValores(3);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		setResolucao(problema.resolucao());
	}
}
