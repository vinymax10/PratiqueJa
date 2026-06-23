package matematica.basico.mmcmdc.nivel3package;

import matematica.GeradorExercicio;
import matematica.basico.mmcmdc.ProblemaMmcMdc;

public class MmcMdc1 extends GeradorExercicio
{
	@Override
	protected void construir()
	{
		ProblemaMmcMdc problema = TextoMmcMdc.getProblemaMMC();
		problema.gerarValores();

		addParagrafo(problema.getPergunta());
		gerarAlternativas("" + problema.resultado());
		for(String passo : problema.resolucao())
			addResolucao("\\(" + passo + "\\)");
	}
}
