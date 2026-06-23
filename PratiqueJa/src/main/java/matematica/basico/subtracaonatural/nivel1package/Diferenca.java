package matematica.basico.subtracaonatural.nivel1package;

import matematica.GeradorExercicio;
import matematica.basico.subtracaonatural.ProblemaDiferenca;
import matematica.basico.subtracaonatural.TextoDiferenca;

public class Diferenca extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaDiferenca problema = TextoDiferenca.getProblema();
		problema.gerarValores(1);

		addParagrafo(problema.getPergunta());
		gerarAlternativasInteiras(problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao(passo);
	}
}
